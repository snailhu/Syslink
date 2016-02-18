package GridCP.core.controller.common;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import GridCP.core.common.Config;
import GridCP.core.dto.commonDto.PackageTypeDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.gogsDomain.User;
import GridCP.core.modelMeta.commonMeta.PackageMeta;
import GridCP.core.result.SubmitResultInfo;
import GridCP.core.service.common.ModelPackageService;
import GridCP.core.service.common.PackageTypeService;
import GridCP.util.ResultUtil;

@Controller
@RequestMapping(value = "/modelPackage", produces = "application/json;charset=utf-8")
public class ModelPackageController {

	@Resource
	private ModelPackageService modelPackageService;
	@Resource
	private PackageTypeService packageTypeService;

	@RequestMapping(value = "/getpkgTypeList")
	@ResponseBody
	public ModelAndView getpkgTypeList(HttpServletRequest request) {
//		System.out.println("getpkgTypeList....");
		ModelAndView mv = new ModelAndView("modelPackageType");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<PackageTypeDto> list = packageTypeService.getPackageTypeList(user.getId());
		String pkgTypeListJSON = JSON.toJSONString(list);
		System.out.println(pkgTypeListJSON);
		mv.addObject("pkgTypeListJSON", pkgTypeListJSON);
		return mv;
		
	}
	
	@RequestMapping(value = "/save_pkgType", method = { RequestMethod.POST })
	@ResponseBody
	public int save_pkgType(
			HttpServletRequest request,
			@RequestParam(value="type_name",required = true) String  type_name,
			@RequestParam(value="type_des",required = false) String type_des){
		PackageTypeDto type = new PackageTypeDto();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		type.setName(type_name);
		type.setDescription(type_des);
		type.setUserId(user.getId());
		int typeId = packageTypeService.savePackageType(type);
		return typeId;
	}
	
	@RequestMapping(value = "/delete_pkgType", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo delete_pkgType(
			@RequestParam(value="type_id",required = true) Integer pkgTypeId){
//		System.out.println("delete_pkgType.." + pkgTypeId);
		packageTypeService.deletePackageType(pkgTypeId);			
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/edit_pkgType", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo edit_pkgType(
			@RequestParam(value="type_id",required = true) Integer  type_id,
			@RequestParam(value="type_name",required = true) String  type_name,
			@RequestParam(value="type_des",required = false) String type_des){
		PackageTypeDto type = new PackageTypeDto();
		type.setTypeId(type_id);
		type.setName(type_name);
		type.setDescription(type_des);
		packageTypeService.updatePackageType(type);
		
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/save_pkg", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo save_pkg(
			@RequestParam(value="pkg_des",required = false) String pkg_des,
			@RequestParam(value="pkg_name",required = false) String  pkg_name,
			@RequestParam(value="pkg_select",required = false) Integer  pkg_select,
			@RequestParam(value="pkg_type",required = false) Integer  pkg_type_id,
			HttpServletRequest request) {
		if(pkg_name !=null){
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			PackageMeta pd = new PackageMeta();
			pd.setName(pkg_name);
			pd.setDescription(pkg_des);
			pd.setParentId(pkg_select);		
			pd.setPackageTypeId(1);
			pd.setUserId(user.getId());
			modelPackageService.savePackage(pd);			
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/delete_pkg", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo delete_pkg(
			@RequestParam(value="pkgId",required = true) Integer pkgId){
		modelPackageService.deletePackage(pkgId);			
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/getInipkg", method = { RequestMethod.POST })
	@ResponseBody
	public List<EasyuiTreeNode> getInipkg(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return modelPackageService.getModelTreeList(user.getId());
	}


}
