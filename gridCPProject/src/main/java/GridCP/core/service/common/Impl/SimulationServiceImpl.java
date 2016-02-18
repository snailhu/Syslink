package GridCP.core.service.common.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.commonDto.simulationVarTreeDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.service.common.SimulationService;
import GridCP.core.service.coprocessorService.CoprocessorCacheService;
import GridCP.core.service.coprocessorService.CoprocessorSimulationService;

@Service
public class SimulationServiceImpl implements SimulationService{

	@Resource
	private CacheManager cacheManager;
	@Resource
	CoprocessorSimulationService coprocessorSimulationService;
	@Resource
	private CoprocessorCacheService coprocessorCacheService;

	@SuppressWarnings("unchecked")
	@Override
	public void saveModelVarInCache(String sessionId, ModelDto modelDto) {
		//根据缓存名字获取Cache
		Cache cache = cacheManager.getCache("ehcache");
		ValueWrapper valueWrapper = cache.get(sessionId);
		if(valueWrapper == null){
			List<ModelDto> models = new ArrayList<ModelDto>();
			models.add(modelDto);
			// 添加数据到缓存中
			cache.put(sessionId, models);
//			ModelCacheDto modelCache = new ModelCacheDto();
//			modelCache.setSessionId(sessionId);
//			modelCache.setModelDtos(models);
//			coprocessorCacheService.save(modelCache);
	    	
		}else{
			List<ModelDto> cacheModelDtoList = (List<ModelDto>) valueWrapper.get();
			for (ModelDto cacheModelDto : cacheModelDtoList) {
				if(cacheModelDto.getId() == modelDto.getId() && cacheModelDto.getClassName().equals(modelDto.getClassName())){
					List<ModelVarDto> cacheVars = cacheModelDto.getVars();
					if(cacheVars != null && cacheVars.size() > 0){
						Set<Integer> varIdSet = new HashSet<Integer>();
						Map<Integer,ModelVarDto> map = new HashMap<Integer,ModelVarDto>();
						for (ModelVarDto var : cacheVars) {
							varIdSet.add(var.getId());
							map.put(var.getId(), var);
						}
						for (ModelVarDto var : modelDto.getVars()) {
							if(varIdSet.contains(var.getId())){
								cacheVars.remove(map.get(var.getId()));
								cacheVars.add(var);
							}else{
								cacheVars.add(var);
							}
						}
						cacheModelDto.setVars(cacheVars);
					}else{
						cacheModelDto.setVars(modelDto.getVars());
					}
					cacheModelDtoList.add(cacheModelDto);
					break;
				}
			}
			cache.put(sessionId, cacheModelDtoList);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void simulationModel(String sessionId, ModelDto modelDto) {
		int modelId = modelDto.getId();
		String className = modelDto.getClassName();
		//根据缓存名字获取Cache
		Cache cache = cacheManager.getCache("ehcache");
		ValueWrapper valueWrapper = cache.get(sessionId);
		//直接跑模型
		if(valueWrapper == null){
			List<ModelVarDto> modelVars = modelDto.getVars();
			if(modelVars == null){
				//流程模型变量
				if(className.equals(CoprocessorModel.class.getSimpleName())){
					coprocessorSimulationService.simulationFlowModel(modelId);
				}
				//modelica
				if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
					
				}
			}else{
				//变量不为空 先将他保存到缓存中
				this.saveModelVarInCache(sessionId, modelDto);
			}
		}else{
			List<ModelDto> cacheModelDtoList = (List<ModelDto>) valueWrapper.get();
			//流程模型变量
			if(className.equals(CoprocessorModel.class.getSimpleName())){
				cacheModelDtoList = coprocessorSimulationService.simulationFlowModel(modelId,cacheModelDtoList);
				//更新缓存
				cache.put(sessionId, cacheModelDtoList);
			}
			//modelica
			if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
				
			}
			
		}
		
	}
	
	@Override
	public void simulationModel(int modelId, String className, List<TreeGridDto> varList){
		//流程模型变量
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			coprocessorSimulationService.simulationFlowModel2(modelId, varList);
		}
		//modelica
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			
		}
	}
	
	@Override
	public simulationVarTreeDto getLastSimulation(int modelId, String className) {
		//流程模型变量
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			
		}
		//modelica
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			
		}
		return null;
	}
}
