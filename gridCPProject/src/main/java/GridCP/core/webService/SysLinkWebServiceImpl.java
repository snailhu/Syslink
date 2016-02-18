package GridCP.core.webService;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.commonMeta.PackageTreeMeta;
import GridCP.core.service.common.ModelPackageService;
import GridCP.core.service.common.ModelService;

@Service
public class SysLinkWebServiceImpl implements ISysLinkWebService {

	@Resource
	private ModelPackageService modelPackageService;
	@Resource
	private ModelService modelService;	
	
	@Override
	public List<PackageTreeMeta> getRootPackage(String token) {
		List<PackageTreeMeta> list = modelPackageService.getRootPackage();
		return list;
	}
	@Override
	public List<PackageTreeMeta> getPackageByParentId(int parentId, String token) {
		List<PackageTreeMeta> list = modelPackageService.getPackageByParentId(parentId);
		return list;
	}
	
	@Override
	public List<PackageTreeMeta> getPackageTree(String token) {
		List<PackageTreeMeta> list = modelPackageService.getPackageTree();
		return list;
	}
	
	@Override
	public List<ModelMeta> getModelListByPackageId(int packageId, String type, String token) {
		List<ModelMeta> list = modelPackageService.getModelListByPackageId(packageId,type);
		return list;
	}
	
	@Override
	public boolean receiveModel(int modelPackageId, long begin,
			long length, byte[] data, String token) {
		try {
			modelService.saveModelInfoByParseZip(modelPackageId, null, begin, length, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public String userLogin(String username, String password) {
		System.out.println("userLogin..");
		System.out.println(modelService);
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		return null;
	}
	@Override
	public boolean userLogout(String username, String token) {
		
		return false;
	}


}
