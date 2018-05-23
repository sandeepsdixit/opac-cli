package pojo;
import java.sql.Timestamp;

public class PetBean {

	private Integer petID = null;
	private String name = null, description = null;
	private Timestamp createdOn = null, lastModifiedOn = null;
	public Integer getPetID() {
		return petID;
	}
	public void setPetID(Integer petID) {
		this.petID = petID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setLastModifiedOn(Timestamp lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}
	@Override
	public String toString() {
		return "PetBean [petID=" + petID + ", name=" + name + ", description=" + description + ", createdOn="
				+ createdOn + ", lastModifiedOn=" + lastModifiedOn + "]";
	}

}