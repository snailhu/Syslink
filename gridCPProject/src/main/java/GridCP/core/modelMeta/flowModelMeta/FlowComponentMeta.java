package GridCP.core.modelMeta.flowModelMeta;

import java.util.List;

/**
 * 组件模型
 * */
public class FlowComponentMeta {
	
	private String name; //组件名称名字=文件夹名称
	
	private String showName; //组件名称名字=文件夹名称
	
	private String description; //描述
	
	private String parent; //算法组件父级目录
		
	private String type;	//算法组件类型名称类型名称 

	private String runEnviroment;	//组件运行环境
	
	private List<FlowComponentVariableMeta> componentVars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRunEnviroment() {
		return runEnviroment;
	}

	public void setRunEnviroment(String runEnviroment) {
		this.runEnviroment = runEnviroment;
	}

	public List<FlowComponentVariableMeta> getComponentVars() {
		return componentVars;
	}

	public void setComponentVars(List<FlowComponentVariableMeta> componentVars) {
		this.componentVars = componentVars;
	}

	@Override
	public String toString() {
		return "MComponent [name=" + name + ", showName=" + showName
				+ ", description=" + description + ", parent=" + parent
				+ ", type=" + type + ", runEnviroment=" + runEnviroment + "]";
	}
	
}
