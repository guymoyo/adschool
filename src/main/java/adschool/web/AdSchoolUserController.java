package adschool.web;

import adschool.security.AdSchoolUser;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "adschoolusers", formBackingObject = AdSchoolUser.class)
@RequestMapping("/adschoolusers")
@Controller
public class AdSchoolUserController {
}
