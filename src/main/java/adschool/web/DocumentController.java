package adschool.web;

import adschool.domain.Document;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "documents", formBackingObject = Document.class)
@RequestMapping("/documents")
@Controller
public class DocumentController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
}
