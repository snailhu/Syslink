package GridCP.core.service.common;

import java.util.List;

import GridCP.core.dto.commonDto.PackageTypeDto;

public interface PackageTypeService {

	/**
	 * @param PackageTypeDto
	 * @return id
	 * */
	public int savePackageType(PackageTypeDto pkgType);
	
	/**
	 * @param id
	 * */
	public void deletePackageType(int typeId);
	
	/**
	 * @param PackageTypeDto
	 * @return 
	 * */
	public void updatePackageType(PackageTypeDto pkgType);
	
	/**
	 * 
	 * */
	public List<PackageTypeDto> getPackageTypeList(int userId);
	
	/**
	 * 
	 * */
	public boolean isExistPackageType(String name);
}
