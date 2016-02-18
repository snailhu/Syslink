package GridCP.core.dto.modelicaDto;

import java.util.List;

public class ModelicaModelDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private int modelPackageId;
	
	private String type;
	
	private List<ModelicaComponentDto> components;
	
	private List<ModelicaModelDto> models;

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

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ModelicaComponentDto> getComponents() {
		return components;
	}

	public void setComponents(List<ModelicaComponentDto> components) {
		this.components = components;
	}

	public List<ModelicaModelDto> getModels() {
		return models;
	}

	public void setModels(List<ModelicaModelDto> models) {
		this.models = models;
	}
	
	
}
