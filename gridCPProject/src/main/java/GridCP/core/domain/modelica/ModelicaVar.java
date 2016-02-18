package GridCP.core.domain.modelica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * modelica 变量以及参数配置表
 * @author Snail
 *
 */
@Entity
@Table(name="t_modelicaVar")
public class ModelicaVar {
	
	@Id
	@GeneratedValue
	private int id;
	
	/**所属的modelica模型*/
	@ManyToOne()
	@JoinColumn(name = "modelica_id")
	private ModelicaModelAndComponent modelicaModelandComponent;
	
	/**参数配置对应的结果*/
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "modelicaResult_id")
	private ModelicaResult modelicaResult;
	
	/**变量单位*/
	@Column(length = 30, nullable = false)
	private String unit;
	
	/**变量名称*/
	@Column(length = 30, nullable = false)
	private String name;
	
	/**变量描述*/
	@Column
	private String description;
	
	/**变量值*/
	@Column
	private String value;
	
	/**输入还是输出*/
	@Column
	private String isInputOrOutput;
	
	/**是否为默认配置*/
	@Column
	private boolean isDefault;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelicaModelAndComponent getModelicaModelandComponent() {
		return modelicaModelandComponent;
	}

	public void setModelicaModelandComponent(
			ModelicaModelAndComponent modelicaModelandComponent) {
		this.modelicaModelandComponent = modelicaModelandComponent;
	}

	public ModelicaResult getModelicaResult() {
		return modelicaResult;
	}

	public void setModelicaResult(ModelicaResult modelicaResult) {
		this.modelicaResult = modelicaResult;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsInputOrOutput() {
		return isInputOrOutput;
	}

	public void setIsInputOrOutput(String isInputOrOutput) {
		this.isInputOrOutput = isInputOrOutput;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "ModelicaVar [id=" + id + ", modelicaModelandComponent="
				+ modelicaModelandComponent.getId() + ", modelicaResult="
				+ modelicaResult + ", unit=" + unit + ", name=" + name
				+ ", description=" + description + ", value=" + value
				+ ", isInputOrOutput=" + isInputOrOutput + ", isDefault="
				+ isDefault + "]";
	}

	
}
