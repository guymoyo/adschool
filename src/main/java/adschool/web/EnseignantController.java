package adschool.web;

import adschool.domain.Enseignant;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "enseignants", formBackingObject = Enseignant.class)
@RequestMapping("/enseignants")
@Controller
public class EnseignantController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
}
