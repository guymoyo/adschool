// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Inscription;
import java.lang.String;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

privileged aspect InscriptionController_Roo_Controller_Finder {
    
    @RequestMapping(params = { "find=ByAnnee", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByAnneeForm(Model uiModel) {
        uiModel.addAttribute("anneescolaires", AnneeScolaire.findAllAnneeScolaires());
        return "inscriptions/findInscriptionsByAnnee";
    }
    
    @RequestMapping(params = "find=ByAnnee", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByAnnee(@RequestParam("annee") AnneeScolaire annee, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByAnnee(annee).getResultList());
        return "inscriptions/list";
    }
    
    @RequestMapping(params = { "find=ByClasse", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByClasseForm(Model uiModel) {
        uiModel.addAttribute("classes", Classe.findAllClasses());
        return "inscriptions/findInscriptionsByClasse";
    }
    
    @RequestMapping(params = "find=ByClasse", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByClasse(@RequestParam("classe") Classe classe, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByClasse(classe).getResultList());
        return "inscriptions/list";
    }
    
    @RequestMapping(params = { "find=ByDateInscriptionBetween", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByDateInscriptionBetweenForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "inscriptions/findInscriptionsByDateInscriptionBetween";
    }
    
    @RequestMapping(params = "find=ByDateInscriptionBetween", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByDateInscriptionBetween(@RequestParam("minDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByDateInscriptionBetween(minDateInscription, maxDateInscription).getResultList());
        addDateTimeFormatPatterns(uiModel);
        return "inscriptions/list";
    }
    
    @RequestMapping(params = { "find=ByDateInscriptionEquals", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByDateInscriptionEqualsForm(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "inscriptions/findInscriptionsByDateInscriptionEquals";
    }
    
    @RequestMapping(params = "find=ByDateInscriptionEquals", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByDateInscriptionEquals(@RequestParam("dateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateInscription, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByDateInscriptionEquals(dateInscription).getResultList());
        addDateTimeFormatPatterns(uiModel);
        return "inscriptions/list";
    }
    
    @RequestMapping(params = { "find=ByEleve", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByEleveForm(Model uiModel) {
        uiModel.addAttribute("eleves", Eleve.findAllEleves());
        return "inscriptions/findInscriptionsByEleve";
    }
    
    @RequestMapping(params = "find=ByEleve", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByEleve(@RequestParam("eleve") Eleve eleve, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByEleve(eleve).getResultList());
        return "inscriptions/list";
    }
    
    @RequestMapping(params = { "find=ByInscriptionKeyLike", "form" }, method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByInscriptionKeyLikeForm(Model uiModel) {
        return "inscriptions/findInscriptionsByInscriptionKeyLike";
    }
    
    @RequestMapping(params = "find=ByInscriptionKeyLike", method = RequestMethod.GET)
    public String InscriptionController.findInscriptionsByInscriptionKeyLike(@RequestParam("inscriptionKey") String inscriptionKey, Model uiModel) {
        uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByInscriptionKeyLike(inscriptionKey).getResultList());
        return "inscriptions/list";
    }
    
}
