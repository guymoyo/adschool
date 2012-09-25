package adschool.web;

import adschool.domain.Options;
import adschool.domain.Speciality;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "specialitys", formBackingObject = Speciality.class)
@RequestMapping("/specialitys")
@Controller
public class SpecialityController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	 @RequestMapping(value = "/listeoptions", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("optionses", Options.findOptionsesBySpecialite(Speciality.findSpeciality(id)).getResultList());
	     
	        return "optionses/list";
	    }
	
	
	
}
