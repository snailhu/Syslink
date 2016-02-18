package GridCP.core.dto.modelicaDto;

import java.util.List;

public class ModelicaComponentDto {

	private int id;
	
	private String name;
	
	private String description;
	
	private List<ModelicaComponentVariableDto> vars;
	
	private List<ModelicaComponentDto> components;

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

	public List<ModelicaComponentVariableDto> getVars() {
		return vars;
	}

	public void setVars(List<ModelicaComponentVariableDto> vars) {
		this.vars = vars;
	}

	public List<ModelicaComponentDto> getComponents() {
		return components;
	}

	public void setComponents(List<ModelicaComponentDto> components) {
		this.components = components;
	}
	
}
