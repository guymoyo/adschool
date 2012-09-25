package adschool.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import adschool.domain.Evaluation;
import adschool.domain.Moyenneegenerales;
import adschool.domain.Moyenneevaluations;

@RooWebScaffold(path = "evaluations", formBackingObject = Evaluation.class, update = false)
@RequestMapping("/evaluations")
@Controller
public class EvaluationController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	
	
	
	 @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid Evaluation evaluation, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("evaluation", evaluation);
	            addDateTimeFormatPatterns(uiModel);
	            return "evaluations/create";
	        }
	        
	        List<Evaluation> listeEvalutation = Evaluation.findEvaluationsByPeriode(evaluation.getPeriode()).getResultList();
	        
	        double somme = 0;
	        
	        for (Evaluation ev : listeEvalutation) {
				
	        	somme = ev.getPourcentage();
			}
	        
	        if (somme + evaluation.getPourcentage() > 100 ){
	        	
	        	uiModel.addAttribute("evaluation", evaluation);
	        	uiModel.addAttribute("apMessage", "Verifier les pourcentages, somme des pourcentages de cette periode > 100 %");
	            addDateTimeFormatPatterns(uiModel);
	            return "evaluations/create";
	        	
	        }
	        
	        
	        uiModel.asMap().clear();
	        evaluation.persist();
	        return "redirect:/evaluations/" + encodeUrlPathSegment(evaluation.getId().toString(), httpServletRequest);
	    }
	
	
	
	
	 @RequestMapping(value = "/cloturer", method = RequestMethod.GET)
	    public String cloturer(@RequestParam("id") Long id, Model uiModel) {
	        
		 	Evaluation ev = Evaluation.findEvaluation(id);
		 
		 	ev.cloture();
		 	
		 	uiModel.addAttribute("apMessage", "Evaluation cloturee avec succes ");
            
	        uiModel.addAttribute("evaluation", ev);
	        uiModel.addAttribute("itemId", id);
	        return "evaluations/show";
	    }
}
