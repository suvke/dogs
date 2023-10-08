package dogs.dogDatabase.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Dog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="Name can't be empty")
	@Size(min=5, max=40, message="Name must be 5-40 characters long")
	private String name;
	
	@NotEmpty(message="Origin can't be empty")
	@Size(min=1, max=20, message="Origin must be 1-20 characters long")
	private String origin;
	
	@Min(value=15, message="minimum value is 15")
	@Max(value=80, message="maximum value is 80")
	private int height;
	
	@Min(value=2, message="minimum value is 2")
	@Max(value=100, message="maximum value is 100")
	private int weight;
	
	@NotEmpty(message="Original use can't be empty")
	@Size(min=5, max=30, message="Original use must be 5-30 characters long")
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
