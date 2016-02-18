package GridCP.core.dto.commonDto;

import java.util.List;

public class ModelCacheDto {

	private String sessionId;
	
	private List<ModelDto> modelDtos;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<ModelDto> getModelDtos() {
		return modelDtos;
	}

	public void setModelDtos(List<ModelDto> modelDtos) {
		this.modelDtos = modelDtos;
	}

		
	
}
