package adschool.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.ConfigInscription;
import adschool.domain.ConfigPension;
import adschool.domain.Etablissement;
import adschool.domain.Inscription;
import adschool.domain.Regime;
import adschool.utils.ProcessHelper;

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

@RooWebScaffold(path = "configinscriptions", formBackingObject = ConfigInscription.class)
@RequestMapping("/configinscriptions")
@Controller
public class ConfigInscriptionController {
	
	
	
	@ResponseBody
    @RequestMapping(value = "/displayCnfInscriptionByAjax", method = RequestMethod.GET)
	 public String displayCnfInscriptionByAjax(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,
			 @RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId, Model uiModel) {
		ConfigInscription configInscription = ConfigInscription.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
	       if (configInscription != null) return configInscription.toJson();
	        return new ConfigInscription().toJson();
	}
	
	
	
	
	@RequestMapping(value = "/config", params = "form", method = RequestMethod.GET)
	public String createForm5(Model uiModel) {
		uiModel.addAttribute("configinscriptions", new ArrayList<ConfigInscription>());
		uiModel.addAttribute("configinscription", new ConfigInscription());
		
		addDateTimeFormatPatterns(uiModel);
		return "configinscriptions/configInscriptionprocessform";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model uiModel) {
		
		ConfigInscription cf = ConfigInscription.findConfigInscription(id);
		
		cf.remove();
		
		uiModel.addAttribute("configinscriptions", new ArrayList<ConfigInscription>());
		uiModel.addAttribute("configinscription", new ConfigInscription());
		
		addDateTimeFormatPatterns(uiModel);
		
		return "configinscriptions/configInscriptionprocessform";
	}
	
	@ResponseBody
    @RequestMapping(value = "/ajouterconfigByAjax", method = RequestMethod.GET)
	 public String getpension(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,
			 @RequestParam("regimeId") Long regimeId, @RequestParam("montantId") BigInteger montant, @RequestParam("datelimite") @DateTimeFormat(pattern="dd/MM/yyyy") Date datelimite, Model uiModel) {
			
			List<ConfigInscription> search = ConfigInscription.search2(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
        
			if( search != null && ! search.isEmpty() ){
				System.out.println(search+"++++++++++++++ok");
			
				return null;
				
			}else{
			
				ConfigInscription cf = new ConfigInscription();
				
				cf.setAnneeScolaire(AnneeScolaire.findAnneeScolaire(yearId));
				cf.setClasse(Classe.findClasse(classeId));
				cf.setRegime(Regime.findRegime(regimeId));
				cf.setEtablissement(Etablissement.findEtablissement(etsId));
				
				cf.setMontant(montant);
				System.out.println(datelimite);
				cf.setDateLimitePaiement(datelimite);
				
				cf.persist();
				
				cf.flush();
				
				search = ConfigInscription.search2(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
		        
				
				return ConfigInscription.toJsonArray(search);
			}
			
			
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/findconfigByAjax", method = RequestMethod.GET)
	 public String findpension(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId, Model uiModel) {
		
		List<ConfigInscription> search = ConfigInscription.search2(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
        
		return ConfigInscription.toJsonArray(search);
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/deletePensionByAjax", method = RequestMethod.GET)
	 public String deletePensionByAjax(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId,@RequestParam("idInscription") Long idPension, Model uiModel) {

		ConfigInscription cf = ConfigInscription.findConfigInscription(idPension);
		
		cf.remove();
		
		List<ConfigInscription> search = ConfigInscription.search2(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
        
		return ConfigInscription.toJsonArray(search);
			
			
	}
	
	
	
	
	
	
	
	  @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid ConfigInscription configInscription, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("configInscription", configInscription);
	            addDateTimeFormatPatterns(uiModel);
	            return "configinscriptions/create";
	        }
	        
	        List<Classe> listeClasse;
	        
	        if( configInscription.isAppied_others()){
	        	
	        	listeClasse = Classe.getListeClasseToNiveau(configInscription.getClasse());
	        	
	        	
	        	for( Classe cl : listeClasse){
	        		
	        		ConfigInscription cf = new ConfigInscription();
	        		
	        		cf.setAnneeScolaire(configInscription.getAnneeScolaire());
	        		cf.setClasse(cl);
	        		cf.setDateLimitePaiement(configInscription.getDateLimitePaiement());
	        		cf.setEtablissement(configInscription.getEtablissement());
	        		cf.setMontant(configInscription.getMontant());
	        		cf.setRegime(configInscription.getRegime());
	        		
	        		
	        		cf.persist();
	        	}
	        	
	        }
	        
	        
	        uiModel.asMap().clear();
	        configInscription.persist();
	        return "redirect:/configinscriptions/" + encodeUrlPathSegment(configInscription.getId().toString(), httpServletRequest);
	    }
	  
	  @ModelAttribute("etablissements")
		public Collection<Etablissement> populateEtab() {
			
			return ProcessHelper.getListeEtablissement();
		}
}
