// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Inscription;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

privileged aspect InscriptionController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.showJson(@PathVariable("id") Long id) {
        Inscription inscription = Inscription.findInscription(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        if (inscription == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(inscription.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findAllInscriptions()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> InscriptionController.createFromJson(@RequestBody String json) {
        Inscription.fromJsonToInscription(json).persist();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> InscriptionController.createFromJsonArray(@RequestBody String json) {
        for (Inscription inscription: Inscription.fromJsonArrayToInscriptions(json)) {
            inscription.persist();
        }
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> InscriptionController.updateFromJson(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (Inscription.fromJsonToInscription(json).merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> InscriptionController.updateFromJsonArray(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        for (Inscription inscription: Inscription.fromJsonArrayToInscriptions(json)) {
            if (inscription.merge() == null) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> InscriptionController.deleteFromJson(@PathVariable("id") Long id) {
        Inscription inscription = Inscription.findInscription(id);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (inscription == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        inscription.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByAnnee", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByAnnee(@RequestParam("annee") AnneeScolaire annee) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByAnnee(annee).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByClasse", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByClasse(@RequestParam("classe") Classe classe) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByClasse(classe).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByDateInscriptionBetween", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByDateInscriptionBetween(@RequestParam("minDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByDateInscriptionBetween(minDateInscription, maxDateInscription).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByDateInscriptionEquals", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByDateInscriptionEquals(@RequestParam("dateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateInscription) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByDateInscriptionEquals(dateInscription).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByEleve", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByEleve(@RequestParam("eleve") Eleve eleve) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByEleve(eleve).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByInscriptionKeyLike", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> InscriptionController.jsonFindInscriptionsByInscriptionKeyLike(@RequestParam("inscriptionKey") String inscriptionKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Inscription.toJsonArray(Inscription.findInscriptionsByInscriptionKeyLike(inscriptionKey).getResultList()), headers, HttpStatus.OK);
    }
    
}