package GridCP.core.domain.coprocessor;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 流程组件模型实体
 * @author Snail
 *
 */
@Entity
@Table(name="t_coprocessorModel")
public class CoprocessorModel {

	/**ID*/
	@Id
	@GeneratedValue
	private int coprocessorId;
	
	/**父类Id*/
	@Column(nullable = true)
	private int parentId;
	
	/**模型包Id*/
	@Column(nullable = true)
	private int modelPackageId;
	
	/**模型名*/
	@Column(length = 30, nullable = true)
	private String name;
	
	/**数据类型*/
	@Column(length = 30, nullable = true)
	private String dataType;
	
	/**模型组件类型类型*/
	@Column(length = 30, nullable = true)
	private String type;
	
	/**模型描述*/
	@Column(length = 1000, nullable = true)
	private String description;
	
	/**模型文本路径*/
	@Column(length = 30, nullable = true)
	private String  URL;

	/**模型当前版本*/
	@Column(nullable = true)
	private int verison;
	
	/**该模型对应的变量 */
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="coprocessorModel") 
	private Set<CoprocessorVar> coprocessorVars;
	
	/**该模型对应的运行参数配置 */
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="coprocessorModel") 
	private Set<CoprocesorRun> coprocesorRuns;
	
	/**该模型对应的运行参数配置 */
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="coprocessorModel") 
	private Set<CoprocessorResult> coprocessorResults;
		
	public Set<CoprocessorResult> getCoprocessorResults() {
		return coprocessorResults;
	}

	public void setCoprocessorResults(Set<CoprocessorResult> coprocessorResults) {
		this.coprocessorResults = coprocessorResults;
	}

	public Set<CoprocesorRun> getCoprocesorRuns() {
		return coprocesorRuns;
	}

	public void setCoprocesorRuns(Set<CoprocesorRun> coprocesorRuns) {
		this.coprocesorRuns = coprocesorRuns;
	}

	public Set<CoprocessorVar> getCoprocessorVars() {
		return coprocessorVars;
	}

	public void setCoprocessorVars(Set<CoprocessorVar> coprocessorVars) {
		this.coprocessorVars = coprocessorVars;
	}

	public int getVerison() {
		return verison;
	}

	public void setVerison(int verison) {
		this.verison = verison;
	}

	public int getCoprocessorId() {
		return coprocessorId;
	}

	public void setCoprocessorId(int coprocessorId) {
		this.coprocessorId = coprocessorId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

}
