package dogs.dogDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dogs.dogDatabase.domain.Dog;
import dogs.dogDatabase.domain.DogAppUser;
import dogs.dogDatabase.domain.DogAppUserRepository;
import dogs.dogDatabase.domain.DogRepository;
import dogs.dogDatabase.domain.DogGroup;
import dogs.dogDatabase.domain.GroupRepository;

@SpringBootApplication
public class DogDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogDatabaseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(DogRepository dogRep, GroupRepository groupRep, DogAppUserRepository dogAppUserRep) {
	return (args) -> {
		
		// lisätään roturyhmiä ajonaikaiseen tietokantaan
		groupRep.save(new DogGroup("FCI 1", "Lammas- ja karjakoirat"));
		groupRep.save(new DogGroup("FCI 2", "Pinserit, snautserit, molossityyppiset ja sveitsinpaimenkoirat"));
		groupRep.save(new DogGroup("FCI 3", "Terrierit"));
		groupRep.save(new DogGroup("FCI 4", "Mäyräkoirat"));
		groupRep.save(new DogGroup("FCI 5", "Pystykorvat ja alkukantaiset koirat"));
		groupRep.save(new DogGroup("FCI 6", "Ajavat ja jäljestävät koirat"));
		groupRep.save(new DogGroup("FCI 7", "Seisovat lintukoirat"));
		groupRep.save(new DogGroup("FCI 8", "Noutajat, ylösajavat koirat ja vesikoirat"));
		groupRep.save(new DogGroup("FCI 9", "Seura- ja kääpiökoirat"));
		groupRep.save(new DogGroup("FCI 10", "Vinttikoirat"));
		
		// lisätään muutama koira ajonaikaiseen tietokantaan
		dogRep.save(new Dog("australianpaimenkoira", "USA", 45, 30, "paimennus", groupRep.findByName("Lammas- ja karjakoirat").get(0)));
		dogRep.save(new Dog("coton de tulear", "Madagaskar", 25, 8, "seurakoira", groupRep.findByName("Seura- ja kääpiökoirat").get(0)));
		dogRep.save(new Dog("englanninsetteri", "Iso-Britannia", 40, 25, "lintujen metsästys", groupRep.findByName("Seisovat lintukoirat").get(0)));
		dogRep.save(new Dog("norfolkinterrieri", "Iso-Britannia", 25, 10, "pieneläinten pyydystys", groupRep.findByName("Terrierit").get(0)));
		dogRep.save(new Dog("suomenlapinkoira", "Suomi", 45, 20, "poronhoito", groupRep.findByName("Pystykorvat ja alkukantaiset koirat").get(0)));
		
		// lisätään kaksi eri käyttäjätyyppiä ajonaikaiseen tietokantaan
		dogAppUserRep.save(new DogAppUser("user", "$2a$10$tE9wixP88rCi/.YsBE./TedZij6N7ovj4vAmW1h.5mvb06impe9Ka", "USER"));
		dogAppUserRep.save(new DogAppUser("admin", "$2a$10$1J7gZs79aA.TnQ.DT263nexqVXCLod03.pZSzM6YZS5rcJT52vjM2", "ADMIN"));
		
	}; 
		
	}

}
