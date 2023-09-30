package dogs.dogDatabase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String origin;
	private int height;
	private int weight;
	private String originalUse;
	
	// jokainen koirarotu kuuluu yhteen roturyhmään, roturyhmään voi kuulua monia koiria
	@ManyToOne
	@JoinColumn(name="groupId")
	private DogGroup group;
	
	public Dog() {
		
	}

	public Dog(String name, String origin, int height, int weight, String originalUse, DogGroup group) {
		this.name = name;
		this.origin = origin;
		this.height = height;
		this.weight = weight;
		this.originalUse = originalUse;
		this.group = group;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getOriginalUse() {
		return originalUse;
	}

	public void setOriginalUse(String originalUse) {
		this.originalUse = originalUse;
	}

	public DogGroup getGroup() {
		return group;
	}

	public void setGroup(DogGroup group) {
		this.group = group;
	}
	

}
