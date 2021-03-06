// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package adschool.web;

import adschool.domain.AuditRecord;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect AuditRecordController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String AuditRecordController.create(@Valid AuditRecord auditRecord, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("auditRecord", auditRecord);
            addDateTimeFormatPatterns(uiModel);
            return "auditrecords/create";
        }
        uiModel.asMap().clear();
        auditRecord.persist();
        return "redirect:/auditrecords/" + encodeUrlPathSegment(auditRecord.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String AuditRecordController.createForm(Model uiModel) {
        uiModel.addAttribute("auditRecord", new AuditRecord());
        addDateTimeFormatPatterns(uiModel);
        return "auditrecords/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String AuditRecordController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("auditrecord", AuditRecord.findAuditRecord(id));
        uiModel.addAttribute("itemId", id);
        return "auditrecords/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String AuditRecordController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("auditrecords", AuditRecord.findAuditRecordEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) AuditRecord.countAuditRecords() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("auditrecords", AuditRecord.findAllAuditRecords());
        }
        addDateTimeFormatPatterns(uiModel);
        return "auditrecords/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String AuditRecordController.update(@Valid AuditRecord auditRecord, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("auditRecord", auditRecord);
            addDateTimeFormatPatterns(uiModel);
            return "auditrecords/update";
        }
        uiModel.asMap().clear();
        auditRecord.merge();
        return "redirect:/auditrecords/" + encodeUrlPathSegment(auditRecord.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String AuditRecordController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("auditRecord", AuditRecord.findAuditRecord(id));
        addDateTimeFormatPatterns(uiModel);
        return "auditrecords/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String AuditRecordController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        AuditRecord.findAuditRecord(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/auditrecords";
    }
    
    @ModelAttribute("auditrecords")
    public Collection<AuditRecord> AuditRecordController.populateAuditRecords() {
        return AuditRecord.findAllAuditRecords();
    }
    
    void AuditRecordController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("auditRecord_creele_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("auditRecord_updatele_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    String AuditRecordController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
