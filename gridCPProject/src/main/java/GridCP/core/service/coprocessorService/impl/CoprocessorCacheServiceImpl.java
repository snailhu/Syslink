package GridCP.core.service.coprocessorService.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import GridCP.core.dto.commonDto.ModelCacheDto;
import GridCP.core.service.coprocessorService.CoprocessorCacheService;

@Service
public class CoprocessorCacheServiceImpl implements CoprocessorCacheService {

	Set<ModelCacheDto> modelCacheDtos = new HashSet<ModelCacheDto>();
	
	@Override
	@CachePut(value = "coprocessor", key = "#model.sessionId")
	public ModelCacheDto save(ModelCacheDto model) {
		modelCacheDtos.add(model);
		return model;
	}

	@Override
	@CacheEvict(value = "coprocessor", key = "#model.sessionId")
	public ModelCacheDto delete(ModelCacheDto model) {
		modelCacheDtos.remove(model);
		return model;
	}

	@Override
	@CacheEvict(value = "coprocessor", allEntries = true)
	public void deleteAll() {
		modelCacheDtos.clear();
	}

	@Override
	@CachePut(value = "coprocessor", key = "#model.sessionId")
	public ModelCacheDto update(ModelCacheDto model) {
		modelCacheDtos.remove(model);
		modelCacheDtos.add(model);
		return model;
	}

	@Override
	@Cacheable(value = "coprocessor", key = "#sessionId")
	public ModelCacheDto findBySessionId(String sessionId) {
		System.out.println("cache miss, invoke find by id, id:" + sessionId);
		for (ModelCacheDto modelCacheDto : modelCacheDtos) {
			if(modelCacheDto.getSessionId().equals(sessionId)){
				return modelCacheDto;
			}
		}
		return null;
	}

}
