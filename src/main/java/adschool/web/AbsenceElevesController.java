package adschool.web;

import adschool.domain.AbsenceEleves;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "absenceeleveses", formBackingObject = AbsenceEleves.class)
@RequestMapping("/absenceeleveses")
@Controller
public class AbsenceElevesController {
	
	@ModelAttribute("discipline")
    public String populateMenu() {
        return "block";
    }
}
