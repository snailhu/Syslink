package GridCP.core.domain.coprocessor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 流程模型组件仿真参数配置
 * @author Snail
 *
 */
@Entity
@Table(name="t_coprocessorVar")
public class CoprocessorVar {
	/**Id*/
	@Id
	@GeneratedValue
	private  int id ;
	
	/**参数对应的模型*/
	@ManyToOne(optional = true)
	@JoinColumn(name = "coprocessor_id")
	private CoprocessorModel coprocessorModel;
			
	/**变量类型*/
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private VarType varType;
	
	/**输入输出*/
	@Column(length = 30, nullable = true)
	private String inOrOut;
	
	/**是否为全局变量*/
	@Column(length = 30, nullable = true)
	private String isGlobal;
	
	/**变量名*/
	@Column(length = 30, nullable = true)
	private String name;
	
	/**变量值*/
	@Column(length = 30, nullable = true)
	private String value;
	
	/**变量描述说明*/
	@Column(length = 30, nullable = true)
	private String description;
	
	/**变量单位*/
	@Column(length = 30, nullable = true)
	private String units;
	
	/**变量上界*/
	@Column(length = 30, nullable = true)
	private String lowerBound;
	/**变量下界*/
	@Column(length = 30, nullable = true)
	private String upperBound;
	
	/**枚举值*/
	@Column(length = 30, nullable = true)
	private String enumValues;
	
	/**是否是设计变量*/
	@Column(length = 30, nullable = true)
	private String isDesignVar;
	
	/**是否是输入变量*/
	@Column(length = 30, nullable = true)
	private String isInput;

	public CoprocessorModel getCoprocessorModel() {
		return coprocessorModel;
	}

	public void setCoprocessorModel(CoprocessorModel coprocessorModel) {
		this.coprocessorModel = coprocessorModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VarType getVarType() {
		return varType;
	}

	public void setVarType(VarType varType) {
		this.varType = varType;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getIsGlobal() {
		return isGlobal;
	}

	public void setIsGlobal(String isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
 
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}

	public String getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	public String getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(String enumValues) {
		this.enumValues = enumValues;
	}

	public String getIsDesignVar() {
		return isDesignVar;
	}

	public void setIsDesignVar(String isDesignVar) {
		this.isDesignVar = isDesignVar;
	}

	public String getIsInput() {
		return isInput;
	}

	public void setIsInput(String isInput) {
		this.isInput = isInput;
	}
	
	
}
