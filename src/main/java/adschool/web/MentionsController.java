package adschool.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import adschool.domain.Mentions;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RooWebScaffold(path = "mentionses", formBackingObject = Mentions.class)
@RequestMapping("/mentionses")
@Controller
public class MentionsController {
	
	@ModelAttribute("pedagogie")
    public String populateMenu() {
        return "block";
    }
	
	 @RequestMapping(method = RequestMethod.POST)
	    public String create(@Valid Mentions mentions, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
	        if (bindingResult.hasErrors()) {
	            uiModel.addAttribute("mentions", mentions);
	            return "mentionses/create";
	        }
	        
	        if( Mentions.existe(mentions.getValueMin(), mentions.getValueMax())){
	        	uiModel.addAttribute("mentions", mentions);
	        	uiModel.addAttribute("apMessage", " Cette plage est deja utilise");
	        	
	            return "mentionses/create";
	        }
	        
	        
	        uiModel.asMap().clear();
	        mentions.persist();
	        return "redirect:/mentionses/" + encodeUrlPathSegment(mentions.getId().toString(), httpServletRequest);
	    }
	    
	    @RequestMapping(params = "form", method = RequestMethod.GET)
	    public String createForm(Model uiModel) {
	        uiModel.addAttribute("mentions", new Mentions());
	        return "mentionses/create";
	    }
	    
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	    public String show(@PathVariable("id") Long id, Model uiModel) {
	        uiModel.addAttribute("mentions", Mentions.findMentions(id));
	        uiModel.addAttribute("itemId", id);
	        return "mentionses/show";
	    }
}
