package adschool.web;

import adschool.domain.EnseignantMatieres;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "enseignantmatiereses", formBackingObject = EnseignantMatieres.class)
@RequestMapping("/enseignantmatiereses")
@Controller
public class EnseignantMatieresController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
}
