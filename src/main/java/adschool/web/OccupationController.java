package adschool.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.Occupation;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RooWebScaffold(path = "occupations", formBackingObject = Occupation.class)
@RequestMapping("/occupations")
@Controller
public class OccupationController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	   @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid Occupation occupation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("occupation", occupation);
	            addDateTimeFormatPatterns(uiModel);
	            return "occupations/create";
	        }
	        
	        if( occupation.getClasse().getNombreplace() > occupation.getSalle().getNombrePlace()){
	        	uiModel.addAttribute("occupation", occupation);
	        	uiModel.addAttribute("apMessage", "La taille de cette salle > taille de cette classe ");
	            addDateTimeFormatPatterns(uiModel);
	            return "occupations/create";
	        }
	        
	        uiModel.asMap().clear();
	        occupation.persist();
	        return "redirect:/occupations/" + encodeUrlPathSegment(occupation.getId().toString(), httpServletRequest);
	    }
}
