package dogs.dogDatabase.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class DogGroup {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long groupId;
	
	private String groupCode; 
	
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="group")
	private List<Dog> dogs;
	
	public DogGroup() {
		
	}

	public DogGroup(String groupCode, String name) {
		this.groupCode = groupCode;
		this.name = name;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode; 
	}

}
