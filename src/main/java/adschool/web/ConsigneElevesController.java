package adschool.web;

import adschool.domain.ConsigneEleves;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "consigneeleveses", formBackingObject = ConsigneEleves.class)
@RequestMapping("/consigneeleveses")
@Controller
public class ConsigneElevesController {
	
	@ModelAttribute("discipline")
    public String populateMenu() {
        return "block";
    }
}
