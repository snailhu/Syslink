package GridCP.core.dto.commonDto;

import java.util.List;

import GridCP.core.dto.modelicaDto.TreeGridDto;

public class simulationVarTreeDto {

	private Integer id;
	
	private String modelName; 
	
	private String varName; // 变量名称

	private String value; // 默认值
	
	private String units; // 变量单位

	private String state = "open";
	
	private String type;//数据类型 model/var
	
	private List<TreeGridDto> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<TreeGridDto> getChildren() {
		return children;
	}

	public void setChildren(List<TreeGridDto> children) {
		this.children = children;
	}
	
	
}
