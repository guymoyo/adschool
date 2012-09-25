package adschool.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Evaluation;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import adschool.domain.Notes;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RooWebScaffold(path = "matieres", formBackingObject = Matiere.class)
@RequestMapping("/matieres")
@Controller
public class MatiereController {
	
	@ModelAttribute("maintenance")
    public String populateMenu() {
        return "block";
    }
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/getListeMatiereClasseByAjax/{idclasse}", method = RequestMethod.GET)
	@ResponseBody
	public String getListeMatiereClasseByAjax(@PathVariable("idclasse") Long idclasse, Model uiModel) {
		
		List<Matiere> matiere = Matiere.getListeMatiere(Classe.findClasse(idclasse));
		
		return Matiere.toJsonArray(matiere);
	}
	
	
	
	@RequestMapping(value="/findMatiereAjax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String findMatiereAjax(@PathVariable("id") Long cip, Model uiModel) {
		
		Matiere matiere = Matiere.findMatiere(cip);
		
		return matiere.toJson();
	}

	
	
	
	
	
	@RequestMapping(value="/getNextMatiereByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getNextMatiereByAjax(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long matiere = Long.parseLong(httpServletRequest.getParameter("matiere"));
		
		Matiere mat = Matiere.findMatiere(matiere);
		
		Classe classe = mat.getClasse();
		
		Matiere matNext = new Matiere();
		
		List<Matiere> listeMatiere = Matiere.getListeMatiere(classe);
		
		for (int i=0; i< listeMatiere.size(); i++){
			
			if ( listeMatiere.get(i).getId() == mat.getId()){
				
				if( i < (listeMatiere.size()-1)){
					
					matNext = listeMatiere.get(i+1);
					
				}else{
					
					matNext = null;
				}
				 
			}
			
		}
		
		System.out.println("Next - "+matNext);
		
		
		return  matNext != null ? matNext.toJson() : null;
	}
	
	
	@RequestMapping(value="/getPreviousMatiereByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getPreviousMatiereByAjax(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long matiere = Long.parseLong(httpServletRequest.getParameter("matiere"));
		
		Matiere mat = Matiere.findMatiere(matiere);
		
		Classe classe = mat.getClasse();
		
		Matiere matNext = new Matiere();
		
		List<Matiere> listeMatiere = Matiere.getListeMatiere(classe);
		
		for (int i=0; i< listeMatiere.size(); i++){
			
			if ( listeMatiere.get(i).getId() == mat.getId()){
				
				if( i > 0){
					
					matNext = listeMatiere.get(i-1);
					
				}else{
					
					matNext = null;
				}
				 
			}
			
		}
		
		
		
		return  matNext != null ? matNext.toJson() : null;
	}

	
	
	
	 @RequestMapping(params = "find=ByClasse", method = RequestMethod.GET)
	    public String findMatieresByClasse(@RequestParam("classe") Classe classe, Model uiModel) {
	        uiModel.addAttribute("matieres", Matiere.getListeMatiere(classe));
	        return "matieres/list";
	    }
	
	
	
}
