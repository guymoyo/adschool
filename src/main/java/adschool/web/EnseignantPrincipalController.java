package adschool.web;

import adschool.domain.EnseignantPrincipal;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "enseignantprincipals", formBackingObject = EnseignantPrincipal.class)
@RequestMapping("/enseignantprincipals")
@Controller
public class EnseignantPrincipalController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
}
