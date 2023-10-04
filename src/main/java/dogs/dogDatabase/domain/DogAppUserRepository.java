package dogs.dogDatabase.domain;

import org.springframework.data.repository.CrudRepository;

public interface DogAppUserRepository extends CrudRepository<DogAppUser, Long> {
	
	DogAppUser findByUsername(String username);
}
