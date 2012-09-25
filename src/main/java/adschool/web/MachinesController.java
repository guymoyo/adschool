package adschool.web;

import adschool.domain.Machines;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "machineses", formBackingObject = Machines.class)
@RequestMapping("/machineses")
@Controller
public class MachinesController {
	
	@ModelAttribute("securite")
    public String populateMenu() {
        return "block";
    }
}
