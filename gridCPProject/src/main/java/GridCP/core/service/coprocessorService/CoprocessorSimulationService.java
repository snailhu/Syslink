package GridCP.core.service.coprocessorService;

import java.util.List;

import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.simulationVarTreeDto;
import GridCP.core.dto.coprocessor.FlowModelResultDto;
import GridCP.core.dto.coprocessor.FlowModelRunDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;

public interface CoprocessorSimulationService {

	public void simulationFlowModel(int modelId);
	
	public List<ModelDto> simulationFlowModel(int modelId, List<ModelDto> modelDtos);
	
	public void simulationFlowModel2(int modelId, List<TreeGridDto> varList);
	
	public simulationVarTreeDto getLastSimulation(int modelId);
	
	public List<FlowModelRunDto> getFlowModelRunList(int modelId);
	
	public List<FlowModelResultDto> getFlowModelRunResultList(int modelId,int runNum);
}
