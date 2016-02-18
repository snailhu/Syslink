package GridCP.core.modelMeta.modellicaModelMeta;

import java.util.List;

public class ModelicaComponentMeta {

	private String name;
		
	private String description;
	
	private String className;
	
	private String classDes;
	
	private List<ModelicaComponentVariableMeta> vars;
	
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassDes() {
		return classDes;
	}

	public void setClassDes(String classDes) {
		this.classDes = classDes;
	}

	public List<ModelicaComponentVariableMeta> getVars() {
		return vars;
	}

	public void setVars(List<ModelicaComponentVariableMeta> vars) {
		this.vars = vars;
	}

	public List<ModelicaComponentMeta> getComponents() {
		return components;
	}

	public void setComponents(List<ModelicaComponentMeta> components) {
		this.components = components;
	}

	@Override
	public String toString() {
		return "ModelicaComponent [name=" + name + ", description="
				+ description + ", className=" + className + ", classDes="
				+ classDes + "]";
	}


}
