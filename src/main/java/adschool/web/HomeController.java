package adschool.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/home")
@Controller
public class HomeController {
	
	@RequestMapping("/module")
    public String redirect(@RequestParam(value="wchich") String wchich, Model uiModel) {
		
		uiModel.addAttribute(wchich, "block");
		return wchich;
	}
}
