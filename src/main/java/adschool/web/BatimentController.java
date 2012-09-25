package adschool.web;

import adschool.domain.Batiment;
import adschool.domain.Classe;
import adschool.domain.Matiere;
import adschool.domain.Occupation;
import adschool.domain.Salle;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "batiments", formBackingObject = Batiment.class)
@RequestMapping("/batiments")
@Controller
public class BatimentController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	 @RequestMapping(value = "/listesalles", method = RequestMethod.GET)
	    public String listesalles(@RequestParam("id") Long id, Model uiModel) {
		 			 
	        uiModel.addAttribute("salles", Salle.findSallesByBatiment(Batiment.findBatiment(id)).getResultList());
	     
	        return "salles/list";
	    }
	 
	 @RequestMapping(value = "/listeoccupations", method = RequestMethod.GET)
	    public String listeoccupations(@RequestParam("id") Long id, Model uiModel) {
		 			 
	        uiModel.addAttribute("occupations", Occupation.findOccupationByBatiment(Batiment.findBatiment(id)).getResultList());
	     
	        return "occupations/list";
	    }
}
