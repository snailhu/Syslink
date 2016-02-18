package GridCP.core.service.coprocessorService;

import GridCP.core.dto.commonDto.ModelCacheDto;

public interface CoprocessorCacheService {

	public ModelCacheDto save(ModelCacheDto model);
	
	public ModelCacheDto delete(ModelCacheDto model);
	
	public void deleteAll();
	
	public ModelCacheDto update(ModelCacheDto model);
	
	public ModelCacheDto findBySessionId(String sessionId);
}
