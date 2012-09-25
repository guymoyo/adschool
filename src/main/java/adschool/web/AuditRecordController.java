package adschool.web;

import adschool.domain.AuditRecord;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "auditrecords", formBackingObject = AuditRecord.class)
@RequestMapping("/auditrecords")
@Controller
public class AuditRecordController {
}
