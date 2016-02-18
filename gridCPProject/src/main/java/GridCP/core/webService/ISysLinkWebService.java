package GridCP.core.webService;

import java.util.List;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.commonMeta.PackageTreeMeta;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

@WebService
@BindingType(value=SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public interface ISysLinkWebService {

	/**
	 * 获取树形列表根目录
	 * @param token 票据
	 * */
	public List<PackageTreeMeta> getRootPackage(String token);
	
	/**
	 * 根据父节点Id获取树形列表目录
	 * @param parentId 父节点Id
	 * @param token 票据
	 * */
	public List<PackageTreeMeta> getPackageByParentId(int parentId, String token);
	
	/**
	 * 获取树形列表整个树目录结构
	 * @param token 票据
	 * */
	public List<PackageTreeMeta> getPackageTree(String token);
	
	/**
	 * 获取某个包下面的模型
	 * @param packageId 
	 * @param type：flowModel、modelicaModel
	 * @param token 票据
	 * */
	public List<ModelMeta> getModelListByPackageId(int packageId, String type, String token);
	
	/**
	 * 接收文件，一次接收一小块
	 * @param modelPackageId 
	 * @param begin 开始位置
	 * @param length 长度
	 * @param data 传递过来的数据
	 * @param token 票据
	 * @return true == 接收成功
	 */
//	public boolean receiveModel(int modelPackageId, long begin, long length, DataHandler data, String token);
	
	public boolean receiveModel(int modelPackageId, long begin, long length, byte[] data, String token);

	/**
	 *用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return (token 票据)  (0 用户名不存在) (1 密码错误)
	 */
	public String userLogin(String username,String password);
	
	/**
	 *用户注销
	 * @param username 用户名
	 * @param token 票据
	 * @return true=成功
	 */
	public boolean userLogout(String username,String token);
}
