// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.GroupeMatiere;
import java.lang.String;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect GroupeMatiereController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByClasse", "form" }, method = RequestMethod.GET)
    public String GroupeMatiereController.findGroupeMatieresByClasseForm(Model uiModel) {
        uiModel.addAttribute("classes", Classe.findAllClasses());
        return "groupematieres/findGroupeMatieresByClasse";
    }
    
    @RequestMapping(params = "find=ByClasse", method = RequestMethod.GET)
    public String GroupeMatiereController.findGroupeMatieresByClasse(@RequestParam("classe") Classe classe, Model uiModel) {
        uiModel.addAttribute("groupematieres", GroupeMatiere.findGroupeMatieresByClasse(classe).getResultList());
        return "groupematieres/list";
    }
    
}