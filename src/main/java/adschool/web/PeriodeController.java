package adschool.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.w3c.dom.ls.LSInput;

import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Evaluation;
import adschool.domain.Filiere;
import adschool.domain.PaiementPensions;
import adschool.domain.Periode;

@RooWebScaffold(path = "periodes", formBackingObject = Periode.class)
@RequestMapping("/periodes")
@Controller
public class PeriodeController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	   @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid Periode periode, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("periode", periode);
	            addDateTimeFormatPatterns2(uiModel);
	            return "periodes/create";
	        }
	        
//	        List<Evaluation> listeEvalutaion = Evaluation.findEvaluationsByPeriode(periode).getResultList();
//	        
//	        for (Evaluation evaluation : listeEvalutaion) {
//				
//	        	double somme = evaluation.
//			}
//	        
//	        
//	        
//	        
//	        
//	        
	        
	        uiModel.asMap().clear();
	        periode.persist();
	        return "redirect:/periodes/" + encodeUrlPathSegment2(periode.getId().toString(), httpServletRequest);
	    }
	   
	   
	   @RequestMapping(value= "/cloturer", method = RequestMethod.GET)
	    public String cloturer(@RequestParam("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
		 
		   Periode findPeriode = Periode.findPeriode(id);
		   
		   findPeriode.cloturer();
		   
		   return "periodes/show";
	   }
	   
	   @RequestMapping(value = "/listeExamens", method = RequestMethod.GET)
	    public String listeUE(@RequestParam("id") Long id, Model uiModel) {
		 
	        uiModel.addAttribute("evaluations", Evaluation.findEvaluationsByPeriode(Periode.findPeriode(id)).getResultList());
	     
	        return "evaluations/list";
	    }
	   
	   
	   
	   
	   
	   void addDateTimeFormatPatterns2(Model uiModel) {
	        uiModel.addAttribute("periode_datedebut_date_format", "dd-MM-yyyy");
	        uiModel.addAttribute("periode_datefin_date_format", "dd-MM-yyyy");
	    }
	   
	   
	   String encodeUrlPathSegment2(String pathSegment, HttpServletRequest httpServletRequest) {
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
