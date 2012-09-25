package adschool.web;

import adschool.domain.AvertissementEleves;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "avertissementeleveses", formBackingObject = AvertissementEleves.class)
@RequestMapping("/avertissementeleveses")
@Controller
public class AvertissementElevesController {
	
	@ModelAttribute("discipline")
    public String populateMenu() {
        return "block";
    }
}
