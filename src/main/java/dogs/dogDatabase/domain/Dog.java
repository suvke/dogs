package dogs.dogDatabase.domain;

import jakarta.persistence.Entity;

@Entity
public class Dog {
	
	private String origin;
	private int height;
	private int weight;
	private String originalUse;
	
	// jokainen koirarotu kuuluu yhteen roturyhmään, roturyhmään voi kuulua monia koiria
	private String group;
	

}
