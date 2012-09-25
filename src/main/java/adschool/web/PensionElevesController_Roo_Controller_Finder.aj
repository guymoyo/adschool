// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Eleve;
import adschool.domain.PensionEleves;
import java.lang.String;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect PensionElevesController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByEleve", "form" }, method = RequestMethod.GET)
    public String PensionElevesController.findPensionElevesesByEleveForm(Model uiModel) {
        uiModel.addAttribute("eleves", Eleve.findAllEleves());
        return "pensioneleveses/findPensionElevesesByEleve";
    }
    
    @RequestMapping(params = "find=ByEleve", method = RequestMethod.GET)
    public String PensionElevesController.findPensionElevesesByEleve(@RequestParam("eleve") Eleve eleve, Model uiModel) {
        uiModel.addAttribute("pensioneleveses", PensionEleves.findPensionElevesesByEleve(eleve).getResultList());
        return "pensioneleveses/list";
    }
    
    @RequestMapping(params = { "find=ByPensionKeyLike", "form" }, method = RequestMethod.GET)
    public String PensionElevesController.findPensionElevesesByPensionKeyLikeForm(Model uiModel) {
        return "pensioneleveses/findPensionElevesesByPensionKeyLike";
    }
    
    @RequestMapping(params = "find=ByPensionKeyLike", method = RequestMethod.GET)
    public String PensionElevesController.findPensionElevesesByPensionKeyLike(@RequestParam("pensionKey") String pensionKey, Model uiModel) {
        uiModel.addAttribute("pensioneleveses", PensionEleves.findPensionElevesesByPensionKeyLike(pensionKey).getResultList());
        return "pensioneleveses/list";
    }
    
}