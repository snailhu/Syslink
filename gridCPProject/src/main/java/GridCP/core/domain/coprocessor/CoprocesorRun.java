package GridCP.core.domain.coprocessor;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 流程模型运行记录实体
 * @author Shenwp
 *
 */
@Entity
@Table(name="t_coprocesorRun")
public class CoprocesorRun {

	@Id
	@GeneratedValue
	private int runId;
	
	/**运行记录对应的流程模型*/
	@ManyToOne(optional = true)
	@JoinColumn(name = "coprocessor_id")
	private CoprocessorModel coprocessorModel;
	
	/**运行次数*/
	@Column
	private int runNum;
	
	/**运行时间*/
	@Column
	private String runTime;
	
	/**运行状态*/
	@Column
	private String runStatus;
	
	/**运行结果路径*/
	@Column
	private String resultPath;
	
	/**运行描述*/
	@Column
	private String description;
	
	/**开始运行时间*/
	@Column
	private Date createDate;
	
	/**该模型对应的运行参数配置 */
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="coprocesorRun") 
	private Set<CoprocessorResult> coprocessorResults;

	public Set<CoprocessorResult> getCoprocessorResults() {
		return coprocessorResults;
	}

	public void setCoprocessorResults(Set<CoprocessorResult> coprocessorResults) {
		this.coprocessorResults = coprocessorResults;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public CoprocessorModel getCoprocessorModel() {
		return coprocessorModel;
	}

	public void setCoprocessorModel(CoprocessorModel coprocessorModel) {
		this.coprocessorModel = coprocessorModel;
	}

	public int getRunNum() {
		return runNum;
	}

	public void setRunNum(int runNum) {
		this.runNum = runNum;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
