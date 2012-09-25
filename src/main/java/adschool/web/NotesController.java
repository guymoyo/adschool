package adschool.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import adschool.beans.SaisieNoteProcessBean;
import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Evaluation;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import adschool.domain.Notes;
import adschool.security.SecurityUtil;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RooWebScaffold(path = "noteses", formBackingObject = Notes.class)
@RequestMapping("/noteses")
@Controller
public class NotesController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
	
	@RequestMapping(value = "/config", params = "form", method = RequestMethod.GET)
	public String createForm5(Model uiModel) {
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
		
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteEleveStep1"; 
	}
	
	

	@RequestMapping(value = "/saisieNoteEleveStep1", params = "find=ByEleve", method = RequestMethod.GET)
	public String saisieNoteClasseStep2(@RequestParam("etablissement") Etablissement etablissement,@RequestParam("anneescolaire") AnneeScolaire annee,
			@RequestParam("classe") Classe classe,@RequestParam("evaluation") Evaluation evaluation,Model uiModel,HttpServletRequest httpServletRequest) {
		
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
		
		HttpSession session = httpServletRequest.getSession();
		
		session.setAttribute("anneescolaire", annee);
		
		
		session.setAttribute("etablissement", etablissement);
				
		session.setAttribute("classe", classe);
		
		
		session.setAttribute("evaluation", evaluation);
		
		uiModel.addAttribute("eleves", Inscription.findInscription(classe, annee, etablissement));
		
		
		
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteEleveStep2"; 
	}
	
	
	
	@RequestMapping(value = "/saisieNoteEleveStep2", params = "find=ByEleve", method = RequestMethod.GET)
	public String saisieNoteEleveStep3(@RequestParam("eleve") Eleve eleve,Model uiModel,HttpServletRequest httpServletRequest) {
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
		

		HttpSession session = httpServletRequest.getSession();
		
		uiModel.addAttribute("anneescolaire", (AnneeScolaire) session.getAttribute("anneescolaire"));
		
		uiModel.addAttribute("classe", (Classe) session.getAttribute("classe"));
		
		uiModel.addAttribute("inscription",  Inscription.findInscription(eleve, (AnneeScolaire) session.getAttribute("anneescolaire")));
		
		uiModel.addAttribute("evaluation", Evaluation.getCurrentEvaluation());
		
		uiModel.addAttribute("etablissement", (Etablissement) session.getAttribute("etablissement"));
		
		
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteClasse3"; 
	}
	
	
	@RequestMapping(value = "/config2", params = "form", method = RequestMethod.GET)
	public String createForm51(Model uiModel) {
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
				
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteClasseStep1"; 
	}
	
	
	@RequestMapping(value = "/saisieNoteClasseStep1", params = "find=ByClasse", method = RequestMethod.GET)
	public String saisieNoteClasseStep2(@RequestParam("etablissement") Etablissement etablissement,@RequestParam("anneescolaire") AnneeScolaire annee,
			@RequestParam("classe") Classe classe,@RequestParam("matiere") Matiere matiere,@RequestParam("evaluation") Evaluation evaluation,Model uiModel) {
		
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
		
		uiModel.addAttribute("anneescolaire",annee);
		
		uiModel.addAttribute("etablissement", etablissement);
				
		uiModel.addAttribute("classe", classe);
		
		uiModel.addAttribute("matiere", matiere);
		
		uiModel.addAttribute("evaluation", evaluation);
		
		
		
		
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteClasseStep2"; 
	}
	
	
	@RequestMapping(value = "/config3", params = "form", method = RequestMethod.GET)
	public String createForm3(Model uiModel) {
		uiModel.addAttribute("saisieNoteProcessBeans", new ArrayList<SaisieNoteProcessBean>());
		uiModel.addAttribute("saisieNoteProcessBean", new SaisieNoteProcessBean());
		
		uiModel.addAttribute("anneescolaire",AnneeScolaire.getCurrentAnneeScolaire());
		
		uiModel.addAttribute("classe", Classe.findClasse(new Long(1)));
		
		uiModel.addAttribute("inscription", Inscription.findInscription(new Long(9)));
		
		uiModel.addAttribute("evaluation", Evaluation.getCurrentEvaluation());
		
		uiModel.addAttribute("etablissement", Etablissement.findEtablissement(new Long(1)));
		
		addDateTimeFormatPatterns(uiModel);
		return "noteses/saisieNoteClasse3"; 
	}
	
	
	@RequestMapping(value = "/saisieNote", method = RequestMethod.POST)
	public String saisieNoteEtape2(@Valid SaisieNoteProcessBean matiere, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		
		  if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("saisieNoteProcessBean", matiere);
	            return "matieres/create";
		  }
		
		Notes note = new Notes();
			
		  
		uiModel.addAttribute("eleves", Inscription.findInscription(matiere.getClasse(), matiere.getAnneeScolaire(), matiere.getEtablissement()));
		
		uiModel.addAttribute("note", note);
		
		addDateTimeFormatPatterns(uiModel);
		
		return "noteses/saisieNoteClasse"; 
	}
	

	@RequestMapping(value="/getListeNoteClasseByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getListeNoteClasse(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long matiere = Long.parseLong(httpServletRequest.getParameter("matiere"));
		 
		long evaluation = Long.parseLong(httpServletRequest.getParameter("evaluation"));
		
		List<Notes> listeN = Notes.getListeNoteClasse(Matiere.findMatiere(matiere), Evaluation.findEvaluation(evaluation)).getResultList();
	
		return Notes.toJsonArray(listeN);
	}
	
	
	@RequestMapping(value="/getListeNoteEleveByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getListeNoteEleve(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long inscription = Long.parseLong(httpServletRequest.getParameter("inscription"));
		 
		long evaluation = Long.parseLong(httpServletRequest.getParameter("evaluation"));
		
		List<Notes> listeN = Notes.getListeNoteEleve(Inscription.findInscription(inscription), Evaluation.findEvaluation(evaluation)).getResultList();
		
		return Notes.toJsonArray(listeN);
	}

	


	@RequestMapping(value="/deleteNoteClasseByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String deleteNoteByAjax(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long idNote = Long.parseLong(httpServletRequest.getParameter("idNote"));
		 
		Notes.findNotes(idNote).remove();
		
		long evaluation = Long.parseLong(httpServletRequest.getParameter("evaluation"));
		
		long matiere = Long.parseLong(httpServletRequest.getParameter("matiere"));
		 
		
		List<Notes> listeN = Notes.getListeNoteClasse(Matiere.findMatiere(matiere), Evaluation.findEvaluation(evaluation)).getResultList();
	
		return Notes.toJsonArray(listeN);
	}
	
	
	@RequestMapping(value="/deleteNoteEleveByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String deleteNoteEleveByAjax(@RequestParam(value = "idNote") long idNote, @RequestParam(value = "evaluation") long evaluation, @RequestParam(value = "inscription") long inscription, Model uiModel, HttpServletRequest httpServletRequest) {
		 
		Notes.findNotes(idNote).remove();
		
		List<Notes> listeN = Notes.getListeNoteEleve(Inscription.findInscription(inscription), Evaluation.findEvaluation(evaluation)).getResultList();
		
		return Notes.toJsonArray(listeN);
	}

		
	
	@RequestMapping(value="/saveNoteEleveByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String saveNoteEleveByAjax(@RequestParam(value = "matiere") long matiere, @RequestParam(value = "evaluation") long evaluation, @RequestParam(value = "inscription") long inscription, @RequestParam(value = "noteEtudiant") long value, Model uiModel, HttpServletRequest httpServletRequest) {
		
		Notes note;
		
		Matiere mat = Matiere.findMatiere(matiere);
		
		Evaluation ev = Evaluation.findEvaluation(evaluation);
		
		Inscription inscript = Inscription.findInscription(inscription);
		
		Eleve eleve = inscript.getEleve();
		
		note = Notes.findNote(mat, ev, inscript);
		
		if ( value > 20){
			
			return null;
		}
		
		

		if ( note == null ){
		
			note = new Notes();
			
			note.setCoefficient(mat.getCoefficient());
			
			note.setMatiere(mat);
			
			note.setDateSaisie(new Date());
			
			note.setEleve(eleve);
			
			note.setInscripionEleve(inscript);
			
			note.setEvaluation(ev);
			
			note.setPourcentage(ev.getPourcentage());
			
			note.setValeur(value);
			
			note.setEtablissement(ev.getEtablissement());
			
			note.setAgentSaisie(SecurityUtil.getUserName());
			
			note.persist();
			
			
		}else{
		
			note.setCoefficient(mat.getCoefficient());
			
			note.setMatiere(mat);
			
			note.setEleve(eleve);
			
			note.setInscripionEleve(inscript);
			
			note.setEvaluation(ev);
			
			note.setPourcentage(ev.getPourcentage());
			
			note.setValeur(value);
			
			note.setEtablissement(ev.getEtablissement());
			
			note.setDateUpdate(new Date());
			
			note.setAgentUpdate(SecurityUtil.getUserName());
			
			
			note.merge();
			
			
		}
		
		
		List<Notes> listeN = Notes.getListeNoteEleve(Inscription.findInscription(inscription), Evaluation.findEvaluation(evaluation)).getResultList();
		
		return Notes.toJsonArray(listeN);
	
	
	}
	
	
	
	@RequestMapping(value="/saveNoteClasseByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String saveNoteClasseByAjax(@RequestParam(value = "matiere") long matiere, @RequestParam(value = "evaluation") long evaluation, @RequestParam(value = "inscription") long inscription, @RequestParam(value = "noteEtudiant") long value, Model uiModel, HttpServletRequest httpServletRequest) {
		
		Notes note;
		
		Matiere mat = Matiere.findMatiere(matiere);
		
		Evaluation ev = Evaluation.findEvaluation(evaluation);
		
		Inscription inscript = Inscription.findInscription(inscription);
		
		Eleve eleve = inscript.getEleve();
		
		note = Notes.findNote(mat, ev, inscript);
		

		if ( value > 20){
			
			return null;
		}
		
		

		if ( note == null ){
		
			note = new Notes();
			
			note.setCoefficient(mat.getCoefficient());
			
			note.setMatiere(mat);
			
			note.setDateSaisie(new Date());
			
			note.setEleve(eleve);
			
			note.setInscripionEleve(inscript);
			
			note.setEvaluation(ev);
			
			note.setPourcentage(ev.getPourcentage());
			
			note.setValeur(value);
			
			note.setEtablissement(ev.getEtablissement());
			
			note.setAgentSaisie(SecurityUtil.getUserName());
			
			note.persist();
			
			
		}else{
		
			note.setCoefficient(mat.getCoefficient());
			
			note.setMatiere(mat);
			
			note.setEleve(eleve);
			
			note.setInscripionEleve(inscript);
			
			note.setEvaluation(ev);
			
			note.setPourcentage(ev.getPourcentage());
			
			note.setValeur(value);
			
			note.setEtablissement(ev.getEtablissement());
			
			note.setDateUpdate(new Date());
			
			note.setAgentUpdate(SecurityUtil.getUserName());
			
			
			note.merge();
			
			
		}
		
		List<Notes> listeN = Notes.getListeNoteClasse(Matiere.findMatiere(matiere), Evaluation.findEvaluation(evaluation)).getResultList();
		
		System.out.println("Taille - : "+listeN.size());
		
		return Notes.toJsonArray(listeN);
	
	
	}
	
	
	
	/* @ModelAttribute("inscriptions")
	    public Collection<Inscription> NotesController.populateInscriptions() {
	        return Inscription.findAllInscriptions();
	    }
	*/
	
	
	 @ModelAttribute("anneescolaires")
	    public Collection<AnneeScolaire> populateEleves() {
	     
		 List<AnneeScolaire> list = new ArrayList<AnneeScolaire>();
		 
		 list.add(AnneeScolaire.getCurrentAnneeScolaire());
		 
		 return list;
	    }
	    
	    @ModelAttribute("classes")
	    public Collection<Classe> populateClasses() {
	        return Classe.findAllClasses();
	    }
}
