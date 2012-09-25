package adschool.web;

import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;
import adschool.domain.Options;
import adschool.domain.Speciality;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "groupematieres", formBackingObject = GroupeMatiere.class)
@RequestMapping("/groupematieres")
@Controller
public class GroupeMatiereController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	
	 @RequestMapping(value = "/listeUE", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("matieres", Matiere.findMatieresByGroupe(GroupeMatiere.findGroupeMatiere(id)).getResultList());
	     
	        return "matieres/list";
	    }
	
}
