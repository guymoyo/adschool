package adschool.web;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.Classe;
import adschool.domain.ConfigInscription;
import adschool.domain.Eleve;
import adschool.domain.FamilleMatiere;
import adschool.domain.GroupeMatiere;
import adschool.domain.Matiere;
import adschool.domain.Occupation;
import adschool.domain.Salle;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RooWebScaffold(path = "classes", formBackingObject = Classe.class)
@RequestMapping("/classes")
@Controller
public class ClasseController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	
	
	  @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid Classe classe, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("classe", classe);
	            return "classes/create";
	        }
	        
	        List<Classe> listeClasse = Classe.findClassesByNomClasseEquals(classe.getNomClasse(), classe.getNomAbreger()).getResultList();
	    
	        if( listeClasse != null && ! listeClasse.isEmpty()){
	        	
	        	uiModel.addAttribute("apMessage", "Cette Classe a deja ete cree");
	        	uiModel.addAttribute("classe", classe);
	            return "classes/create";
	        	
	        }
	        
	        
	        uiModel.asMap().clear();
	        classe.persist();
	        return "redirect:/classes/" + encodeUrlPathSegment(classe.getId().toString(), httpServletRequest);
	    }
	
	/* @RequestMapping(value = "copierclasse", method = RequestMethod.GET)
	    public String copierclasse(@RequestParam("id") Long id, Model uiModel) {
	        uiModel.addAttribute("classe", Classe.findClasse(id));
	        uiModel.addAttribute("itemId", id);
	        return "classes/show";
	    }*/
	 
	 @RequestMapping(value = "/copierclasse", method = RequestMethod.GET)
	    public String copierclasse4(@RequestParam("id") Long id, Model uiModel) {
		 
		 Long count = Classe.countClasses()+1;
		 
	        uiModel.addAttribute("classe", Classe.findClasse(id));
	        uiModel.addAttribute("itemId", count);
	        uiModel.addAttribute("myId", id);
	        return "classes/copierclasse";
	    }
	 
	 @RequestMapping(value = "/copierclasse/{id}", method = RequestMethod.GET)
	    public String copierclasse(@Valid Classe classe, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {
		 System.out.println(" uuu "+id+ "++"+classe.getId());
		 if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("classe", classe);
	            return "classes/copierclasse";
		 }
	        uiModel.asMap().clear();
	        classe.persist(); 
	        classe.flush();
	        
	        if (classe.isCopy_to_matiere() ){
	        	
	        	List<Matiere> listeMatiere = Matiere.getListeMatiere(Classe.findClasse(id));
	   	        
	   	        if (listeMatiere != null){
	   	        	
	   	        	for (Matiere matiere : listeMatiere) {
	   					
	   	        		Matiere mat = new Matiere();
	   	        		
	   	        		mat.setActif(matiere.getActif());
	   	        		mat.setClasse(classe);
	   	        		mat.setCoefficient(matiere.getCoefficient());
	   	        		mat.setCredit(matiere.getCredit());
	   	        		mat.setDisposition(matiere.getDisposition());
	   	        		mat.setFamille(matiere.getFamille());
	   	        		mat.setGroupe(matiere.getGroupe());
	   	        		mat.setIntitule(matiere.getIntitule());
	   	        		
	   	        		mat.persist();
	   	        		
	   	        		
	   				}
	   	        }
	        }
	        
	     

	        return "redirect:/classes/" + encodeUrlPathSegment(classe.getId().toString(), httpServletRequest);
	    }
	 
		@RequestMapping(value="/findClasseAjax", method = RequestMethod.GET)
		@ResponseBody
		public String findProductByCipAjax(Model uiModel ,  HttpServletRequest httpServletRequest) {
			 
			String des = httpServletRequest.getParameter("nom");
			 
			  
			List<Classe> resultList = Classe.findClasseByName(des);

			return Classe.toJsonArray(resultList);
		}
		
		@RequestMapping(value="/findClasseAjax/{cip}", method = RequestMethod.GET)
		@ResponseBody
		public String findProductApByCipAjax(@PathVariable("cip") Long cip, Model uiModel) {

			Classe produits = Classe.findClasse(cip);
			
			return produits.toJson();
		}
		
		@RequestMapping(value="/findMontantInscriptionClasseAjax/{cip}", method = RequestMethod.GET)
		@ResponseBody
		public String findProductApByCipAjax2(@PathVariable("cip") Long cip, Model uiModel) {
			System.out.println("TEST---------------------------TEST2----------");
			Classe produits = Classe.findClasse(cip);
			System.out.println("TEST---------------------------TEST2----------"+produits.toString());
			
			ConfigInscription config = new ConfigInscription();
			
			System.out.println("----------------"+config+"-------------------A");
			
			config = ConfigInscription.search(null, produits, null, null);
			
			System.out.println("----------------"+config+"-------------------B");
			
			BigDecimal montant = new BigDecimal(config.getMontant());
			
			return montant.toString();
		}
		
		
		 @RequestMapping(value = "/listeUE", method = RequestMethod.GET)
		    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
			 			 
		        uiModel.addAttribute("matieres", Matiere.getListeMatiere(Classe.findClasse(id)));
		     
		        return "matieres/list";
		    }
	
		 @RequestMapping(value = "/listemodules", method = RequestMethod.GET)
		    public String listemodules(@RequestParam("id") Long id, Model uiModel) {
			 	
		        uiModel.addAttribute("groupematieres", GroupeMatiere.findGroupeMatieresByClasse(Classe.findClasse(id)).getResultList());
		     
		        return "groupematieres/list";
		    }
		 
		 @RequestMapping(value = "/listeoccupations", method = RequestMethod.GET)
		    public String listeoccupations(@RequestParam("id") Long id, Model uiModel) {
			 
		        uiModel.addAttribute("occupations", Occupation.findOccupationsByClasse(Classe.findClasse(id)).getResultList());
		     
		        return "occupations/list";
		    }
	
}
