package adschool.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import adschool.domain.Etablissement;
import adschool.domain.Parent;
import adschool.utils.FileUploadService;

@RooWebScaffold(path = "etablissements", formBackingObject = Etablissement.class)
@RequestMapping("/etablissements")
@Controller
public class EtablissementController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Etablissement etablissement, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		System.out.println("in create");
		etablissement.validate(bindingResult);
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("etablissement", etablissement);
			return "etablissements/create";
		}
		MultipartFile file=   etablissement.getLogoFile();
		try {
			String filePath =  FileUploadService.storeFile(file,FileUploadService.LOGO_ETS_DIR,etablissement.getNomEtablissement());
			if (filePath == null) {
				etablissement.setLogoPath(FileUploadService.getDefaultImage(etablissement));
			} else {
				etablissement.setLogoPath(filePath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uiModel.asMap().clear();
		if (etablissement.getId()==null) {
			etablissement.persist();
		}else {
			etablissement.merge();
		}

		return "redirect:/etablissements/" + encodeUrlPathSegment(etablissement.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		Etablissement etablissement = Etablissement.findEtablissement(id);
		if(etablissement != null && StringUtils.isNotBlank(etablissement.getLogoPath())) FileUploadService.deleteFiles(etablissement.getLogoPath());
		if(etablissement != null) etablissement.remove();
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/etablissements";
	}
}
