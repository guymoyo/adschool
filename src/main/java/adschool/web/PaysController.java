package adschool.web;

import adschool.domain.Pays;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "payses", formBackingObject = Pays.class)
@RequestMapping("/payses")
@Controller
public class PaysController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
}
