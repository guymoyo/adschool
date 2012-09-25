// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.Jours;
import adschool.domain.Occupation;
import adschool.domain.Salle;
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

privileged aspect OccupationController_Roo_Controller {
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String OccupationController.createForm(Model uiModel) {
        uiModel.addAttribute("occupation", new Occupation());
        addDateTimeFormatPatterns(uiModel);
        return "occupations/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String OccupationController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("occupation", Occupation.findOccupation(id));
        uiModel.addAttribute("itemId", id);
        return "occupations/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String OccupationController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("occupations", Occupation.findOccupationEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Occupation.countOccupations() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("occupations", Occupation.findAllOccupations());
        }
        addDateTimeFormatPatterns(uiModel);
        return "occupations/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String OccupationController.update(@Valid Occupation occupation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("occupation", occupation);
            addDateTimeFormatPatterns(uiModel);
            return "occupations/update";
        }
        uiModel.asMap().clear();
        occupation.merge();
        return "redirect:/occupations/" + encodeUrlPathSegment(occupation.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String OccupationController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("occupation", Occupation.findOccupation(id));
        addDateTimeFormatPatterns(uiModel);
        return "occupations/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String OccupationController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Occupation.findOccupation(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/occupations";
    }
    
    @ModelAttribute("classes")
    public Collection<Classe> OccupationController.populateClasses() {
        return Classe.findAllClasses();
    }
    
    @ModelAttribute("jourses")
    public Collection<Jours> OccupationController.populateJourses() {
        return Arrays.asList(Jours.class.getEnumConstants());
    }
    
    @ModelAttribute("occupations")
    public Collection<Occupation> OccupationController.populateOccupations() {
        return Occupation.findAllOccupations();
    }
    
    @ModelAttribute("salles")
    public Collection<Salle> OccupationController.populateSalles() {
        return Salle.findAllSalles();
    }
    
    void OccupationController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("occupation_heurestart_date_format", "HH:MM");
        uiModel.addAttribute("occupation_heurestop_date_format", "HH:MM");
    }
    
    String OccupationController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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