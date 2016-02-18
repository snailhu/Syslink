package GridCP.core.domain.modelica;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Modeliaca 求解计算参数配置表
 * @author Snail
 *
 */
@Entity
@Table(name="t_modelicaCalculateConfig")
public class ModelicaCalculateConfig {
	/**ID*/
	@Id
	@GeneratedValue
	private int simulationId;
	
	/**该仿真参数所属的模型*/
	@ManyToOne()
	@JoinColumn(name = "modelica_id")
	private ModelicaModelAndComponent modelicaModelandComponent;
	
	/**仿真参数对应的结果*/
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	private ModelicaResult modelicaResult;
	
	/**启始时间*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	/**结束时间*/
	@Temporal(TemporalType.TIMESTAMP)
	private Date stopTime;
	
	/**步长值*/
	@Column(nullable = false)
	private float numOfStep;
	
	/**容错值*/
	@Column(nullable = false)
	private float tolerance;
	
	/**类型*/
	@Column(length = 30, nullable = false)
	private String algorithm;
	
	

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

	public int getSimulationId() {
		return simulationId;
	}

	public void setSimulationId(int simulationId) {
		this.simulationId = simulationId;
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

	public float getNumOfStep() {
		return numOfStep;
	}

	public void setNumOfStep(float numOfStep) {
		this.numOfStep = numOfStep;
	}

	public float getTolerance() {
		return tolerance;
	}

	public void setTolerance(float tolerance) {
		this.tolerance = tolerance;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	
}
