package GridCP.core.dto.commonDto;

public class PackageTypeDto {

	private int typeId;
	
	private String name;
	
	private String description;
	
	private int userId;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PackageTypeDto [typeId=" + typeId + ", name=" + name + ", description="
				+ description + ", userId=" + userId + "]";
	}
	
	
}
