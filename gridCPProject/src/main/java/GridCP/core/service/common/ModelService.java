package GridCP.core.service.common;

import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;

import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;

public interface ModelService {

	/**
	 * @param modelPackageId 包Id
	 * @param type xml类型
	 * @param inputStream xml输入流
	 * @return 
	 * @throws Exception 
	 * */
	public boolean saveModelInfoByParseZip(int modelPackageId,String type, DataHandler handler) throws Exception;
	
	public boolean saveModelInfoByParseZip(int modelPackageId,String type, InputStream inputStream) throws Exception;
	
	public boolean saveModelInfoByParseZip(int modelPackageId,String type, long begin, long length, byte[] data) throws Exception;
	/**
	 * @param modelPackageId 包Id
	 * @param type xml类型
	 * @param inputStream xml输入流
	 * @return 
	 * */
	public boolean saveModelInfoByParseXML(int modelPackageId,String type,InputStream inputStream);
	
	/**
	 * 根据包id 获取所有模型列表
	 * @param modelPackageId
	 */
	public List<ModelDto> getModelListByModelPackageId(int modelPackageId);
	/**
	 * 根据包id 获取所有子模型列表
	 * @param modelId
	 * @param type 模型类型：modelicaModel、flowModel
	 */
	public List<ModelDto> getChildrenModelListByModelId(int modelId, String className, String type);
	
	public ModelDto getChildrenModelListByModelId(int modelId, String className);
	/**
	 * 根据包id 获取所有模型变量
	 * @param modelId
	 */
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId,String className);
	
	/**
	 * 根据模型名  获取模型列表
	 * @param name
	 */
	public List<ModelDto> getModelDtoListByModelName(String name);
	
	public List<TreeGridDto> getModelComponentVarsList(int modelId,String className);
	
}
