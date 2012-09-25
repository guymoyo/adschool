// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.GroupeMatiere;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
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

privileged aspect GroupeMatiereController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String GroupeMatiereController.create(@Valid GroupeMatiere groupeMatiere, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("groupeMatiere", groupeMatiere);
            return "groupematieres/create";
        }
        uiModel.asMap().clear();
        groupeMatiere.persist();
        return "redirect:/groupematieres/" + encodeUrlPathSegment(groupeMatiere.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String GroupeMatiereController.createForm(Model uiModel) {
        uiModel.addAttribute("groupeMatiere", new GroupeMatiere());
        return "groupematieres/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String GroupeMatiereController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("groupematiere", GroupeMatiere.findGroupeMatiere(id));
        uiModel.addAttribute("itemId", id);
        return "groupematieres/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String GroupeMatiereController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("groupematieres", GroupeMatiere.findGroupeMatiereEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) GroupeMatiere.countGroupeMatieres() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("groupematieres", GroupeMatiere.findAllGroupeMatieres());
        }
        return "groupematieres/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String GroupeMatiereController.update(@Valid GroupeMatiere groupeMatiere, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("groupeMatiere", groupeMatiere);
            return "groupematieres/update";
        }
        uiModel.asMap().clear();
        groupeMatiere.merge();
        return "redirect:/groupematieres/" + encodeUrlPathSegment(groupeMatiere.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String GroupeMatiereController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("groupeMatiere", GroupeMatiere.findGroupeMatiere(id));
        return "groupematieres/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String GroupeMatiereController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        GroupeMatiere.findGroupeMatiere(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/groupematieres";
    }
    
    @ModelAttribute("classes")
    public Collection<Classe> GroupeMatiereController.populateClasses() {
        return Classe.findAllClasses();
    }
    
    @ModelAttribute("groupematieres")
    public Collection<GroupeMatiere> GroupeMatiereController.populateGroupeMatieres() {
        return GroupeMatiere.findAllGroupeMatieres();
    }
    
    String GroupeMatiereController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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