package GridCP.core.dto.coprocessor;

public class ParentDto {

	private int id;
	
	//type=pkg||model
	private String type;
	
	private String name;
	
	private String className;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "ParentDto [id=" + id + ", type=" + type + ", name=" + name
				+ ", className=" + className + "]";
	}

	
	
}
