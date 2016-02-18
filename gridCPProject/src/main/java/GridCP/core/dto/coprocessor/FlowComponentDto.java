package GridCP.core.dto.coprocessor;

import java.util.List;

public class FlowComponentDto {

	private int id;
	
	private String name; //组件名称名字
		
	private String description; //描述
			
	private String type;	//算法组件类型名称类型名称 
	
	private String className;

	private List<FlowModelVarDto> vars;

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

	public List<FlowModelVarDto> getVars() {
		return vars;
	}

	public void setVars(List<FlowModelVarDto> vars) {
		this.vars = vars;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
