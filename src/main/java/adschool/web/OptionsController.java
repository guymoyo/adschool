package adschool.web;

import adschool.domain.Classe;
import adschool.domain.Matiere;
import adschool.domain.Options;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "optionses", formBackingObject = Options.class)
@RequestMapping("/optionses")
@Controller
public class OptionsController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	
	 @RequestMapping(value = "/listeclasse", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("classes", Classe.findClassesByOptions(Options.findOptions(id)).getResultList());
	     
	        return "classes/list";
	    }
}
