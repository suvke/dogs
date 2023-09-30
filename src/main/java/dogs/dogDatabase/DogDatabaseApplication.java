package dogs.dogDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dogs.dogDatabase.domain.Dog;
import dogs.dogDatabase.domain.DogRepository;
import dogs.dogDatabase.domain.DogGroup;
import dogs.dogDatabase.domain.GroupRepository;

@SpringBootApplication
public class DogDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogDatabaseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(DogRepository dogRep, GroupRepository groupRep) {
	return (args) -> {
		
		groupRep.save(new DogGroup("FCI 1", "Lammas- ja karjakoirat"));
		groupRep.save(new DogGroup("FCI 3", "Terrierit"));
		groupRep.save(new DogGroup("FCI 7", "Seisovat lintukoirat"));
		groupRep.save(new DogGroup("FCI 9", "Seura- ja kääpiökoirat"));
		
		dogRep.save(new Dog("australianpaimenkoira", "USA", 45, 30, "paimennus", groupRep.findByName("Lammas- ja karjakoirat").get(0)));
		dogRep.save(new Dog("coton de tulear", "Madagaskar", 25, 8, "seurakoira", groupRep.findByName("Seura- ja kääpiökoirat").get(0)));
		dogRep.save(new Dog("englanninsetteri", "Iso-Britannia", 40, 25, "lintukoira", groupRep.findByName("Seisovat lintukoirat").get(0)));
		dogRep.save(new Dog("norfolkinterrieri", "Iso-Britannia", 25, 10, "terrieri", groupRep.findByName("Terrierit").get(0)));
	};
		
	}

}
