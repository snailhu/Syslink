package GridCP.core.service.modelicaService;

import GridCP.core.domain.modelica.ModelicaResult;
/**
 * modelica 仿真操作
 * @author Snail
 *
 */

public interface ModelicaSimulationService {
	
	public ModelicaResult getModelicaResult();
	
	public void configSimulation();
	
	public void calculateSimulation();
	
}
