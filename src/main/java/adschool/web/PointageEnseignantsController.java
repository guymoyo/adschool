package adschool.web;

import adschool.domain.PointageEnseignants;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "pointageenseignantses", formBackingObject = PointageEnseignants.class)
@RequestMapping("/pointageenseignantses")
@Controller
public class PointageEnseignantsController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
}
