package GridCP.core.domain.coprocessor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 流程组件仿真结果实体
 * @author Snail
 *
 */
@Entity
@Table(name="t_coprocessorResult")
public class CoprocessorResult {

	/**Id*/
	@Id
	@GeneratedValue
	private  int id ;
	
	/**运行结果*/
	@ManyToOne(optional = true)
	@JoinColumn(name = "coprocessor_RunId", nullable = true)
	private CoprocesorRun coprocesorRun;
	
	/**运行结果对应的流程模型*/
	@ManyToOne(optional = true)
	@JoinColumn(name = "coprocessor_id", nullable = true)
	private CoprocessorModel coprocessorModel;
	
	/**该表类型(result为结果表，simulation为参数配置表)*/
	@Column(length = 30, nullable = true)
	private String type;
	
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
	
	
	/**是否是输入变量*/
	@Column(length = 30, nullable = true)
	private String isInput;
	
	/**变量值*/
	@Column(length = 30, nullable = true)
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CoprocesorRun getCoprocesorRun() {
		return coprocesorRun;
	}

	public void setCoprocesorRun(CoprocesorRun coprocesorRun) {
		this.coprocesorRun = coprocesorRun;
	}

	public CoprocessorModel getCoprocessorModel() {
		return coprocessorModel;
	}

	public void setCoprocessorModel(CoprocessorModel coprocessorModel) {
		this.coprocessorModel = coprocessorModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getIsInput() {
		return isInput;
	}

	public void setIsInput(String isInput) {
		this.isInput = isInput;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
