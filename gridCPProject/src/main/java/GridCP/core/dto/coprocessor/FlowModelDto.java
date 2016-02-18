package GridCP.core.dto.coprocessor;

import java.util.List;

public class FlowModelDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private int modelPackageId;
	
	private String className;
	
	private List<FlowModelVarDto> globalVars;
	
	private List<FlowComponentDto> components;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public List<FlowModelVarDto> getGlobalVars() {
		return globalVars;
	}

	public void setGlobalVars(List<FlowModelVarDto> globalVars) {
		this.globalVars = globalVars;
	}

	public List<FlowComponentDto> getComponents() {
		return components;
	}

	public void setComponents(List<FlowComponentDto> components) {
		this.components = components;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
