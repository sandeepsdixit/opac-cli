package pojo;
import java.sql.Timestamp;

public class HobbyBean {

	private Integer hobbyID = null;
	private String name = null, description = null;
	private Timestamp createdOn = null, lastModifiedOn = null;
	public Integer getHobbyID() {
		return hobbyID;
	}
	public void setHobbyID(Integer hobbyID) {
		this.hobbyID = hobbyID;
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
		return "HobbyBean [hobbyID=" + hobbyID + ", name=" + name + ", description=" + description + ", createdOn="
				+ createdOn + ", lastModifiedOn=" + lastModifiedOn + "]";
	}

}