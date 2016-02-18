package GridCP.core.service.coprocessorService.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import GridCP.core.dao.coprocessorDao.CoprocesorRunDao;
import GridCP.core.dao.coprocessorDao.CoprocessorModelDao;
import GridCP.core.dao.coprocessorDao.CoprocessorResultDao;
import GridCP.core.domain.coprocessor.CoprocesorRun;
import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.coprocessor.CoprocessorResult;
import GridCP.core.domain.coprocessor.CoprocessorVar;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.commonDto.simulationVarTreeDto;
import GridCP.core.dto.coprocessor.FlowModelResultDto;
import GridCP.core.dto.coprocessor.FlowModelRunDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.service.coprocessorService.CoprocessorSimulationService;

@Service
public class CoprocessorSimulationServiceImpl implements CoprocessorSimulationService {

	@Resource
	private CoprocessorModelDao coprocessorModelDao;
	@Resource
	private CoprocesorRunDao coprocesorRunDao;
	@Resource
	private CoprocessorResultDao coprocessorResultDao;
	
	@Override
	public void simulationFlowModel(int modelId) {
		System.out.println("SimulationFlowModel 1");
		CoprocessorModel model = coprocessorModelDao.get(modelId);
		int parentId = model.getParentId();
		//此为流程模型模型
		if(parentId == 0){
			CoprocesorRun run = new CoprocesorRun();
			CoprocesorRun lastRun = coprocesorRunDao.getLastRunCoprocesorRun(model.getCoprocessorId());
			if(lastRun != null){
				run.setRunNum(lastRun.getRunNum() + 1);
			}else{
				run.setRunNum(1);
			}
			run.setCoprocessorModel(model);
			run.setCreateDate(new Date());
			Set<CoprocessorResult> results = new HashSet<CoprocessorResult>();
			Set<CoprocessorVar> globalVars = model.getCoprocessorVars();
			if(globalVars != null && globalVars.size() > 0){
				CoprocessorResult result = null;
				for (CoprocessorVar var : globalVars) {
					result = new CoprocessorResult();
					result.setDescription(var.getDescription());
					result.setValue(var.getValue());
					result.setLowerBound(var.getLowerBound());
					result.setName(var.getName());
					result.setUnits(var.getUnits());
					result.setUpperBound(var.getUpperBound());
					results.add(result);
				}
			}
			run.setCoprocessorResults(results);
			coprocesorRunDao.add(run);
			//获取组件
			List<CoprocessorModel> modelChildren = coprocessorModelDao.findByParam("parentId", model.getCoprocessorId());
			if(modelChildren != null && modelChildren.size() > 0){
				for (CoprocessorModel component : modelChildren) {
					Set<CoprocessorVar> componentVars = component.getCoprocessorVars();
					if(componentVars != null && componentVars.size() > 0){
						CoprocessorResult result = null;
						for (CoprocessorVar var : componentVars) {
							result = new CoprocessorResult();
							result.setDescription(var.getDescription());
							result.setValue(var.getValue());
							result.setLowerBound(var.getLowerBound());
							result.setName(var.getName());
							result.setUnits(var.getUnits());
							result.setUpperBound(var.getUpperBound());
							result.setCoprocessorModel(component);
							result.setCoprocesorRun(run);
							coprocessorResultDao.add(result);
						}
					}
				}
			}
		}else{ //此为流程组件
			this.simulationFlowModel(parentId);
		}
	}
	
	@Override
	public List<ModelDto> simulationFlowModel(int modelId, List<ModelDto> modelDtos) {
		CoprocessorModel model = coprocessorModelDao.get(modelId);
		int parentId = model.getParentId();
		//此为流程模型模型
		if(parentId == 0){
			CoprocesorRun run = new CoprocesorRun();
			CoprocesorRun lastRun = coprocesorRunDao.getLastRunCoprocesorRun(model.getCoprocessorId());
			if(lastRun != null){
				run.setRunNum(lastRun.getRunNum() + 1);
			}else{
				run.setRunNum(1);
			}
			run.setCoprocessorModel(model);
			run.setCreateDate(new Date());
			Set<CoprocessorResult> results = new HashSet<CoprocessorResult>();
			for (ModelDto modelDto : modelDtos) {
				if(modelId == modelDto.getId() && 
					modelDto.getClassName().equals(CoprocessorModel.class.getSimpleName())){
					List<ModelVarDto> globalVars = modelDto.getVars();
					if(globalVars != null && globalVars.size() > 0){
						CoprocessorResult result = null;
						for (ModelVarDto var : globalVars) {
							result = new CoprocessorResult();
							result.setDescription(var.getDescription());
							result.setValue(var.getValue());
							result.setLowerBound(var.getMinValue());
							result.setName(var.getVarName());
							result.setUnits(var.getUnits());
							result.setUpperBound(var.getMaxValue());
							results.add(result);
						}
					}
					modelDtos.remove(modelDto);
					break;
				}
			}
		run.setCoprocessorResults(results);
		run = coprocesorRunDao.add(run);
		//获取组件
		List<CoprocessorModel> modelChildren = coprocessorModelDao.findByParam("parentId", model.getCoprocessorId());
		if(modelChildren != null && modelChildren.size() > 0){
			for (CoprocessorModel component : modelChildren) {
				for (ModelDto modelDto : modelDtos) {
					if(component.getCoprocessorId() == modelDto.getId() && 
							modelDto.getClassName().equals(CoprocessorModel.class.getSimpleName())){
						List<ModelVarDto> componentVars = modelDto.getVars();
						CoprocessorResult result = null;
						for (ModelVarDto var : componentVars) {
							result = new CoprocessorResult();
							result.setDescription(var.getDescription());
							result.setValue(var.getValue());
							result.setLowerBound(var.getMinValue());
							result.setName(var.getVarName());
							result.setUnits(var.getUnits());
							result.setUpperBound(var.getMaxValue());
							result.setCoprocessorModel(component);
							coprocessorResultDao.add(result);
						}
						modelDtos.remove(modelDto);
					}
				}
			}
		}
		return modelDtos;
		}else{//此为流程组件
			return this.simulationFlowModel(parentId, modelDtos);
		}
	}
	
	@Override
	public void simulationFlowModel2(int modelId, List<TreeGridDto> varList) {
		CoprocessorModel model = coprocessorModelDao.get(modelId);
		CoprocesorRun run = new CoprocesorRun();
		CoprocesorRun lastRun = coprocesorRunDao.getLastRunCoprocesorRun(model.getCoprocessorId());
		if(lastRun != null){
			run.setRunNum(lastRun.getRunNum() + 1);
		}else{
			run.setRunNum(1);
		}
		run.setCoprocessorModel(model);
		run.setCreateDate(new Date());
		Set<CoprocessorResult> results = new HashSet<CoprocessorResult>();
		CoprocessorResult result = null;
		CoprocessorModel component = null;
		for (TreeGridDto var : varList) {
			if(var.getType().equals("var")){
				result = new CoprocessorResult();
				component = new CoprocessorModel();
				component.setCoprocessorId(var.getParentId());
				result.setCoprocessorModel(component);
				result.setDescription(var.getDescription());
				result.setValue(var.getValue());
				result.setLowerBound(var.getMinValue());
				result.setName(var.getVarName());
				result.setUnits(var.getUnits());
				result.setUpperBound(var.getMaxValue());
				results.add(result);
			}
		}
	run.setCoprocessorResults(results);
	coprocesorRunDao.add(run);
	}
	
	@Override
	public simulationVarTreeDto getLastSimulation(int modelId) {
		CoprocessorModel model = coprocessorModelDao.get(modelId);
		CoprocesorRun run = coprocesorRunDao.getLastRunCoprocesorRun(modelId);
		Set<CoprocessorResult> resultSet = run.getCoprocessorResults();
		return null;
	}
	
	@Override
	public List<FlowModelRunDto> getFlowModelRunList(int modelId) {
		
		return null;
	}

	@Override
	public List<FlowModelResultDto> getFlowModelRunResultList(int modelId, int runNum) {
		
		return null;
	}


	
	
}
