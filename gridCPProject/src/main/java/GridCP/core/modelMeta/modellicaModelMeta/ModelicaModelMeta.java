package GridCP.core.modelMeta.modellicaModelMeta;

import java.util.List;

public class ModelicaModelMeta {

	private String name;
	
	private String description;
	
	private String modelSVGUrl;
	
	private List<ModelicaComponentMeta> components;

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

	public String getModelSVGUrl() {
		return modelSVGUrl;
	}

	public void setModelSVGUrl(String modelSVGUrl) {
		this.modelSVGUrl = modelSVGUrl;
	}

	public List<ModelicaComponentMeta> getComponents() {
		return components;
	}

	public void setComponents(List<ModelicaComponentMeta> components) {
		this.components = components;
	}

	@Override
	public String toString() {
		return "ModelicaModel [name=" + name + ", description=" + description
				+ "]";
	}
	
	
}
