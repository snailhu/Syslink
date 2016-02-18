package GridCP.core.modelMeta.flowModelMeta;

public class FlowComponentVariableMeta {
	
	private String varName; //变量名称
	
	private String FullName; //变量全名
	       
    private String type;	//输入或输出变量
    
    private String value; //默认值
    
    private String varType;	//变量类型
    
    private String units; //变量单位
    
    private String description;	//变量描述
    
    private String MinValue;	//变量下届
    
    private String MaxValue;	//变量上届

    private String fileName;	//变量所在文件名
    
    private String enumValues;	//枚举值
    
    private String enumName; //枚举名称

    private String format;	//格式

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(String enumValues) {
		this.enumValues = enumValues;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public String toString() {
		return "MComponentVariable [varName=" + varName + ", FullName="
				+ FullName + ", type=" + type + ", value=" + value
				+ ", varType=" + varType + ", units=" + units
				+ ", description=" + description + ", MinValue=" + MinValue
				+ ", MaxValue=" + MaxValue + "]";
	}
    
    
}
