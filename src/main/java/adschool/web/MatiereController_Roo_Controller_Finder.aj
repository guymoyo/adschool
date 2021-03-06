// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;
import java.lang.String;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect MatiereController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByClasse", "form" }, method = RequestMethod.GET)
    public String MatiereController.findMatieresByClasseForm(Model uiModel) {
        uiModel.addAttribute("classes", Classe.findAllClasses());
        return "matieres/findMatieresByClasse";
    }
    
    @RequestMapping(params = { "find=ByFamille", "form" }, method = RequestMethod.GET)
    public String MatiereController.findMatieresByFamilleForm(Model uiModel) {
        uiModel.addAttribute("famillematieres", FamilleMatiere.findAllFamilleMatieres());
        return "matieres/findMatieresByFamille";
    }
    
    @RequestMapping(params = "find=ByFamille", method = RequestMethod.GET)
    public String MatiereController.findMatieresByFamille(@RequestParam("famille") FamilleMatiere famille, Model uiModel) {
        uiModel.addAttribute("matieres", Matiere.findMatieresByFamille(famille).getResultList());
        return "matieres/list";
    }
    
    @RequestMapping(params = { "find=ByGroupe", "form" }, method = RequestMethod.GET)
    public String MatiereController.findMatieresByGroupeForm(Model uiModel) {
        uiModel.addAttribute("groupematieres", GroupeMatiere.findAllGroupeMatieres());
        return "matieres/findMatieresByGroupe";
    }
    
    @RequestMapping(params = "find=ByGroupe", method = RequestMethod.GET)
    public String MatiereController.findMatieresByGroupe(@RequestParam("groupe") GroupeMatiere groupe, Model uiModel) {
        uiModel.addAttribute("matieres", Matiere.findMatieresByGroupe(groupe).getResultList());
        return "matieres/list";
    }
    
}
