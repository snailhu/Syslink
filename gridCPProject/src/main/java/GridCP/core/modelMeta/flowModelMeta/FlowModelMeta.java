package GridCP.core.modelMeta.flowModelMeta;

import java.util.List;

/**
 * 流程模型
 * */
public class FlowModelMeta {

	private String flowName;
	
	private String description;
	
	private String flowType;
	
	private int modelPackageId;
	
	private List<FlowComponentMeta> components;
	
	private List<FlowComponentVariableMeta> globalVars;


	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public List<FlowComponentMeta> getComponents() {
		return components;
	}

	public void setComponents(List<FlowComponentMeta> components) {
		this.components = components;
	}

	public List<FlowComponentVariableMeta> getGlobalVars() {
		return globalVars;
	}

	public void setGlobalVars(List<FlowComponentVariableMeta> globalVars) {
		this.globalVars = globalVars;
	}

	@Override
	public String toString() {
		return "MFlowModel [flowName=" + flowName + ", description="
				+ description + ", flowType=" + flowType + "]";
	}
	
}
