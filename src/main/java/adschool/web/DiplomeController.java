package adschool.web;

import adschool.domain.Diplome;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "diplomes", formBackingObject = Diplome.class)
@RequestMapping("/diplomes")
@Controller
public class DiplomeController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
}
