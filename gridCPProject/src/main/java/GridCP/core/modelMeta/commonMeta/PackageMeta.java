package GridCP.core.modelMeta.commonMeta;

public class PackageMeta {

	private int id;
	
	private String name;
	
	private int parentId;
	
	private String description;
	
	private int packageTypeId;
	
	private int userId;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(int packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PaskageDto [id=" + id + ", parentId=" + parentId + ", name="
				+ name + ", description=" + description + ", packageTypeId="
				+ packageTypeId + "]";
	}


}
