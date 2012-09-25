package adschool.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.beans.InscriptionProcessBean;
import adschool.domain.AnneeScolaire;
import adschool.domain.Classe;
import adschool.domain.ConfigInscription;
import adschool.domain.ConfigPension;
import adschool.domain.Etablissement;
import adschool.domain.Regime;
import adschool.utils.ProcessHelper;


import org.hibernate.annotations.ParamDef;
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

@RooWebScaffold(path = "configpensions", formBackingObject = ConfigPension.class)
@RequestMapping("/configpensions")
@Controller
public class ConfigPensionController {
	
	
	
	@ResponseBody
    @RequestMapping(value = "/displayCnfInscriptionByAjax", method = RequestMethod.GET)
	 public String displayCnfInscriptionByAjax(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,
			 @RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId, Model uiModel) {
	 List<ConfigPension> search = ConfigPension.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
	        return ConfigPension.toJsonArray(search);
	}
	
	@RequestMapping(value = "/config", params = "form", method = RequestMethod.GET)
	public String createForm5(Model uiModel) {
		uiModel.addAttribute("configpensions", new ArrayList<ConfigPension>());
		uiModel.addAttribute("configpension", new ConfigPension());
		
		addDateTimeFormatPatterns(uiModel);
		return "configpensions/configPensionprocessform";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model uiModel) {
		
		ConfigPension cf = ConfigPension.findConfigPension(id);
		
		cf.remove();
		
		uiModel.addAttribute("configpensions", new ArrayList<ConfigPension>());
		uiModel.addAttribute("configpension", new ConfigPension());
		
		addDateTimeFormatPatterns(uiModel);
		
		return "configpensions/configPensionprocessform";
	}
	
	@ResponseBody
    @RequestMapping(value = "/deletePensionByAjax", method = RequestMethod.GET)
	 public String deletePensionByAjax(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId,@RequestParam("idPension") Long idPension, Model uiModel) {

		ConfigPension cf = ConfigPension.findConfigPension(idPension);
		
		cf.remove();
		
		List<ConfigPension> search = ConfigPension.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
        
		return ConfigPension.toJsonArray(search);
			
			
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/ajouterconfigByAjax", method = RequestMethod.GET)
	 public String getpension(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,
			 @RequestParam("regimeId") Long regimeId,@RequestParam("trancheId") BigInteger tranche, @RequestParam("montantId") BigInteger montant, @RequestParam("datelimite") @DateTimeFormat(pattern="dd/MM/yyyy") Date datelimite, Model uiModel) {
			
			List<ConfigPension> search = ConfigPension.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId), tranche);
        
			if( search != null && ! search.isEmpty() ){
				
				return null;
				
			}else{
			
				ConfigPension cf = new ConfigPension();
				
				cf.setAnneeScolaire(AnneeScolaire.findAnneeScolaire(yearId));
				cf.setClasse(Classe.findClasse(classeId));
				cf.setRegime(Regime.findRegime(regimeId));
				cf.setEtablissement(Etablissement.findEtablissement(etsId));
				cf.setTranche(tranche.intValue());
				cf.setMontant(montant);
				System.out.println(datelimite);
				cf.setDateLimitePaiement(datelimite);
				
				cf.persist();
				
				cf.flush();
				
				search = ConfigPension.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
		        
				
				return ConfigPension.toJsonArray(search);
			}
			
			
	}
	
	
	@ResponseBody
    @RequestMapping(value = "/findconfigByAjax", method = RequestMethod.GET)
	 public String findpension(@RequestParam("yearId") Long yearId,@RequestParam("etsId") Long etsId ,@RequestParam("classeId") Long classeId,@RequestParam("regimeId") Long regimeId, Model uiModel) {
		
		List<ConfigPension> search = ConfigPension.search(AnneeScolaire.findAnneeScolaire(yearId), Classe.findClasse(classeId), Regime.findRegime(regimeId), Etablissement.findEtablissement(etsId));
        
		return ConfigPension.toJsonArray(search);
	}
	
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid ConfigPension configPension, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("configPension", configPension);
            addDateTimeFormatPatterns(uiModel);
            return "configpensions/create";
        }
        
        List<Classe> listeClasse;
        
        if( configPension.isAppied_others()){
        	
        	listeClasse = Classe.getListeClasseToNiveau(configPension.getClasse());
        	
        	
        	for( Classe cl : listeClasse){
        		
        		ConfigPension cf = new ConfigPension();
        		
        		cf.setAnneeScolaire(configPension.getAnneeScolaire());
        		cf.setClasse(cl);
        		cf.setDateLimitePaiement(configPension.getDateLimitePaiement());
        		cf.setEtablissement(configPension.getEtablissement());
        		cf.setMontant(configPension.getMontant());
        		cf.setRegime(configPension.getRegime());
        		cf.setTranche(configPension.getTranche());
        		
        		cf.persist();
        	}
        	
        }
        

        
        uiModel.asMap().clear();
        configPension.persist();
        return "redirect:/configpensions/" + encodeUrlPathSegment(configPension.getId().toString(), httpServletRequest);
        
    
    
    }
    
    
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String afficher( Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("configpensions", ConfigPension.findAllConfigPensions());
        
        return "configpensions/list";
    }
    
    
    @ModelAttribute("etablissements")
	public Collection<Etablissement> populateEtab() {
		
		return ProcessHelper.getListeEtablissement();
	}
    
    
    
}
