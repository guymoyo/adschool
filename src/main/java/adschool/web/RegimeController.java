package adschool.web;

import adschool.domain.Regime;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "regimes", formBackingObject = Regime.class)
@RequestMapping("/regimes")
@Controller
public class RegimeController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
}
