package adschool.web;

import adschool.domain.FootPrint;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "footprints", formBackingObject = FootPrint.class)
@RequestMapping("/footprints")
@Controller
public class FootPrintController {
}
