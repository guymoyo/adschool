package adschool.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Evaluation;
import adschool.domain.Genre;
import adschool.domain.ImportNote;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import adschool.domain.Notes;
import adschool.domain.Parent;
import adschool.domain.Pays;
import adschool.security.SecurityUtil;
import adschool.utils.FileUploadService;

@RooWebScaffold(path = "importnotes", formBackingObject = ImportNote.class)
@RequestMapping("/importnotes")
@Controller
public class ImportNoteController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }

	String matricule_text = "MATRICULE";
	String noms_text = "NOMS";
	String note_text = "NOTE";

	String[] listeSheet;

	String[] liste_colonne;

	boolean load_data = false;

	Notes note;

	@RequestMapping(value = "/importerStep1", method = RequestMethod.POST)
	public String importer(@Valid ImportNote importNote,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {


		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("importNote", importNote);
			return "importnotes/create";
		}

		
		uiModel.asMap().clear();
		

		MultipartFile file = importNote.getFichier();

		String filePath = "";

		try {

			filePath = FileUploadService.storeFileExcel(file, "excel", RandomStringUtils.randomAlphabetic(6));

			
			if (filePath == null) {

			} else {

				importNote.setChemin(filePath);

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		uiModel.asMap().clear();

		List<Notes> li = lire(filePath, importNote);

		HttpSession session = httpServletRequest.getSession();
		
		session.setAttribute("noteses", li);
		

		return "noteses/importNote";
	}
	
	
	
	@RequestMapping(value = "/importerStep2")
	public String importer2(Model uiModel, HttpServletRequest httpServletRequest) {
		
		HttpSession session = httpServletRequest.getSession();
		
		List<Notes> attribute = (List<Notes>) session.getAttribute("noteses");
		 
		for(Notes n : attribute){
			 
			 Notes note = Notes.findNote(n.getMatiere(), n.getEvaluation(), n.getInscripionEleve());
		
			 if ( note == null ){
				 
				 n.persist();
				 
			 }else{
				 	
				 note.setMatiere(n.getMatiere());
					
				 note.setValeur(n.getValeur());
					
				 note.setDateUpdate(new Date());
					
				 note.setAgentUpdate(SecurityUtil.getUserName());
				 
				 note.merge();
				
			 }
			 
		}
		
		uiModel.addAttribute("apMessage", "Importation effectue avec succes");

		return "noteses/importNote";
	}

	public List<Notes> lire(String chemin, ImportNote importeNote) {

		Workbook workbook = null;

		List<Notes> listeNotes = new ArrayList<Notes>();

		try {

			// Recuperation du classeur Excel (en lecture)

			workbook = Workbook.getWorkbook(new File(chemin));

			System.out.println("che " + new File(chemin).getAbsolutePath());

			// Un fichier excel est composé de plusieurs feuilles, on y accède
			// de la manière suivante

			Sheet sheet = workbook.getSheet(0);

			int start_row = 1;

			liste_colonne = new String[sheet.getColumns()];


			for (int k = 0; k < sheet.getColumns(); k++) {

				liste_colonne[k] = sheet.getCell(k, start_row - 1)
						.getContents();


			}

			listeNotes.clear();

			int nbre_row = sheet.getRows();

			int col = -1;

			for (int lig = start_row; lig < nbre_row; lig++) {
				
				Notes n = new Notes();
				
				Eleve el = new Eleve();

				Inscription ins = new Inscription();
				
				col = getIndice(matricule_text);

				if (col > -1) {

					el = Eleve.getEleve(sheet.getCell(col, lig).getContents());
					
				}
				
				if( el != null){
					
					n.setEleve(el);
					
					ins = Inscription.findInscription(el,AnneeScolaire.getCurrentAnneeScolaire());
					
					if( ins != null){
						
						System.out.println("is null inscription");
						
						n.setInscripionEleve(ins);
					
					}else{
					
						System.out.println("is fisrt continue");
						
						continue;
					}
					
				}else{
					
					continue;
				}

				
				listeNotes.add(n);

				n.setCoefficient(importeNote.getMatiere().getCoefficient());
				n.setDateSaisie(new Date());
				n.setEvaluation(importeNote.getEvaluation());
				n.setPourcentage(importeNote.getEvaluation().getPourcentage());
				n.setAgentSaisie(SecurityUtil.getUserName());
				n.setDateSaisie(new Date());
				n.setMatiere(importeNote.getMatiere());
			 	n.setEtablissement(importeNote.getEtablissement());
			
			
				col = getIndice(note_text);

				if (col > -1) {

					String data = sheet.getCell(col, lig).getContents();

					n.setValeur(data == null ? 0 : Double.parseDouble(data));

				}
				
			

			}

		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				// On ferme le worbook pour liberer la memoire
				workbook.close();
			}
		}

		return listeNotes;
	}

	int getIndice(String name) {

		int ind = -1;

		int nbre_col = liste_colonne.length;

		for (int j = 0; j < nbre_col; j++) {

			if (liste_colonne[j].equalsIgnoreCase(name)) {

				return j;
			}
		}

		return ind;

	}
	
	
	   @RequestMapping(params = "form", method = RequestMethod.GET)
	    public String createForm(Model uiModel) {
	        uiModel.addAttribute("importNote", new ImportNote());
	        
	        return "importnotes/create";
	    }
	   
	   
	   @ModelAttribute("etablissements")
	    public Collection<Etablissement> populateEtablissement() {
	       
		   return Etablissement.findAllEtablissements();
	    }
	    
	    @ModelAttribute("classes")
	    public Collection<Classe> populateClasse() {
	        return Classe.findAllClasses();
	    }
	    
	    @ModelAttribute("matieres")
	    public Collection<Matiere> populateMatiere() {
	        return Matiere.findAllMatieres();
	    }
	    
	    @ModelAttribute("evaluations")
	    public Collection<Evaluation> populateEvaluation() {
	        return Evaluation.findAllEvaluations();
	    }
	    

}
