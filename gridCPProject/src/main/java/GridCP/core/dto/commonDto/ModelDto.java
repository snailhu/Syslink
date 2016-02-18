package GridCP.core.dto.commonDto;

import java.io.Serializable;
import java.util.List;

public class ModelDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String name;
	
	private String description;
	
	private String type;
	
	private int modelPackageId;
	
	private String className;
		
	private long modelVarSize;
	
	private String svgPath;
	
	private List<ModelVarDto> vars;
	
	private List<ModelDto> models;

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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public long getModelVarSize() {
		return modelVarSize;
	}

	public void setModelVarSize(long modelVarSize) {
		this.modelVarSize = modelVarSize;
	}

	public String getSvgPath() {
		return svgPath;
	}

	public void setSvgPath(String svgPath) {
		this.svgPath = svgPath;
	}

	public List<ModelVarDto> getVars() {
		return vars;
	}

	public void setVars(List<ModelVarDto> vars) {
		this.vars = vars;
	}

	public List<ModelDto> getModels() {
		return models;
	}

	public void setModels(List<ModelDto> models) {
		this.models = models;
	}

	@Override
	public String toString() {
		return "ModelDto [id=" + id + ", name=" + name + ", description="
				+ description + ", type=" + type + ", modelPackageId="
				+ modelPackageId + ", className=" + className
				+ ", modelVarSize=" + modelVarSize + ", svgPath=" + svgPath
				+ "]";
	}

}
