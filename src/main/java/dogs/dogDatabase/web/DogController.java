package dogs.dogDatabase.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DogController {
	
	@RequestMapping("main")
	@ResponseBody
	public String showMainPage() {
		return "This is the main page";
	}

}
