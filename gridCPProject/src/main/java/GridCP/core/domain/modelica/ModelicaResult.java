package GridCP.core.domain.modelica;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * modelica 仿真结果表
 * @author Snail
 *
 */
@Entity
@Table(name="t_modelicaResult")
public class ModelicaResult {
	
	@Id
	@GeneratedValue
	private int resultId;
	
	/**该结果对应的modelica求解参数配置*/
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "simulation_id", nullable = false)
	private ModelicaCalculateConfig modelicaCalculateConfig;
	
	/**该结果对应的参数变量配置*/
	@OneToMany(cascade=CascadeType.ALL,mappedBy="modelicaResult")
	private Set<ModelicaVar> modelicaVars;
	
	/**启始时间*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	/**结束时间*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopTime;
	
	/**结果文件路径*/
	@Column(length = 250)
	private String URL;

	

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public ModelicaCalculateConfig getModelicaCalculateConfig() {
		return modelicaCalculateConfig;
	}

	public void setModelicaCalculateConfig(
			ModelicaCalculateConfig modelicaCalculateConfig) {
		this.modelicaCalculateConfig = modelicaCalculateConfig;
	}

	public Set<ModelicaVar> getModelicaVars() {
		return modelicaVars;
	}

	public void setModelicaVars(Set<ModelicaVar> modelicaVars) {
		this.modelicaVars = modelicaVars;
	}
	
	
	
}
