// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Classe;
import adschool.domain.Filiere;
import adschool.domain.Options;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.String;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

privileged aspect ClasseController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.showJson(@PathVariable("id") Long id) {
        Classe classe = Classe.findClasse(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        if (classe == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(classe.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.listJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findAllClasses()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> ClasseController.createFromJson(@RequestBody String json) {
        Classe.fromJsonToClasse(json).persist();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> ClasseController.createFromJsonArray(@RequestBody String json) {
        for (Classe classe: Classe.fromJsonArrayToClasses(json)) {
            classe.persist();
        }
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> ClasseController.updateFromJson(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (Classe.fromJsonToClasse(json).merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> ClasseController.updateFromJsonArray(@RequestBody String json) {
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        for (Classe classe: Classe.fromJsonArrayToClasses(json)) {
            if (classe.merge() == null) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> ClasseController.deleteFromJson(@PathVariable("id") Long id) {
        Classe classe = Classe.findClasse(id);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Type", "application/text");
        if (classe == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        classe.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByActif", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.jsonFindClassesByActif(@RequestParam(value = "actif", required = false) Boolean actif) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findClassesByActif(actif == null ? new Boolean(false) : actif).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByFiliere", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.jsonFindClassesByFiliere(@RequestParam("filiere") Filiere filiere) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findClassesByFiliere(filiere).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByNomClasseLike", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.jsonFindClassesByNomClasseLike(@RequestParam("nomClasse") String nomClasse) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findClassesByNomClasseLike(nomClasse).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByNomClasseNotEquals", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.jsonFindClassesByNomClasseNotEquals(@RequestParam("nomClasse") String nomClasse) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findClassesByNomClasseNotEquals(nomClasse).getResultList()), headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByOptions", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> ClasseController.jsonFindClassesByOptions(@RequestParam("options") Options options) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/text; charset=utf-8");
        return new ResponseEntity<String>(Classe.toJsonArray(Classe.findClassesByOptions(options).getResultList()), headers, HttpStatus.OK);
    }
    
}
