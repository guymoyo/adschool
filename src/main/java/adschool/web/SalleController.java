package adschool.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import adschool.domain.Evaluation;
import adschool.domain.Occupation;
import adschool.domain.Periode;
import adschool.domain.Salle;

@RooWebScaffold(path = "salles", formBackingObject = Salle.class)
@RequestMapping("/salles")
@Controller
public class SalleController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	
	 @RequestMapping(value = "/listeoccupations", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("occupations", Occupation.findOccupationsBySalle(Salle.findSalle(id)).getResultList());
	     
	        return "occupations/list";
	    }
	
}
