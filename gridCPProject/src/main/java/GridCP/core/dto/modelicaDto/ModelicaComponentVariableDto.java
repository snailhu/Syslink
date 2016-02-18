package GridCP.core.dto.modelicaDto;

public class ModelicaComponentVariableDto {

	private int id;
	
	private String componentName;
	
	private String varName; // 变量名称

	private String value; // 默认值

	private String varType; // 变量类型

	private String units; // 变量单位

	private String description; // 变量描述

	private String MinValue; // 变量下届

	private String MaxValue; // 变量上届

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVarType() {
		return varType;
	}

	public void setVarType(String varType) {
		this.varType = varType;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMinValue() {
		return MinValue;
	}

	public void setMinValue(String minValue) {
		MinValue = minValue;
	}

	public String getMaxValue() {
		return MaxValue;
	}

	public void setMaxValue(String maxValue) {
		MaxValue = maxValue;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	@Override
	public String toString() {
		return "ModelicaComponentVariableDto [id=" + id + ", varName="
				+ varName + ", value=" + value + ", varType=" + varType
				+ ", units=" + units + ", description=" + description
				+ ", MinValue=" + MinValue + ", MaxValue=" + MaxValue + "]";
	}
}
