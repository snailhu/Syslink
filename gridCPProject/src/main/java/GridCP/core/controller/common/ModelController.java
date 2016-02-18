package GridCP.core.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.service.common.ModelPackageService;
import GridCP.core.service.common.ModelService;

@Controller
@RequestMapping(value = "/model", produces = "application/json;charset=utf-8")
public class ModelController {

	@Resource
	private ModelPackageService modelPackageService;
	@Resource
	private ModelService modelService;
	
	@RequestMapping(value = "/uploadModel", method = { RequestMethod.POST })
	public String uploadModel(
			@RequestParam(value = "file_name", required = false) MultipartFile file,
			@RequestParam(value="pkg_id",required = false) Integer pkg_id
			) throws Exception {
//		coprocessorService.saveCoprocesorByParseXML(pkg_id, file.getInputStream());
//		modelService.saveModelInfoByParseXML(pkg_id, "", file.getInputStream());
		modelService.saveModelInfoByParseZip(pkg_id, null, file.getInputStream());
		return "redirect:/model/getModeList/"+pkg_id+"/";
	}
//	@RequestMapping(value = "/getModeList/{id}/")
//	public ModelAndView getModeList(
//			@PathVariable Integer id 
//			) throws IOException {							
//		List<FlowModelDto> modelDtos=  coprocessorService.getFlowComponentListByModelPackageId(id);
//		ModelAndView mv =new ModelAndView("showModel");
//		List<ParentDto> pds = modelPackageService.getAllParentsByPkgId(id);
//		mv.addObject("parentList",pds);
//		mv.addObject("flag",0);
//		mv.addObject("modelDtos",modelDtos);
//		return mv;			
//	}
//	
//	@RequestMapping(value = "/getModeListChild/{id}/")
//	public ModelAndView getModeListChild(
//			@PathVariable Integer id 
//			) throws IOException {							
//		System.out.println(new Date() + "--getModeListChild--");
//		FlowModelDto modelDtos=  coprocessorService.getFlowModelListByFlowModelId(id);
//		if(modelDtos.getGlobalVars() == null){
//			modelDtos.setGlobalVars(new ArrayList<FlowModelVarDto>());
//		}else{
//			System.out.println(" + modelDtos.getGlobalVars().size()--" + modelDtos.getGlobalVars().size());
//			for(FlowModelVarDto var : modelDtos.getGlobalVars()){
//				System.out.println(var);
//			}
//		}
//		List<ParentDto> pds = coprocessorService.getAllParentsByFlowModelId(id);
//		ModelAndView mv =new ModelAndView("showModel");
//		mv.addObject("flag",1);
//		mv.addObject("parentList",pds);
//		mv.addObject("modelDtos",modelDtos);
//		return mv;			
//	}
	
	@RequestMapping(value = "/getModeList/{id}/")
	public ModelAndView getModelList(
			@PathVariable Integer id 
			) throws IOException {	
		System.out.println(new Date() + "--getModeList--");
		List<ModelDto> modelDtos = modelService.getModelListByModelPackageId(id);
		for (ModelDto modelDto : modelDtos) {
			System.out.println(modelDto);
		}
		System.out.println();
		ModelAndView mv =new ModelAndView("showModel_new");
		List<ParentDto> pds = modelPackageService.getAllParentsByPkgId(id);
		Collections.reverse(pds);
		mv.addObject("parentList",pds);
		//标志从包中获取模型列表
		mv.addObject("flag",1);
		//是否存在变量
		mv.addObject("modelVarCount", 0);
//		String modelVarJSON = JSON.toJSONString(new ArrayList<ModelVarDto>());
//		mv.addObject("modelVarJSON", modelVarJSON);
		mv.addObject("modelDtos",modelDtos);
		return mv;			
	}
	@RequestMapping(value = "/getModelListChild/{id}/{className}/")
	public ModelAndView getModeListChild(
			@PathVariable Integer id ,
			@PathVariable String className 
			) throws IOException {							
		System.out.println(new Date() + "--getModeListChild--");
		System.out.println("id: " + id);
		System.out.println("className: " + className);
		ModelDto modelDto = modelService.getChildrenModelListByModelId(id, className);
		if(modelDto.getModels() == null){
			modelDto.setModels(new ArrayList<ModelDto>());
		}
		ModelAndView mv =new ModelAndView("showModel_new");
		//标志从模型中获取子模型列表
		mv.addObject("flag",2);
		//从子模型到父模型
		List<ParentDto> pds = modelPackageService.getAllParentsByModelId(id, className);
		Collections.reverse(pds);
		mv.addObject("parentList",pds);
		mv.addObject("modelId", id);
		mv.addObject("className", className);
		String modelVarJSON = "";
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName()) || 
				className.equals(CoprocessorModel.class.getSimpleName())	){
			List<TreeGridDto> varTrees = modelService.getModelComponentVarsList(id, className);
			modelVarJSON = JSON.toJSONString(varTrees);
			mv.addObject("modelVarCount",varTrees.size());
			mv.addObject("modelVarJSON", modelVarJSON);
		}else{
			modelVarJSON = JSON.toJSONString(new ArrayList<TreeGridDto>());
			mv.addObject("modelVarCount",0);
		}
		mv.addObject("modelDto",modelDto);
		
//		List<ModelVarDto> modelVarDtos = modelService.getModelVarListByModelPackageId(id, className);
//		String modelVarJSON = JSON.toJSONString(modelVarDtos);
//		System.out.println("modelVarJSON--");
//		System.out.println(modelVarJSON);
//		mv.addObject("modelVarJSON", modelVarJSON);
//		mv.addObject("modelVarCount", modelVarDtos.size());
		return mv;			
	}
	
	@RequestMapping(value = "/getModelVarList/{id}/{className}/")
	public ModelAndView getModeVarList(
			@PathVariable Integer id ,
			@PathVariable String className 
			) {							
		System.out.println(new Date() + "--getModeVarListChild--");
		System.out.println("id: " + id);
		System.out.println("className: " + className);
		List<ModelVarDto> modelVarDtos = modelService.getModelVarListByModelPackageId(id, className);
		for (ModelVarDto var : modelVarDtos) {
			System.out.println(var);
		}
		System.out.println();
//		List<ParentDto> pds = coprocessorService.getAllParentsByFlowModelId(id);
		ModelAndView mv =new ModelAndView("showModel_new");
//		Collections.reverse(pds);
//		mv.addObject("flag",1);
//		mv.addObject("parentList",pds);
		mv.addObject("modelVarDtos",modelVarDtos);
		return mv;			
	}
		
	@RequestMapping(value = "/searchModel", method = { RequestMethod.POST })
	public ModelAndView searchModel(
			@RequestParam(value="modelName",required = true) String modelName){
		System.out.println("come in searchModel");
		List<ModelDto> modelDtos = modelService.getModelDtoListByModelName(modelName);
		for (ModelDto modelDto : modelDtos) {
			System.out.println(modelDto);
		}
		System.out.println();
		ModelAndView mv =new ModelAndView("showModel_new");
		//标志从包中获取模型列表
		mv.addObject("flag",1);
		//是否存在变量
		mv.addObject("modelVarCount", 0);
		mv.addObject("modelDtos",modelDtos);
		return mv;		
	}
}
