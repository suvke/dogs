package dogs.dogDatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dogs.dogDatabase.domain.Dog;
import dogs.dogDatabase.domain.DogRepository;
import dogs.dogDatabase.domain.GroupRepository;
import jakarta.validation.Valid;

@Controller
public class DogController {
	
	//injektoidaan DogRepository käyttöön
	@Autowired
	private DogRepository dogRepository;
	
	//injektoidaan GroupRepository käyttöön
	@Autowired
	private GroupRepository groupRepository;
	
	//login-sivu, joka palauttaa sisäänkirjautumislomakkeen
	@GetMapping("/login")
	public String AppLogin() {
		return "loginForm";
	}
	
	//etusivu, joka näyttää listan kaikista tietokantaan talletetuista koirista
	@GetMapping("/doglist")
	public String DogList(Model model) {
		model.addAttribute("dogs", dogRepository.findAll());
		return "dogList";		
	}
	
	//palautetaan html-lomake koiran lisäystä varten
	//vain admin tasoisilla käyttäjillä on oikeus lisätä uusi koira
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/add")
	public String addDog(Model model) {
		model.addAttribute("dog", new Dog());
		model.addAttribute("groups", groupRepository.findAll());
		return "addDog";
	}
	
	//ennen tietojen tallennusta tarkistetaan, onko lomakkeeelle syötetyissä tiedoissa virheitä 
	//jos tiedot on annettu oikein, tallennetaan uusi koira ja ohjataan takaisin /doglist sivulle
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("dog", dog);
			model.addAttribute("groups", groupRepository.findAll());
			return "addDog";
		}
		
		dogRepository.save(dog);
		return "redirect:doglist";
	} 

	
	//poistetaan koira tietokannasta id:n perusteella
	//vain admin tasoisilla käyttäjillä on oikeus poistaa koira
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteDog(@PathVariable("id") Long id, Model model) {
		dogRepository.deleteById(id);
		return "redirect:../doglist";
	}
	
	//editoidaan tietokannassa olevan koiran tietoja id:n perusteella
	//vain admin tasoisilla käyttäjillä on oikeus muokata koiran tietoja
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/edit/{id}")
	public String editDog(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dog", dogRepository.findById(id));
		model.addAttribute("groups", groupRepository.findAll());
		return "editDog";
	}
	
	//oma save-metodi editoidun koiran tietojen tallentamista varten
	//tarkistetaan käyttäjän syötteet
	//jos tiedot on annettu oikein, tallennetaan koiran tiedot (huom! ei lisätä uutta koiraa) ja ohjataan takaisin /doglist sivulle
	@PostMapping("/saveedit")
	public String saveEdit(@Valid @ModelAttribute("dog") Dog dog, BindingResult bindingResult, Model model) {
			
		if (bindingResult.hasErrors()) {
			model.addAttribute("dog", dog);
			model.addAttribute("groups", groupRepository.findAll());
			return "editDog";
		}
			
		dogRepository.save(dog);
		return "redirect:doglist";
	}

	
	//näytetään lista kaikista koirarotuluokista
	//tähän on pääsy kaikilla käyttäjillä
	@GetMapping("/grouplist")
	public String dogGroupList(Model model) {
		model.addAttribute("groups", groupRepository.findAll());
		return "dogGroupList";
	}
	
}
