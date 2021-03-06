// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Diplome;
import adschool.domain.LevelDiplome;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect DiplomeController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String DiplomeController.create(@Valid Diplome diplome, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("diplome", diplome);
            return "diplomes/create";
        }
        uiModel.asMap().clear();
        diplome.persist();
        return "redirect:/diplomes/" + encodeUrlPathSegment(diplome.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String DiplomeController.createForm(Model uiModel) {
        uiModel.addAttribute("diplome", new Diplome());
        return "diplomes/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String DiplomeController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("diplome", Diplome.findDiplome(id));
        uiModel.addAttribute("itemId", id);
        return "diplomes/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String DiplomeController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("diplomes", Diplome.findDiplomeEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Diplome.countDiplomes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("diplomes", Diplome.findAllDiplomes());
        }
        return "diplomes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String DiplomeController.update(@Valid Diplome diplome, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("diplome", diplome);
            return "diplomes/update";
        }
        uiModel.asMap().clear();
        diplome.merge();
        return "redirect:/diplomes/" + encodeUrlPathSegment(diplome.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String DiplomeController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("diplome", Diplome.findDiplome(id));
        return "diplomes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String DiplomeController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Diplome.findDiplome(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/diplomes";
    }
    
    @ModelAttribute("diplomes")
    public Collection<Diplome> DiplomeController.populateDiplomes() {
        return Diplome.findAllDiplomes();
    }
    
    @ModelAttribute("leveldiplomes")
    public Collection<LevelDiplome> DiplomeController.populateLevelDiplomes() {
        return Arrays.asList(LevelDiplome.class.getEnumConstants());
    }
    
    String DiplomeController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
