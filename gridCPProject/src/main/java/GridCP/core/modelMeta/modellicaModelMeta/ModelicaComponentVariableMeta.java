package GridCP.core.modelMeta.modellicaModelMeta;

public class ModelicaComponentVariableMeta {

	private String varName; //变量名称
		       
//    private String type;	//输入或输出变量
	
    private String prefixes;
    
    private String value; //默认值
    
    private String varType;	//变量类型
    
    private String units; //变量单位
    
    private String description;	//变量描述
    
    private String MinValue;	//变量下届
    
    private String MaxValue;	//变量上届

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(String prefixes) {
		this.prefixes = prefixes;
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

	@Override
	public String toString() {
		return "ModelicaComponentVariable [varName=" + varName + ", prefixes="
				+ prefixes + ", value=" + value + ", varType=" + varType
				+ ", units=" + units + ", description=" + description
				+ ", MinValue=" + MinValue + ", MaxValue=" + MaxValue + "]";
	}

    
    
}
