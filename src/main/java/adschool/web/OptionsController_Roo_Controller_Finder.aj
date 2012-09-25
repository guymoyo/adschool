// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Options;
import adschool.domain.Speciality;
import java.lang.String;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect OptionsController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=BySpecialite", "form" }, method = RequestMethod.GET)
    public String OptionsController.findOptionsesBySpecialiteForm(Model uiModel) {
        uiModel.addAttribute("specialitys", Speciality.findAllSpecialitys());
        return "optionses/findOptionsesBySpecialite";
    }
    
    @RequestMapping(params = "find=BySpecialite", method = RequestMethod.GET)
    public String OptionsController.findOptionsesBySpecialite(@RequestParam("specialite") Speciality specialite, Model uiModel) {
        uiModel.addAttribute("optionses", Options.findOptionsesBySpecialite(specialite).getResultList());
        return "optionses/list";
    }
    
}