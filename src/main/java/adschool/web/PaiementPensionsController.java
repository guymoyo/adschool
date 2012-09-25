package adschool.web;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Produces;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Inscription;
import adschool.domain.PaiementPensions;
import adschool.domain.PensionEleves;
import adschool.services.JasperPrintService;
import adschool.utils.ProcessHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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



@RooWebScaffold(path = "paiementpensionses", formBackingObject = PaiementPensions.class)
@RequestMapping("/paiementpensionses")
@Controller
public class PaiementPensionsController {
	
	@Autowired
	JasperPrintService jasperPrintService ;
	
	@ModelAttribute("economat")
    public String populateMenu() {
        return "block";
    }
	
	
	 
	 
	 
	 @RequestMapping(value="/getStudentByAjax", method = RequestMethod.GET)
		@ResponseBody
		public String getStudentByAjax(Model uiModel ,  HttpServletRequest httpServletRequest) {
			
			String Id = httpServletRequest.getParameter("Id");
			System.out.println(Id+"enter");
			
			return Eleve.findEleve(Long.parseLong(Id)).toJson();
		}
	 
	 
	 @RequestMapping(value= "/payerpension", method = RequestMethod.GET)
	    public String payerpension(@RequestParam("montantVersement") Long montant_versement, @RequestParam("IdInscription") Long IdInscription, Model uiModel, HttpServletRequest httpServletRequest) {
	
		 Eleve eleve = Inscription.findInscription(IdInscription).getEleve(); 	
		    
		 List<PaiementPensions> listePaiement = new ArrayList<PaiementPensions>();
		
	        List<PensionEleves> liste = PensionEleves.findPensionElevesPayable(eleve).getResultList();
	        
	        long reste = montant_versement; 
	        
	        int numero = 0;
	        
	        PensionEleves pension = new PensionEleves();
	        
	        while ( reste > 0 && numero < liste.size()){
	        	
	        	
	        	pension = liste.get(numero);
	        	
	        	int mont = pension.getNetAPayer(); 
	        	
	        	if ( reste > pension.getNetAPayer()){
	        		
	        		PaiementPensions paie = new PaiementPensions();
	        		
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(pension.getNetAPayer());
		        	paie.setMontantVersement(pension.getNetAPayer());
		        	
		        	paie.setPension(pension);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(pension.getNetAPayer());
		        	
		        	pension.setSolder(true);
		        	
		        	pension.merge();
		        	
		        	listePaiement.add(paie);
		        	
		        	reste -= mont;
		        	
	        	}else{
	        		
	        		PaiementPensions paie = new PaiementPensions();
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(pension.getNetAPayer());
		        	paie.setMontantVersement((int)reste);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.setPension(pension);
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(pension.getNetAPayer());
		        	
		        	boolean solder = pension.getNetAPayer()==0;
		        	
		        	pension.setSolder(solder);
		        	
		        	pension.merge();
	        		
		        	listePaiement.add(paie);
	        		
		        	reste -= mont;
		        	
	        	}
	        	// on incremente le numero de la pension ( on passe la pension suivante)
	        	numero++;
	    
	        	
	        }
	        	
        		uiModel.addAttribute("paiementpensionses", listePaiement);
        
	        return "paiementpensionses/list";
	   
	    }
	 
	 
	 @RequestMapping(value= "/payerpensionEleve", method = RequestMethod.GET)
	    public String payerpensionEleve(@RequestParam("montantVersement") Long montant_versement, @RequestParam("IdEleve") Long idELeve, Model uiModel, HttpServletRequest httpServletRequest) {
		 
		 Eleve eleve = Eleve.findEleve(idELeve);
		    
	     List<PaiementPensions> listePaiement = new ArrayList<PaiementPensions>();
		
	        List<PensionEleves> liste = PensionEleves.findPensionElevesPayable(eleve).getResultList();
	     
	        long reste = montant_versement; 
	        
	        int numero = 0;
	        
	        if( montant_versement <= 0){
	        	
	        	uiModel.addAttribute("apMessage"," Merci de saisir un montant valide");
				
				uiModel.addAttribute("eleve",eleve);
				
				uiModel.addAttribute("pensioneleveses",liste);
				
				return  "paiementpensionses/paiementAfter";
		        
	        	
	        }
	        

	        if( liste == null || liste.isEmpty() ){
	        	
	        	uiModel.addAttribute("apMessage"," Cet etudiant ne possede pas de tranche de pension impayees");
				
				uiModel.addAttribute("eleve",eleve);
				
				uiModel.addAttribute("pensioneleveses",liste);
				
				return  "paiementpensionses/paiementAfter";
		        
	        	
	        }
	        
	        PensionEleves pension = new PensionEleves();
	        
	        while ( reste > 0 && numero < liste.size()){
	        	
	        	
	        	pension = liste.get(numero);
	        	
	        	int mont = pension.getNetAPayer(); 
	        	
	        	if ( reste > pension.getNetAPayer()){
	        		
	        		PaiementPensions paie = new PaiementPensions();
	        		
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(pension.getNetAPayer());
		        	paie.setMontantVersement(pension.getNetAPayer());
		        	
		        	paie.setPension(pension);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(pension.getNetAPayer());
		        	
		        	pension.setSolder(true);
		        	
		        	pension.merge();
		        	
		        	listePaiement.add(paie);
		        	
		        	reste -= mont;
		        	
	        	}else{
	        		
	        		PaiementPensions paie = new PaiementPensions();
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(pension.getNetAPayer());
		        	paie.setMontantVersement((int)reste);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.setPension(pension);
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(pension.getNetAPayer());
		        	
		        	boolean solder = pension.getNetAPayer()==0;
		        	
		        	pension.setSolder(solder);
		        	
		        	pension.merge();
	        		
		        	listePaiement.add(paie);
	        		
		        	reste -= mont;
		        	
	        	}
	        	// on incremente le numero de la pension ( on passe la pension suivante)
	        	numero++;
	    
	        	
	        }
	        	
     		uiModel.addAttribute("paiementpensionses", listePaiement);
     
	        return "paiementpensionses/list";
	   
	    }
	 
	 
	 
	 @RequestMapping(value= "/payerOnepension", method = RequestMethod.GET)
	    public String payerOnepension(@RequestParam("montantVersement") Long montant_versement, @RequestParam("IdPension") Long Idpension, Model uiModel, HttpServletRequest httpServletRequest) {
		 
		 PaiementPensions paie = new PaiementPensions();
	     
		 PensionEleves pension = PensionEleves.findPensionEleves(Idpension);
		 
		 int trancheCourante = pension.getTranche(); 
		 
		 List<PensionEleves> listePension = PensionEleves.findPensionElevesByInscription(pension.getInscriptionEleve()).getResultList();
	        	
		 
		 for (PensionEleves pensionEleves : listePension) {
			
			 if ( pensionEleves.getNetAPayer() > 0 && pensionEleves.getTranche() < trancheCourante){
				 
				 uiModel.addAttribute("appMessage", "Merci de commencer par encaisser les tranches dont les Numeros sont inferieures a "+trancheCourante);
	        		
				 uiModel.addAttribute("pensioneleves", PensionEleves.findPensionEleves(Idpension));
	        	
				 return "pensioneleveses/PaiementOnePension";
			 }
		}
		 
		 
	        	int mont = pension.getNetAPayer(); 
	        	
	        	if( mont <= 0){
	        		
	        		uiModel.addAttribute("appMessage", "Cette tranche a deja ete soldee");
	        		uiModel.addAttribute("pensioneleves", PensionEleves.findPensionEleves(Idpension));
	        		
	        		return "pensioneleveses/PaiementOnePension";
	        	}
	        	    	
	        	if ( montant_versement > mont){
	        		
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(mont);
		        	paie.setMontantVersement(mont);
		        	
		        	paie.setPension(pension);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(montant_versement.intValue());
		        	
		        	pension.setSolder(true);
		        	
		        	pension.merge();
		        	
		        	
	        	}else{
	     
	        		paie.setDateVersement(new Date());
		        	paie.setMontantTranche(mont);
		        	paie.setMontantVersement(montant_versement.intValue());
		        	
		        	paie.setPension(pension);
		        	
		        	paie.setInscriptionEleve(pension.getInscriptionEleve());
		        	
		        	paie.persist();
		        	
		        	paie.flush();
		        	
		        	pension.avancer(montant_versement.intValue());
		        	
		        	boolean solder = pension.getNetAPayer()==0;
		        	
		        	pension.setSolder(solder);
		        	
		        	pension.merge();
	            	
	        	}
	       
	      	uiModel.addAttribute("paiementpensions", paie);
	        return "paiementpensionses/show";
	   
	    }
	
	
	
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/printRecuPaiement/{IdVersement}.pdf", method = RequestMethod.GET)
	public @ResponseBody  void printRegistrationReceipt(@PathVariable("IdVersement") Long IdVersement ,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		
		Map parameters = new HashMap();
		
		parameters.put("IdVersement",IdVersement);
		
		PaiementPensions paie = PaiementPensions.findPaiementPensions(IdVersement);
		
		paie.setImprimer(true);
		
		paie.merge();
		
		try {
			File file = new File(JasperPrintService.JASPER_RECUPAIEMENT_FILE_PATH);
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RECUPAIEMENT_FILE_PATH);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping( value = "/print")
	public String printRecu(@RequestParam("id") Long id) {
		
		return "redirect:/paiementpensionses/printRecuPaiement/"+id+".pdf";
		
	}
	

	
	@RequestMapping(params = { "find=ByDateVersementBetweenPdf" }, method = RequestMethod.GET)
	    public String printPaiementPensionsesByDateVersementBetweenForm(Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        return "paiementpensionses/printPaiementPensionsesByDateVersementBetween";
	    }
	
	@RequestMapping(value = "/listingbyclasse", params = { "form" }, method = RequestMethod.GET)
    public String linstingpaiement(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        return "paiementpensionses/findPaiementPension";
    }


	

@RequestMapping(params = { "find=ByPension" }, method = RequestMethod.GET)
public String myTest(@RequestParam("etablissement") Long etablissement, @RequestParam("annee") Long annee, @RequestParam("classe") Long classe,Model uiModel) {
   
    
    List<PaiementPensions> listeP = PaiementPensions.getPaiementClasse(AnneeScolaire.findAnneeScolaire(annee), Classe.findClasse(classe)).getResultList();
    
    
    uiModel.addAttribute("paiementpensionses", listeP);
    
    return "paiementpensionses/list";
}


	
@ModelAttribute("classes")
public Collection<Classe> populateClasse() {
		
		return Classe.findAllClasses();
}


@ModelAttribute("anneescolaires")
public Collection<AnneeScolaire> populateAnnee() {
		
		List<AnneeScolaire> l = new ArrayList<AnneeScolaire>();
		
		l.add(ProcessHelper.getAnneeCourante());
		
		return l;
}

@ModelAttribute("etablissement")
public Collection<Etablissement> populateetablissement() {
										
		return ProcessHelper.getListeEtablissement();
}
	
			

			void addDateTimeFormatPatterns2(Model uiModel) {
			 
				uiModel.addAttribute("paiementPensions_dateversement_date_format", "dd-MM-yyyy");
			}
}
