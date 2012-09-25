// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Enseignant;
import adschool.domain.Matiere;
import adschool.domain.PointageEnseignants;
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

privileged aspect PointageEnseignantsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String PointageEnseignantsController.create(@Valid PointageEnseignants pointageEnseignants, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("pointageEnseignants", pointageEnseignants);
            addDateTimeFormatPatterns(uiModel);
            return "pointageenseignantses/create";
        }
        uiModel.asMap().clear();
        pointageEnseignants.persist();
        return "redirect:/pointageenseignantses/" + encodeUrlPathSegment(pointageEnseignants.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String PointageEnseignantsController.createForm(Model uiModel) {
        uiModel.addAttribute("pointageEnseignants", new PointageEnseignants());
        addDateTimeFormatPatterns(uiModel);
        return "pointageenseignantses/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String PointageEnseignantsController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("pointageenseignants", PointageEnseignants.findPointageEnseignants(id));
        uiModel.addAttribute("itemId", id);
        return "pointageenseignantses/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String PointageEnseignantsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("pointageenseignantses", PointageEnseignants.findPointageEnseignantsEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) PointageEnseignants.countPointageEnseignantses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("pointageenseignantses", PointageEnseignants.findAllPointageEnseignantses());
        }
        addDateTimeFormatPatterns(uiModel);
        return "pointageenseignantses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String PointageEnseignantsController.update(@Valid PointageEnseignants pointageEnseignants, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("pointageEnseignants", pointageEnseignants);
            addDateTimeFormatPatterns(uiModel);
            return "pointageenseignantses/update";
        }
        uiModel.asMap().clear();
        pointageEnseignants.merge();
        return "redirect:/pointageenseignantses/" + encodeUrlPathSegment(pointageEnseignants.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String PointageEnseignantsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("pointageEnseignants", PointageEnseignants.findPointageEnseignants(id));
        addDateTimeFormatPatterns(uiModel);
        return "pointageenseignantses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String PointageEnseignantsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PointageEnseignants.findPointageEnseignants(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/pointageenseignantses";
    }
    
    @ModelAttribute("anneescolaires")
    public Collection<AnneeScolaire> PointageEnseignantsController.populateAnneeScolaires() {
        return AnneeScolaire.findAllAnneeScolaires();
    }
    
    @ModelAttribute("classes")
    public Collection<Classe> PointageEnseignantsController.populateClasses() {
        return Classe.findAllClasses();
    }
    
    @ModelAttribute("enseignants")
    public Collection<Enseignant> PointageEnseignantsController.populateEnseignants() {
        return Enseignant.findAllEnseignants();
    }
    
    @ModelAttribute("matieres")
    public Collection<Matiere> PointageEnseignantsController.populateMatieres() {
        return Matiere.findAllMatieres();
    }
    
    @ModelAttribute("pointageenseignantses")
    public Collection<PointageEnseignants> PointageEnseignantsController.populatePointageEnseignantses() {
        return PointageEnseignants.findAllPointageEnseignantses();
    }
    
    void PointageEnseignantsController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("pointageEnseignants_datedebut_date_format", "dd-MM-yyyy");
    }
    
    String PointageEnseignantsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
