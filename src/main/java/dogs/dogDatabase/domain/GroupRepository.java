package dogs.dogDatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<DogGroup, Long> {

	List<DogGroup> findByName(String name);
}
