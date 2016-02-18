package GridCP.core.service.common.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.dao.common.PackageTypeDao;
import GridCP.core.domain.common.ModelPackageType;
import GridCP.core.dto.commonDto.PackageTypeDto;
import GridCP.core.service.common.PackageTypeService;

@Service
public class PackageTypeServiceImpl implements PackageTypeService{

	@Resource
	private PackageTypeDao packageTypeDao;
	
	@Override
	@Transactional
	public int savePackageType(PackageTypeDto type) {
		ModelPackageType mtype = new ModelPackageType();
		mtype.setName(type.getName());
		mtype.setDescription(type.getDescription());
		mtype.setUserId(type.getUserId());
		mtype = packageTypeDao.add(mtype);
		return mtype.getId();
	}

	@Override
	@Transactional
	public void deletePackageType(int typeId) {
		packageTypeDao.delete(typeId);
	}

	@Override
	@Transactional
	public void updatePackageType(PackageTypeDto type) {
		ModelPackageType mtype = packageTypeDao.get(type.getTypeId());
		mtype.setName(type.getName());
		mtype.setDescription(type.getDescription());
		packageTypeDao.update(mtype);
	}

	@Override
	public List<PackageTypeDto> getPackageTypeList(int userId) {
		List<PackageTypeDto> pkgTypeDtoList = new ArrayList<PackageTypeDto>();
		List<ModelPackageType> mpkgTypeList = packageTypeDao.findByParam("userId", userId);
		if(mpkgTypeList != null && mpkgTypeList.size() > 0){
			PackageTypeDto pkgTypeDto = null;
			for (ModelPackageType mpkgType : mpkgTypeList) {
				pkgTypeDto = new PackageTypeDto();
				pkgTypeDto.setTypeId(mpkgType.getId());
				pkgTypeDto.setName(mpkgType.getName());
				pkgTypeDto.setDescription(mpkgType.getDescription());
				pkgTypeDto.setUserId(userId);
				pkgTypeDtoList.add(pkgTypeDto);
			}
		}
		return pkgTypeDtoList;
	}

	@Override
	public boolean isExistPackageType(String name) {
		List<ModelPackageType> list = packageTypeDao.findByParam("name", name);	
		if(list != null && list.size() > 0){
			return true;
		}
		return false;
	}

}
