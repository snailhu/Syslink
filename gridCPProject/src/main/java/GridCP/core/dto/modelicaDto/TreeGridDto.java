package GridCP.core.dto.modelicaDto;

import java.util.List;
public class TreeGridDto {
	
	private Integer id;
	
	private Integer parentId;
	
	private String text;
	
	private String iconCls;
	
	private String varName; // 变量名称

	private String value; // 默认值

	private String varType; // 变量类型

	private String units; // 变量单位

	private String description; // 变量描述

	private String MinValue; // 变量下届

	private String MaxValue; // 变量上届
	
	private String type; //数据类型
	
	private String state = "open";
					
	private List<TreeGridDto> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeGridDto> getChildren() {
		return children;
	}

	public void setChildren(List<TreeGridDto> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeGridDto [id=" + id + ", parentId=" + parentId + ", text="
				+ text + ", varName=" + varName + ", value=" + value
				+ ", varType=" + varType + ", units=" + units
				+ ", description=" + description + ", MinValue=" + MinValue
				+ ", MaxValue=" + MaxValue + ", type=" + type + ", state="
				+ state + "]";
	}

	
}
