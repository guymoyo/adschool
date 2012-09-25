package adschool.web;

import adschool.domain.Cycles;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "cycleses", formBackingObject = Cycles.class)
@RequestMapping("/cycleses")
@Controller
public class CyclesController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
}
