// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.Options;
import adschool.domain.Speciality;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect OptionsController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String OptionsController.create(@Valid Options options, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("options", options);
            return "optionses/create";
        }
        uiModel.asMap().clear();
        options.persist();
        return "redirect:/optionses/" + encodeUrlPathSegment(options.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String OptionsController.createForm(Model uiModel) {
        uiModel.addAttribute("options", new Options());
        return "optionses/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String OptionsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("options", Options.findOptions(id));
        uiModel.addAttribute("itemId", id);
        return "optionses/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String OptionsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("optionses", Options.findOptionsEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Options.countOptionses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("optionses", Options.findAllOptionses());
        }
        return "optionses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String OptionsController.update(@Valid Options options, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("options", options);
            return "optionses/update";
        }
        uiModel.asMap().clear();
        options.merge();
        return "redirect:/optionses/" + encodeUrlPathSegment(options.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String OptionsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("options", Options.findOptions(id));
        return "optionses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String OptionsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Options.findOptions(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/optionses";
    }
    
    @ModelAttribute("optionses")
    public Collection<Options> OptionsController.populateOptionses() {
        return Options.findAllOptionses();
    }
    
    @ModelAttribute("specialitys")
    public Collection<Speciality> OptionsController.populateSpecialitys() {
        return Speciality.findAllSpecialitys();
    }
    
    String OptionsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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