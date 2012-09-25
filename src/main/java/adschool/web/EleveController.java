package adschool.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.Eleve;
import adschool.domain.Etablissement;
import adschool.domain.Genre;
import adschool.domain.Inscription;
import adschool.domain.Matiere;
import adschool.domain.PaiementPensions;
import adschool.domain.Parent;
import adschool.domain.Pays;
import adschool.domain.PensionEleves;
import adschool.domain.Regime;
import adschool.utils.FileUploadService;
import adschool.utils.ProcessHelper;

import org.springframework.orm.jpa.EntityManagerFactoryUtils;
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
import org.springframework.web.multipart.MultipartFile;

@RooWebScaffold(path = "eleves", formBackingObject = Eleve.class)
@RequestMapping("/eleves")
@Controller
public class EleveController {

	@ModelAttribute("economat")
	public String populateMenu() {
		return "block";
	}
	
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        uiModel.addAttribute("eleve", new Eleve());
        addDateTimeFormatPatterns(uiModel);
        return "eleves/create";
    }

	@RequestMapping(method = RequestMethod.POST)
	public String create(@Valid Eleve eleve, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		eleve.validate(bindingResult);
		
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("eleve", eleve);
			addDateTimeFormatPatterns(uiModel);
			return "eleves/create";
		}
		if (eleve.getId()==null) eleve.save();
		
		MultipartFile file= eleve.getUserImage();
		
		
		
		try {
			String filePath =  FileUploadService.storeFile(file,FileUploadService.IMAGES_STUDENT_DIR,eleve.getMatricule());
			
			
			if (filePath == null) {
				
				if(eleve.getPathPhoto().isEmpty()){
					
					eleve.setPathPhoto(FileUploadService.getDefaultImage(eleve));
				}
				
			} else {
				eleve.setPathPhoto(filePath);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		uiModel.asMap().clear();
		eleve.merge();
		
		if ( ProcessHelper.getAnneeCourante() != null){
			
			if ( Inscription.findInscription(eleve, ProcessHelper.getAnneeCourante()) == null){
				 
				 uiModel.addAttribute("canInscrire", true);

			}else{
				 
				uiModel.addAttribute("canInscrire", false);

			}
			
		}else{
			
			uiModel.addAttribute("canInscrire", false);
		}
		
		
		
		
		
		
		//return "redirect:/eleves/" + encodeUrlPathSegment(eleve.getId().toString(), httpServletRequest);

		 uiModel.addAttribute("eleve", eleve);
		
		return "eleves/show";
	}

	
	 @RequestMapping(method = RequestMethod.PUT)
	    public String update(@Valid Eleve eleve, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		 eleve.validate(bindingResult);
		 
		 if (bindingResult.hasErrors()) {
				uiModel.addAttribute("eleve", eleve);
				addDateTimeFormatPatterns(uiModel);
				return "eleves/update";
			}
			MultipartFile file= eleve.getUserImage();
			try {
				String filePath =  FileUploadService.storeFile(file,FileUploadService.IMAGES_STUDENT_DIR,eleve.getNom());
				if (filePath == null) {
					eleve.setPathPhoto(FileUploadService.getDefaultImage(eleve));
				} else {
					eleve.setPathPhoto(filePath);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			uiModel.asMap().clear();
			if (eleve.getId()==null) {
				//eleve.persist();
			}else {
				eleve.merge();
			}
			
			return "redirect:/eleves/" + encodeUrlPathSegment(eleve.getId().toString(), httpServletRequest);
	    }
	
	@RequestMapping(value="/findEleve/ByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String findProductByCipAjax(@RequestParam("nomcomplet") String nomcomplet, @RequestParam("matricules") String matricule  , Model uiModel ,  HttpServletRequest httpServletRequest) {
		
		List<Eleve> resultList = Eleve.findElevesByNomLikeOrMatricule(nomcomplet, matricule).setMaxResults(200).getResultList();
		
		return Eleve.toJsonArray(resultList);
	}

	@RequestMapping(value="/getStudentByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getStudentByAjax(@RequestParam("Id") Long Id, Model uiModel ,  HttpServletRequest httpServletRequest) {
		
		return Eleve.findEleve(Id).toJson();
	}
	
	@RequestMapping(params = "form",value="/listeSoldeEleve")
	public String getSolde(Model uiModel) {
		
		uiModel.addAttribute("eleves", Eleve.findAllEleves());
        uiModel.addAttribute("eleve",new Eleve());

        addDateTimeFormatPatterns(uiModel);
		
        return "eleves/listeEleveSolde";
	}
	
	@RequestMapping(value="/saveEleveAjax", method = RequestMethod.GET)
	@ResponseBody
	public String saveEleveAjax(@RequestParam("matricule") String matricule, @RequestParam("nom") String nom, Model uiModel ,  HttpServletRequest httpServletRequest) {
		
		List<Eleve> resultList = Eleve.findElevesByNomLike(nom).setMaxResults(200).getResultList();
		
		return Eleve.toJsonArray(resultList);
	}

	
	
	@RequestMapping(value="/saveStudentByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String saveStudentByAjax(@RequestParam("etablissement") Long etablissement, @RequestParam("parentId") Long parent,@RequestParam("nom") String nom,@RequestParam("prenom") String prenom,@RequestParam("lieuNaissance") String lieuNaissance,@RequestParam("nationalite") Long nationalite,@RequestParam("cni") String cni,@RequestParam("telephone") String telephone,@RequestParam("email") String email,@RequestParam("boitePostale") String boitepostale, @RequestParam("password") String pwd, Model uiModel ,  HttpServletRequest httpServletRequest) {
	
		Eleve eleve = new Eleve();
		
		eleve.setNom(nom);
		eleve.setPrenom(prenom);
		eleve.setPassword(pwd);
		eleve.setBoitePostale(boitepostale);
		eleve.setCni(cni);
		eleve.setEmail(email);
		eleve.setGenre(Genre.MASCULIN);
		eleve.setLieuNaissance(lieuNaissance);
		
		if(parent == null) {
			
			return null;
		
		}else{
			
			eleve.setParent(Parent.findParent(parent));
		
		}

		eleve.setNationalite(Pays.findPays(nationalite));
		
		eleve.setEtablissement(Etablissement.findEtablissement(etablissement));
		eleve.setPathPhoto(FileUploadService.getDefaultImage(eleve));
		eleve.persist();
		return eleve.toJson();
	}


	@RequestMapping(value="/findEleveAjax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String findEleveAjax(@PathVariable("id") Long id, Model uiModel) {
	
		Eleve produits = Eleve.findEleve(id);
		
		return produits.toJson();
	}
	
	
	@RequestMapping(value = "/getListeEleveClasseByAjax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getListeEleveClasseByAjax(@PathVariable("id") Long id, Model uiModel) {

		List<Inscription> ins = Inscription.getListeInscription(Classe.findClasse(id), AnneeScolaire.getCurrentAnneeScolaire());
		
		return Inscription.toJsonArray(ins);
	}
		
	@RequestMapping(value = "/listepension", method = RequestMethod.GET)
	public String findPensionEleve(@RequestParam("id") Long id, Model uiModel) {
		
		List<PensionEleves> liste = PensionEleves.findPensionEleve(Eleve.findEleve(id)).getResultList();
		
		uiModel.addAttribute("pensioneleveses",liste);
		return  "pensioneleveses/listePensionEleve";
	}
	//http://localhost:8082/adschool/eleves/listingpaiementeleve?id=2
	@RequestMapping(value = "/listingpaiementeleve", method = RequestMethod.GET)
	public String findPaiementEleve(@RequestParam("id") Long id, Model uiModel) {
		
		List<PaiementPensions> liste = PaiementPensions.getPaiementEleve(Eleve.findEleve(id)).getResultList();
		
		uiModel.addAttribute("paiementpensionses",liste);
		return  "paiementpensionses/list";
	}

	@RequestMapping(value="/payerpension", method = RequestMethod.GET)
	public String payerpension(@RequestParam("id") Long id, Model uiModel) {
		
		Eleve eleve = Eleve.findEleve(id);
		
		List<PensionEleves> liste = PensionEleves.findPensionElevesPayable(eleve).getResultList();
		
		uiModel.addAttribute("eleve",eleve);
		
		uiModel.addAttribute("pensioneleveses",liste);
		
		return  "paiementpensionses/paiementAfter";
	}
	
	
	

	
	
	@RequestMapping(value="/getNextEleveByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getNextEleveByAjax(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long inscription = Long.parseLong(httpServletRequest.getParameter("inscription"));
		
		Inscription ins = Inscription.findInscription(inscription);
		
		Classe classe = ins.getClasse();
		
		Inscription inscriptionNext = new Inscription(); 
		
		List<Inscription> listeInscription = Inscription.getListeInscription(classe, AnneeScolaire.getCurrentAnneeScolaire());
		
		for (int i=0; i< listeInscription.size(); i++){
			
			if ( listeInscription.get(i).getId() == ins.getId()){
				
				if( i < (listeInscription.size()-1)){
					
					inscriptionNext = listeInscription.get(i+1);
					
				}else{
					
					inscriptionNext = null;
				}
				 
			}
			
		}
		
		return  inscriptionNext != null ? inscriptionNext.toJson() : null;
	}
	
	
	@RequestMapping(value="/getPreviousEleveByAjax", method = RequestMethod.GET)
	@ResponseBody
	public String getPreviousMatiereByAjax(Model uiModel, HttpServletRequest httpServletRequest) {
		
		long inscription = Long.parseLong(httpServletRequest.getParameter("inscription"));
		
		Inscription ins = Inscription.findInscription(inscription);
		
		Classe classe = ins.getClasse();
		
		Inscription inscriptionNext = new Inscription(); 
		
		List<Inscription> listeInscription = Inscription.getListeInscription(classe, AnneeScolaire.getCurrentAnneeScolaire());
		
		for (int i=0; i< listeInscription.size(); i++){
			
			if ( listeInscription.get(i).getId() == ins.getId()){
				
				if( i > 0){
					
					inscriptionNext = listeInscription.get(i-1);
					
				}else{
					
					inscriptionNext = null;
				}
				 
			}
			
		}
		
		
		return  inscriptionNext != null ? inscriptionNext.toJson() : null;
	}

	List<Eleve> getListe (Integer page, Integer size, List<Eleve> listeIn, Model uiModel){
		
		List<Eleve> listeOut = new ArrayList<Eleve>();
	
		int sizeNo = size == null ? 10 : size.intValue();
		
		float nrOfPages = (float) Eleve.countEleves() / sizeNo;
		
		listeOut = listeIn.subList(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo );
		
		uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		
		uiModel.addAttribute("eleves", listeOut);
		
		return listeOut;
		
		
	}
	
	
	
	
	


	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {

		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("eleves", Eleve.findEleveEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) Eleve.countEleves() / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			uiModel.addAttribute("eleves", Eleve.findAllEleves());
		}
		uiModel.addAttribute("eleve",new Eleve());

		addDateTimeFormatPatterns(uiModel);
		return "eleves/list";
	}
	
	 @RequestMapping(params = "form",value="/list", method = RequestMethod.GET)
	    public String list2( Model uiModel) {
	    	
	    	uiModel.addAttribute("eleves", Eleve.findAllEleves());
	        uiModel.addAttribute("eleve",new Eleve());

	        addDateTimeFormatPatterns(uiModel);
	        return "eleves/list";
	    }
	 
	 
	 @RequestMapping(params = { "find=ByNomOrMatriculeLike", "form" }, method = RequestMethod.GET)
	    public String findElevesByNomOrMatriculeLikeForm(Model uiModel) {
	        return "eleves/findElevesByNomOrMatriculeLike";
	    }
	    
	    @RequestMapping(params = "find=ByNomOrMatriculeLike", method = RequestMethod.GET)
	    public String findElevesByNomOrMatriculeLike(@RequestParam("matricule") String matricule, @RequestParam("nom") String nom, Model uiModel) {
	        uiModel.addAttribute("eleves", Eleve.findElevesByNomLikeOrMatricule(nom, matricule).getResultList());
	        return "eleves/listEtudiantUpdate";
	    }


		 
		 @RequestMapping(params = { "find=ByPension", "form" }, method = RequestMethod.GET)
		    public String getInscriptionByPension(Model uiModel) {
		        addDateTimeFormatPatterns(uiModel);
		        return "eleves/findElevesByPension";
		    }
		 
		 
		 @RequestMapping(params = "find=ByPension", method = RequestMethod.GET)
		    public String getInscriptionByPension(@RequestParam(value = "solde", required = false) Long solde, @RequestParam("etablissement") Long etablissement, @RequestParam("classe") Long classe,  @RequestParam("annee") Long annee, Model uiModel) {
		    
			 if ( solde == null){
				 
				 solde = new Long(0);
			 }
			 
			 List<Eleve> listeEleveIn = Eleve.getListeElevesInscrit(Classe.findClasse(classe), AnneeScolaire.findAnneeScolaire(annee));
			 
			 List<Eleve> listeEleveOut = new ArrayList<Eleve>();
			 
			 for (Eleve eleve : listeEleveIn) {
				
				 if( eleve.getSolde() >= solde){
					 
					 listeEleveOut.add(eleve);
				 }
			}
			 
			 
			    uiModel.addAttribute("eleves", listeEleveOut);
		        return "eleves/list";
		    }
		 

		 
		 
		 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		       
			 if ( Inscription.findInscriptionsByEleve(Eleve.findEleve(id)) != null){
				 
				 uiModel.addAttribute("eleve", Eleve.findEleve(id));
				 addDateTimeFormatPatterns(uiModel);
				 uiModel.addAttribute("apMessage", "Impossible de supprimer cet etudiant : Inscription en cours sur ce dernier");
				 
				 return "eleves/show";
				 
			 }
			 
			 
			 Eleve.findEleve(id).remove();
		        uiModel.asMap().clear();
		        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		        return "redirect:/eleves";
		    }
		 
		 
		 
		 
		 
		 
		 @RequestMapping(params = "find=ByMatriculeLike", method = RequestMethod.GET)
		    public String findElevesByMatriculeLike(@RequestParam("matricule") String matricule, Model uiModel) {
		        uiModel.addAttribute("eleves", Eleve.findElevesByMatriculeLike(matricule).getResultList());
		       
		        //return "eleves?";
		        return "eleves/list";
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

		 
		


}
