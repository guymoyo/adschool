package adschool.web;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.GroupeMatiere;
import adschool.domain.Periode;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RooWebScaffold(path = "anneescolaires", formBackingObject = AnneeScolaire.class, update = false, delete = false)
@RequestMapping("/anneescolaires")
@Controller
public class AnneeScolaireController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	   @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid AnneeScolaire anneeScolaire, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("anneeScolaire", anneeScolaire);
	            addDateTimeFormatPatterns(uiModel);
	            return "anneescolaires/create";
	        }
	        
	        if( AnneeScolaire.getCurrentAnneeScolaire() != null){
	        	
	        	uiModel.addAttribute("apMessage"," Annee en cours, Veuillez la cloturer avant ! ");
	        	return "anneescolaires/create";
	        }
	        
	        uiModel.asMap().clear();
	        anneeScolaire.persist();
	        return "redirect:/anneescolaires/" + encodeUrlPathSegment(anneeScolaire.getId().toString(), httpServletRequest);
	    }
	

		@RequestMapping(params = "form", method = RequestMethod.GET)
	    public String createForm(Model uiModel) {
	        uiModel.addAttribute("anneeScolaire", new AnneeScolaire());
	        addDateTimeFormatPatterns(uiModel);
	        return "anneescolaires/create";
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public String show(@PathVariable("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        uiModel.addAttribute("anneescolaire", AnneeScolaire.findAnneeScolaire(id));
	        uiModel.addAttribute("itemId", id);
	        return "anneescolaires/show";
	    }
	    
	    
	    @RequestMapping(value = "/cloturer")
	    public String cloturer(@RequestParam("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
	      
	    	AnneeScolaire anneeScolaire = AnneeScolaire.findAnneeScolaire(id);
	    	
	    	uiModel.addAttribute("anneescolaire", anneeScolaire);
	        uiModel.addAttribute("id", id);
	        
	        
	        // Traitement effectue avant la cloture de l'annee academique
	        
	        anneeScolaire.cloture();
	        	
	        
	        uiModel.addAttribute("apMessage"," Annee Scolaire cloturee avec succes ");
	        
	        return "anneescolaires/show";
	        
	    }
	    
	    
		 @RequestMapping(value = "/listePeriodes", method = RequestMethod.GET)
		    public String listemodules(@RequestParam("id") Long id, Model uiModel) {
			 	
		        uiModel.addAttribute("anneescolaires", Periode.findPeriodesByAnnee(AnneeScolaire.findAnneeScolaire(id)).getResultList());
		     
		        return "periodes/list";
		    }
	    
	  
}
