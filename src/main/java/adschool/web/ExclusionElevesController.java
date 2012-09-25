package adschool.web;

import adschool.domain.ExclusionEleves;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "exclusioneleveses", formBackingObject = ExclusionEleves.class)
@RequestMapping("/exclusioneleveses")
@Controller
public class ExclusionElevesController {
	
	@ModelAttribute("discipline")
    public String populateMenu() {
        return "block";
    }
}
