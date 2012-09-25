package adschool.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import adschool.domain.Genre;
import adschool.domain.Inscription;
import adschool.domain.PaiementPensions;
import adschool.domain.Parent;
import adschool.domain.Pays;
import adschool.domain.PensionEleves;
import adschool.services.JasperPrintService;


@RequestMapping("/etats")
@Controller
public class EtatsController {
	@Autowired
	JasperPrintService jasperPrintService ;
	
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/printRegistrationReceipt/{receiptId}.pdf", method = RequestMethod.GET)
	public @ResponseBody  void printRecuInscription(@PathVariable("receiptId") Long receiptId ,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
		  Map parameters = new HashMap();
		  parameters.put("inscriptionId",receiptId);
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RECUINSCRIPTION_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/rapportperiodiqueinscription", params = "find=ByDateInscriptionBetweenPdf",  method = RequestMethod.GET)
    public void findInscriptionsByDateInscriptionBetween(@RequestParam("minDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
        
		System.out.println(minDateInscription+" - "+maxDateInscription);
		
			Map parameters = new HashMap();
		  parameters.put("dateD", minDateInscription);
		  parameters.put("dateF", maxDateInscription);
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RAPPORT_PERIODIQUE_INSCRIPRION_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
    }
	
	@Produces({"application/pdf"})
	@RequestMapping(value = "/rapportperiodiquepaiement", params = "find=ByDateVersementBetweenPdf",  method = RequestMethod.GET)
    public void findPaiementByDatePensionBetween(@RequestParam("minDateVersement") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateVersement") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
        
			Map parameters = new HashMap();
		  parameters.put("dateD", minDateInscription);
		  parameters.put("dateF", maxDateInscription);
		try {
			
			jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RAPPORT_PERIODIQUE_PAIEMENT_FILE_PATH);
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		
    }
	
/*	@Produces({"application/pdf"})
	@RequestMapping(value = "/rapportperiodiqueinscription", params = "find=ByDateInscriptionBetweenPdf",  method = RequestMethod.GET)
    public void findPaiementByDateInscriptionBetween(@RequestParam("minDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date minDateInscription, @RequestParam("maxDateInscription") @DateTimeFormat(pattern = "yyyy-MM-dd") Date maxDateInscription,HttpServletRequest request,HttpServletResponse response, Model uiModel) {
        
			Map parameters = new HashMap();
		  parameters.put("dateD", minDateInscription);
		  parameters.put("dateF", maxDateInscription);
			try {
				
				jasperPrintService.printDocument(parameters, response, JasperPrintService.JASPER_RAPPORT_PERIODIQUE_INSCRIPRION_FILE_PATH);
			
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		
		
		
    }*/
	
	

	void addDateTimeFormatPatterns1(Model uiModel) {
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
