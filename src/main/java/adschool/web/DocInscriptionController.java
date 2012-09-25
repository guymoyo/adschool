package adschool.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.Classe;
import adschool.domain.DocInscription;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RooWebScaffold(path = "docinscriptions", formBackingObject = DocInscription.class)
@RequestMapping("/docinscriptions")
@Controller
public class DocInscriptionController {
	
	@ModelAttribute("configuration")
    public String populateMenu() {
        return "block";
    }
	
	
	 @RequestMapping(params = { "find=ByClasse", "form" }, method = RequestMethod.GET)
	    public String findDocInscriptionsByClasseForm(Model uiModel) {
	        uiModel.addAttribute("classes", Classe.findAllClasses());
	        return "docinscriptions/findDocInscriptionsByClasse";
	    }
	 
	 @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid DocInscription docInscription, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("docInscription", docInscription);
	            return "docinscriptions/create";
	        }
	        
	        List<DocInscription> findDocInscriptionsByClasse = DocInscription.findDocInscriptionsByClasse(docInscription.getClasse()).getResultList();
	        
	        if (  findDocInscriptionsByClasse != null ){
	        	
	        	for (DocInscription docInscription2 : findDocInscriptionsByClasse) {
					
	        		docInscription2.setListeDocuments(docInscription.getListeDocuments());
	        		
	        		docInscription2.merge();
				}
	        	
	        	
	        }else{
	        

		        uiModel.asMap().clear();
		        docInscription.persist();
	           
	        }
	        
	        uiModel.addAttribute("docinscriptions", DocInscription.findAllDocInscriptions());
     
	 		return "docinscriptions/list";
	        
	 }
	 
	 @RequestMapping(method = RequestMethod.PUT)
	    public String update(@Valid DocInscription docInscription, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("docInscription", docInscription);
	            return "docinscriptions/update";
	        }
	        uiModel.asMap().clear();
	        docInscription.merge();
	        return "redirect:/docinscriptions/" + encodeUrlPathSegment(docInscription.getId().toString(), httpServletRequest);
	    }
	 
	 
	 
	 String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
