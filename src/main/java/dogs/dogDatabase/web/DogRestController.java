package dogs.dogDatabase.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dogs.dogDatabase.domain.Dog;
import dogs.dogDatabase.domain.DogGroup;
import dogs.dogDatabase.domain.DogRepository;
import dogs.dogDatabase.domain.GroupRepository;

@RestController
public class DogRestController {
	
	@Autowired
	private DogRepository dogRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	//palautetaan lista koirista JSON-muodossa
	//vain admin-tasoisilla käyttäjillä on oikeus käyttää REST palveluita
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/dogs")
	public List<Dog> dogListRest() {
		return (List<Dog>) dogRepository.findAll();
	}
	
	//palautetaan lista roturyhmistä JSON-muodossa
	//vain admin-tasoisilla käyttäjillä on oikeus käyttää REST palveluita
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/groups")
	public List<DogGroup> groupListRest() {
		return (List<DogGroup>) groupRepository.findAll();
	}
	
	//REST palvelu uuden koiran lisäämiseksi tietokantaan
	//vain admin-tasoisilla käyttäjillä on oikeus käyttää REST palveluita
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/dog")
	public Dog newDog(@RequestBody Dog newDog) {
		return dogRepository.save(newDog);
	}
}
