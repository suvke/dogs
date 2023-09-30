package dogs.dogDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dogs.dogDatabase.domain.Dog;
import dogs.dogDatabase.domain.DogRepository;
import dogs.dogDatabase.domain.GroupRepository;

@Controller
public class DogController {
	
	@Autowired
	private DogRepository dogRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	
	@RequestMapping("main")
	@ResponseBody
	public String showMainPage() {
		return "This is the main page";
	}
	
	@GetMapping("/doglist")
	public String DogList(Model model) {
		model.addAttribute("dogs", dogRepository.findAll());
		return "dogList";
		
	}

}
