// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.PaiementPensions;
import java.lang.String;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect PaiementPensionsController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByDateVersementBetween", "form" }, method = RequestMethod.GET)
    public String PaiementPensionsController.findPaiementPensionsesByDateVersementBetweenForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "paiementpensionses/findPaiementPensionsesByDateVersementBetween";
    }
    
    @RequestMapping(params = "find=ByDateVersementBetween", method = RequestMethod.GET)
    public String PaiementPensionsController.findPaiementPensionsesByDateVersementBetween(@RequestParam("minDateVersement") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateVersement, @RequestParam("maxDateVersement") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateVersement, Model uiModel) {
        uiModel.addAttribute("paiementpensionses", PaiementPensions.findPaiementPensionsesByDateVersementBetween(minDateVersement, maxDateVersement).getResultList());
        addDateTimeFormatPatterns(uiModel);
        return "paiementpensionses/list";
    }
    
}
