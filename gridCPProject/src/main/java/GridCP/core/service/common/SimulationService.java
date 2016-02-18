package GridCP.core.service.common;

import java.util.List;

import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.simulationVarTreeDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;

public interface SimulationService {
	
	public void saveModelVarInCache(String sessionId, ModelDto modelDto);

	public void simulationModel(String sessionId, ModelDto modelDto);
	
	public void simulationModel(int modelId, String className, List<TreeGridDto> varList);
	
	public simulationVarTreeDto getLastSimulation(int modelId,String className);
}
