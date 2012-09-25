package adschool.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Produces;

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
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import adschool.beans.InscriptionProcessBean;
import adschool.domain.ConfigPension;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Genre;
import adschool.domain.Inscription;
import adschool.domain.PaiementPensions;
import adschool.domain.Parent;
import adschool.domain.Pays;
import adschool.domain.PensionEleves;
import adschool.security.SecurityUtil;
import adschool.services.JasperPrintService;
import adschool.utils.ProcessHelper;

@RooWebScaffold(path = "inscriptions", formBackingObject = Inscription.class)
@RequestMapping("/inscriptions")
@Controller
public class InscriptionController {
	@Autowired
	JasperPrintService jasperPrintService ;
	
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/printRegistrationReceipt/{id}.pdf", method = RequestMethod.GET)
	public @ResponseBody  void printRecuInscription(@PathVariable("id") Long receiptId ,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		  
		Map parameters = new HashMap();
		  parameters.put("inscriptionId",receiptId);
		  
		  Inscription inscription = Inscription.findInscription(receiptId);
		  
		  inscription.setImprimer(true);
		  
		  inscription.merge();
		  
		  
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RECUINSCRIPTION_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/printCertificatscolarite/{receiptId}.pdf", method = RequestMethod.GET)
	public @ResponseBody  void printCertificatscolarite(@PathVariable("receiptId") Long receiptId ,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		  Map parameters = new HashMap();
		  parameters.put("IdInscription",receiptId);
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_CERTIFICATSCOLARITE_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/printBadge/{receiptId}.pdf", method = RequestMethod.GET)
	public @ResponseBody  void printBadge(@PathVariable("receiptId") Long receiptId ,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		  Map parameters = new HashMap();
		  parameters.put("IdInscription",receiptId);
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_BADGE_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public String show(@PathVariable("id") Long id, Model uiModel) {
	        addDateTimeFormatPatterns(uiModel);
	        Inscription inscription = Inscription.findInscription(id);
	        uiModel.addAttribute("inscription",inscription );
	        uiModel.addAttribute("itemId", id);
	        uiModel.addAttribute("pensioneleveses", PensionEleves.findPensionElevesByInscription(inscription).getResultList());
	        return "inscriptions/show";
	    }




	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("inscriptionProcessBean", new InscriptionProcessBean());
		uiModel.addAttribute("inscription", new Inscription());
		uiModel.addAttribute("eleve", new Eleve());
		uiModel.addAttribute("parent", new Parent());
		uiModel.addAttribute("IdEleve", -1);
		addDateTimeFormatPatterns(uiModel);
		return "inscriptions/inscriptionprocessform";
	}
	
	@RequestMapping(value = "/inscrire", method = RequestMethod.GET)
	public String inscrire(@RequestParam("id") Long id, Model uiModel) {
		uiModel.addAttribute("inscriptionProcessBean", new InscriptionProcessBean());
		uiModel.addAttribute("inscription", new Inscription()); 
		uiModel.addAttribute("eleve", Eleve.findEleve(id));
		uiModel.addAttribute("parent", Eleve.findEleve(id).getParent());
		uiModel.addAttribute("IdEleve", id);
		addDateTimeFormatPatterns(uiModel);
		return "inscriptions/inscriptionprocessform";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid InscriptionProcessBean inscriptionBean,BindingResult bindingResult , Model uiModel, HttpServletRequest httpServletRequest) {
		
		//uiModel.asMap().clear();
		
		Inscription inscription = inscriptionBean.getInscription();
		
		Eleve eleve = Eleve.findEleve(inscriptionBean.getStudentId());
		
		
		Inscription findInscription = Inscription.findInscription(eleve, inscription.getAnnee());
		
		if ( findInscription != null ){
			
			uiModel.addAttribute("apMessage", "l'eleve/etudiant "+eleve.getFullName()+ " est deja inscrit dans l'etablissement "+findInscription.getEtablissement().getNomEtablissement()+" pour l'annee academique "+inscription.getAnnee().getLibelle() );			
			
			uiModel.addAttribute("inscriptionProcessBean", new InscriptionProcessBean());
			uiModel.addAttribute("inscription", new Inscription());
			uiModel.addAttribute("eleve", new Eleve());
			uiModel.addAttribute("parent", new Parent());
			addDateTimeFormatPatterns(uiModel);
			
			return "inscriptions/inscriptionprocessform";
			
		}
		

		if ( inscription.getAvance() < inscription.getMontantInscription() ){
			
			uiModel.addAttribute("apMessage", "Montant Avance < Montant Inscription" );			
			
			uiModel.addAttribute("inscriptionProcessBean", new InscriptionProcessBean());
			uiModel.addAttribute("inscription", new Inscription());
			uiModel.addAttribute("eleve", new Eleve());
			uiModel.addAttribute("parent", new Parent());
			addDateTimeFormatPatterns(uiModel);
			
			return "inscriptions/inscriptionprocessform";
			
		}
		

		inscription.setAvance(inscription.getMontantInscription());
		
		inscription.setSolder(true);
		
		inscription.setAgentSaisie(SecurityUtil.getUserName());
		
		inscription.setEleve(eleve);
		
		inscription.persist();
		
		
		// enregistrement des differentes de pensions preconfigurees par le superviseur
		List<ConfigPension> listeconfig = ConfigPension.search(inscription.getAnnee(), inscription.getClasse(), inscription.getRegime(), inscription.getEtablissement());
		
		for( ConfigPension config :listeconfig ){
			
			PensionEleves pension = new PensionEleves();
			pension.setAnnee(inscription.getAnnee());
			pension.setMontant(config.getMontant().intValue());
			pension.setAvance(0);
			pension.setClasse(inscription.getClasse());
			pension.setDateLimitePaiement(config.getDateLimitePaiement());
			pension.setInscriptionEleve(inscription);
		
			pension.setReduction(0);
			pension.setSolder(false);
			pension.setEleve(eleve);
			pension.setTranche(config.getTranche());
			pension.setRegime(inscription.getRegime());
			pension.persist();

		}
		addDateTimeFormatPatterns1(uiModel);
        return "redirect:/inscriptions/" + encodeUrlPathSegment(inscription.getId().toString(), httpServletRequest);

	}
	
	@RequestMapping(value="/findInscriptionAjax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String findInscriptionAjax(@PathVariable("id") Long cip, Model uiModel) {

		Inscription produits = Inscription.findInscription(cip);
		
		return produits.toJson();
	}
	




	@RequestMapping(value = "/createEleve", params = "form", method = RequestMethod.GET)
	public String createEleveForm(Model uiModel) {
		uiModel.addAttribute("eleve", new Eleve());
		addDateTimeFormatPatterns1(uiModel);
		return "inscriptions/createEleve";
	}

	@RequestMapping(value = "/createEleve", method = RequestMethod.POST)
	public String createEleve(@Valid Eleve eleve, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("eleve", eleve);
			addDateTimeFormatPatterns1(uiModel);
			return "inscriptions/createEleve";
		}
		uiModel.asMap().clear();
		eleve.persist();
		eleve.flush();
		Long id = eleve.getId();

		return "redirect:/inscriptions/createInscription/"+id;

	}

	@RequestMapping(value = "/createInscription/{id}")
	public String createFormN( @PathVariable("id") Long id, Model uiModel) {

		uiModel.addAttribute("inscription", new Inscription());

		addDateTimeFormatPatterns1(uiModel);

		List<Eleve> liste = new ArrayList<Eleve>();
		liste.add(Eleve.findEleve(id));

		uiModel.addAttribute("eleves",liste);
		uiModel.addAttribute("eleves",liste);
		return "inscriptions/createinscription";
	}
	
	@RequestMapping(value="/getStudentByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentByAjax(@RequestParam("Id") Long Id, Model uiModel ,  HttpServletRequest httpServletRequest) {
		
		return Eleve.findEleve(Id).toJson();
	}
	
	
	@RequestMapping(value = "/paiement/eleve", method = RequestMethod.GET)
	public String paie(@RequestParam("Id") Long id, Model uiModel ,  HttpServletRequest httpServletRequest) {
		
				
		Eleve eleve = Inscription.findInscription(id).getEleve();
		
		uiModel.addAttribute("inscription", Inscription.findInscription(id));
		
		uiModel.addAttribute("paiement", Inscription.findInscription(id));
		uiModel.addAttribute("paiementPensions", new PaiementPensions());
		
		uiModel.addAttribute("pensioneleveses", PensionEleves.findPensionElevesPayable(eleve).getResultList());
		
		addDateTimeFormatPatterns(uiModel);
		return "inscriptions/paiement";
	}
	
	
	 @RequestMapping(params = "find=ByDateInscriptionBetween2", method = RequestMethod.GET)
	    public String findInscriptionsByDateInscriptionBetween2(@RequestParam("minDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription, Model uiModel) {
	        
		 uiModel.addAttribute("dateD", minDateInscription);
		 uiModel.addAttribute("dateF", maxDateInscription);
		   
		 uiModel.addAttribute("inscriptions", Inscription.findInscriptionsByDateInscriptionBetween(minDateInscription, maxDateInscription).getResultList());
	        addDateTimeFormatPatterns(uiModel);
	        
	        return "inscriptions/list";
	    }
	 

	
	

	@ModelAttribute("genres")
	public Collection<Genre> populateGenres() {
		return Arrays.asList(Genre.class.getEnumConstants());
	}

	@ModelAttribute("parents")
	public Collection<Parent> populateParents() {
		return Parent.findAllParents();
	}

	@ModelAttribute("payses")
	public Collection<Pays> populatePayses() {
		return Pays.findAllPayses();
	}


	public JasperPrintService getJasperPrintService() {
		return jasperPrintService;
	}

	public void setJasperPrintService(JasperPrintService jasperPrintService) {
		this.jasperPrintService = jasperPrintService;
	}

	@ModelAttribute("economat")
	public String populateMenu() {
		return "block";
	}
	
	@ModelAttribute("etablissements")
	public Collection<Etablissement> populateEtab() {
		
		return ProcessHelper.getListeEtablissement();
	}

	void addDateTimeFormatPatterns1(Model uiModel) {//inscription_dateinscription_date_format  //inscription_datesaisie_date_format
		uiModel.addAttribute("inscription_dateinscription_date_format", "dd-MM-yyyy");
		uiModel.addAttribute("inscription_datesaisie_date_format", "dd-MM-yyyy");
		uiModel.addAttribute("inscription_datedemission_date_format", "dd-MM-yyyy");
		uiModel.addAttribute("inscription_dateexclusion_date_format", "dd-MM-yyyy");
		uiModel.addAttribute("eleve_datenaissance_date_format", "dd-MM-yyyy");
		uiModel.addAttribute("paiementPensions_dateversement_date_format", "dd-MM-yyyy");

	}

	String encodeUrlPathSegment1(String pathSegment, HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		}
		catch (UnsupportedEncodingException uee) {}
		return pathSegment;
	}

}
