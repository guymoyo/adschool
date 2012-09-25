package adschool.web;

import adschool.domain.Classe;
import adschool.domain.Filiere;
import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "filieres", formBackingObject = Filiere.class)
@RequestMapping("/filieres")
@Controller
public class FiliereController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	 @RequestMapping(value = "/listeclasses", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("classes", Classe.findClassesByFiliere(Filiere.findFiliere(id)).getResultList());
	     
	        return "classes/list";
	    }
}
