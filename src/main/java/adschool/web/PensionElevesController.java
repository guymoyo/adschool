package adschool.web;


import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import adschool.domain.PaiementPensions;
import adschool.domain.PensionEleves;

@RooWebScaffold(path = "pensioneleveses", formBackingObject = PensionEleves.class)
@RequestMapping("/pensioneleveses")
@Controller
public class PensionElevesController {
	
	@ModelAttribute("economat")
    public String populateMenu() {
        return "block";
    }
	
	 @RequestMapping(params = { "find=ByNomLike", "form" }, method = RequestMethod.GET)
	    public String findPensionElevesesByEleveForm2(Model uiModel) {
	        
	        return "pensioneleveses/findElevesByNomLike";
	    }
	 
	
	 @RequestMapping(value = "/payerpension", method = RequestMethod.GET)
	    public String paiementpension(@RequestParam("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        PensionEleves pension = PensionEleves.findPensionEleves(id);
	        uiModel.addAttribute("pensioneleves", pension);
	        uiModel.addAttribute("itemId", id);
	        
	        if( pension.getNetAPayer() == 0){
		        
		        uiModel.addAttribute("apMessage","Operation impossible sur cette tranche de pension : Pension deja soldee");
		        
	        }
	        
	        
	        return "pensioneleveses/PaiementOnePension";
	    }
	 
	 @RequestMapping(value = "/listingpaiementpension", method = RequestMethod.GET)
	    public String listingpaiement(@RequestParam("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        uiModel.addAttribute("paiementpensionses", PaiementPensions.getPaiementOnePension(PensionEleves.findPensionEleves(id)).getResultList());
	        uiModel.addAttribute("itemId", id);
	        return "paiementpensionses/list";
	    }
	 
	 
	 
	 @RequestMapping(value = "/reduction", method = RequestMethod.GET)
	    public String reduction(@RequestParam("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        uiModel.addAttribute("itemId", id);
	        PensionEleves pension = PensionEleves.findPensionEleves(id);
	        uiModel.addAttribute("pensioneleves", pension);
	       
	        if( pension.getNetAPayer() == 0){
	        
		        uiModel.addAttribute("apMessage","Impossible d'effectuer une reduction sur cette pension : pension deja soldee");
		        
		        uiModel.addAttribute("show",false);
		        
	        }else{
	        	
	        	 uiModel.addAttribute("show",true);
	        }
	        
	        return "pensioneleveses/accorderReductionPension";
	    }
	 
	 
	 @RequestMapping( value = "/moratoire", method = RequestMethod.GET)
	    public String moratoire(@RequestParam("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        uiModel.addAttribute("itemId", id);
	        PensionEleves pension = PensionEleves.findPensionEleves(id);
	        uiModel.addAttribute("pensioneleves", pension);
	       
	        if( pension.getNetAPayer() == 0){
	        
		        uiModel.addAttribute("apMessage","Impossible d'accorder un moratoire a cette tranche : pension deja soldee");
		        
		        uiModel.addAttribute("show",false);
		        
	        }else{
	        	
	        	 uiModel.addAttribute("show",true);
	        }
	        
	        return "pensioneleveses/accorderMoratoirePension";
	    }
	 
	 @RequestMapping(value= "/reduire", method = RequestMethod.POST)
	    public String reduire(@RequestParam("montantReduction") Long reduction, @RequestParam("IdPension") Long IdPension , Model uiModel, HttpServletRequest httpServletRequest) {
		 
		 
		 PensionEleves pension = PensionEleves.findPensionEleves(IdPension);
		 
		 uiModel.addAttribute("pensioneleves", pension);
		 
		 if( pension.getNetAPayer() == 0){
		        
		        uiModel.addAttribute("apMessage","Impossible d'effectuer une reduction sur cette pension : pension deja soldee");
		        
		        uiModel.addAttribute("show",true);
		        
		        return "pensioneleveses/accorderReductionPension";
		        
		 }
	      
		 
		 if( reduction > pension.getNetAPayer()){
			 
			 uiModel.addAttribute("apMessage","Impossible d'effectuer une reduction sur cette pension : Montant Reduction > Montant Net a Payer");
			
			 uiModel.addAttribute("show",true);
		        
		     return "pensioneleveses/accorderReductionPension";
		 }
		 
		 pension.setReduction(reduction.intValue());
		 
		 pension.merge();
		 
		 pension.flush();
		 
		 
		 uiModel.addAttribute("pensioneleves", pension);
		 
		 return "pensioneleveses/show";
		 
	 }
	 
	
	 @RequestMapping(value= "/accordermoratoire", method = RequestMethod.GET)
	    public String moratoire(@RequestParam("NombreJour") Long day, @RequestParam("IdPension") Long IdPension , Model uiModel, HttpServletRequest httpServletRequest) {
		 
		 PensionEleves pension = PensionEleves.findPensionEleves(IdPension);
		 
		 uiModel.addAttribute("pensioneleves", pension);
		 
		 if( pension.getNetAPayer() == 0){
		        
		        uiModel.addAttribute("apMessage","Impossible d'accorder un moratoire a cette tranche : pension deja soldee");
		        
		        uiModel.addAttribute("show",true);
		        
		        return "pensioneleveses/accorderMoratoirePension";
		        
		 }
	      
		 
		 if( day < 1){
			 
			 uiModel.addAttribute("apMessage","Impossible d'accorder un moratoire a cette tranche : Nombre de jour Invalide");
			
			 uiModel.addAttribute("show",true);
		        
		     return "pensioneleveses/accorderMoratoirePension";
		 }
		 
		 pension.setDateLimitePaiement(DateUtils.addHours(pension.getDateLimitePaiement(), day.intValue()));
		 
		 pension.merge();
		 
		 pension.flush();
		 
		 
		 uiModel.addAttribute("pensioneleves", pension);
		 
		 return "pensioneleveses/show";
		 
	 }
	 
	
	   
	    @ModelAttribute("pensioneleveses")
	    public Collection<PensionEleves> populatePensionEleveses() {
	        return PensionEleves.getListepension();
	    }
	    
	    @RequestMapping(method = RequestMethod.GET)
	    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
	        if (page != null || size != null) {
	            int sizeNo = size == null ? 10 : size.intValue();
	            uiModel.addAttribute("pensioneleveses", findPensionElevesEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
	            float nrOfPages = (float) PensionEleves.countPensionEleveses() / sizeNo;
	            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
	        } else {
	            uiModel.addAttribute("pensioneleveses", PensionEleves.getListepension());
	        }
	        addDateTimeFormatPatterns(uiModel);
	        return "pensioneleveses/list";
	    }
	    
	    public static List<PensionEleves> findPensionElevesEntries(int firstResult, int maxResults) {
	    	EntityManager em = PensionEleves.entityManager();
	    	
	    	return em.createQuery("SELECT o FROM PensionEleves AS o Order By o.annee, o.eleve.matricule, o.eleve.nom, o.tranche", PensionEleves.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	    }
	 
	    
}
