// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;
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

privileged aspect MatiereController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String MatiereController.create(@Valid Matiere matiere, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("matiere", matiere);
            return "matieres/create";
        }
        uiModel.asMap().clear();
        matiere.persist();
        return "redirect:/matieres/" + encodeUrlPathSegment(matiere.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String MatiereController.createForm(Model uiModel) {
        uiModel.addAttribute("matiere", new Matiere());
        return "matieres/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String MatiereController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("matiere", Matiere.findMatiere(id));
        uiModel.addAttribute("itemId", id);
        return "matieres/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String MatiereController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("matieres", Matiere.findMatiereEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Matiere.countMatieres() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("matieres", Matiere.findAllMatieres());
        }
        return "matieres/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String MatiereController.update(@Valid Matiere matiere, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("matiere", matiere);
            return "matieres/update";
        }
        uiModel.asMap().clear();
        matiere.merge();
        return "redirect:/matieres/" + encodeUrlPathSegment(matiere.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String MatiereController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("matiere", Matiere.findMatiere(id));
        return "matieres/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String MatiereController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Matiere.findMatiere(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/matieres";
    }
    
    @ModelAttribute("classes")
    public Collection<Classe> MatiereController.populateClasses() {
        return Classe.findAllClasses();
    }
    
    @ModelAttribute("famillematieres")
    public Collection<FamilleMatiere> MatiereController.populateFamilleMatieres() {
        return FamilleMatiere.findAllFamilleMatieres();
    }
    
    @ModelAttribute("groupematieres")
    public Collection<GroupeMatiere> MatiereController.populateGroupeMatieres() {
        return GroupeMatiere.findAllGroupeMatieres();
    }
    
    @ModelAttribute("matieres")
    public Collection<Matiere> MatiereController.populateMatieres() {
        return Matiere.findAllMatieres();
    }
    
    String MatiereController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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