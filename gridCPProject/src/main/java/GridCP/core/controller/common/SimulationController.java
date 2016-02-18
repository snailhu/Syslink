package GridCP.core.controller.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import GridCP.core.common.Config;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.JsonMessage;
import GridCP.core.result.SubmitResultInfo;
import GridCP.core.service.common.SimulationService;
import GridCP.core.service.coprocessorService.CoprocessorService;
import GridCP.util.ResultUtil;

@Controller
@RequestMapping(value = "/simulation", produces = "application/json;charset=utf-8")
public class SimulationController {

	@Resource
	private SimulationService simulationService;
	@Resource
	private CoprocessorService coprocessorService;
	
	@RequestMapping(value = "/saveModelVar", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo saveModelVar(
			@RequestParam(value="modelId",required = true) Integer modelId,
			@RequestParam(value="className",required = true) String className,
			@RequestParam(value="modelVarJSON",required = true) String modelVarJSON, WebRequest request){
		System.out.println(new Date() + "--saveModelVar--");
		System.out.println("modelId: " + modelId);
		System.out.println("className: " + className);
		System.out.println("modelVarJSON: " + modelVarJSON);
		System.out.println("httpSessionId: " + request.getSessionId());
		List<ModelVarDto> varList = JSON.parseArray(modelVarJSON, ModelVarDto.class);
		 for (ModelVarDto var : varList) {
			System.out.println(var);
		}
		if(varList != null && varList.size() > 0){
			ModelDto modelDto = new ModelDto();
			modelDto.setId(modelId);
			modelDto.setClassName(className);;
			modelDto.setVars(varList);
			simulationService.saveModelVarInCache(request.getSessionId(), modelDto);			
		}
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/modelSimulation", method = { RequestMethod.POST })
	@ResponseBody
	public SubmitResultInfo modelSimulation(
			@RequestParam(value="modelId",required = true) Integer modelId,
			@RequestParam(value="className",required = true) String className,
			@RequestParam(value="modelVarJSON",required = true) String modelVarJSON, WebRequest request){
		System.out.println(new Date() + "--modelSimulation--");
		System.out.println("modelId: " + modelId);
		System.out.println("className: " + className);
		System.out.println("modelVarJSON: " + modelVarJSON);
		System.out.println("httpSessionId: " + request.getSessionId());
		List<ModelVarDto> varList = JSON.parseArray(modelVarJSON, ModelVarDto.class);
		 for (ModelVarDto var : varList) {
			System.out.println(var);
		}
		ModelDto modelDto = new ModelDto();
		modelDto.setId(modelId);
		modelDto.setClassName(className);;
		if(varList != null && varList.size() > 0){
			modelDto.setVars(varList);			
		}
		simulationService.simulationModel(request.getSessionId(), modelDto);	
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));
	}
	
	@RequestMapping(value = "/modelSimulation2", method = { RequestMethod.POST })
	@ResponseBody
	public JsonMessage modelSimulation(
			@RequestParam(value="modelId",required = true) Integer modelId,
			@RequestParam(value="className",required = true) String className,
			@RequestParam(value="modelVarJSON",required = true) String modelVarJSON){
		System.out.println(new Date() + "--modelSimulation2--");
		System.out.println("modelId: " + modelId);
		System.out.println("className: " + className);
//		System.out.println("modelVarJSON: " + modelVarJSON);
		List<TreeGridDto> varList = JSON.parseArray(modelVarJSON, TreeGridDto.class);
		 for (TreeGridDto var : varList) {
			System.out.println(var);
		}
		simulationService.simulationModel(modelId, className, varList);
		JsonMessage jsonMsg = new JsonMessage();
		jsonMsg.setSuccess(true);
		jsonMsg.setMsg("wu");
		jsonMsg.setObj(modelVarJSON);
		return jsonMsg;
	}
	@RequestMapping(value = "/getModelSimulationList/{id}/{className}/")
	public ModelAndView getModelSimulationList(
			@PathVariable Integer id ,
			@PathVariable String className 
			){
		System.out.println(new Date() + "--getModelSimulationList--");
		System.out.println("id: " + id);
		System.out.println("className: " + className);
		ModelAndView mv =new ModelAndView("showSimulation");
		mv.addObject("modelId", id);
		List<TreeGridDto> list = new ArrayList<TreeGridDto>();
		list.addAll(coprocessorService.getFlowComponentListByFlowModelId(id));
		String modelVarJSON = JSON.toJSONString(list);
		mv.addObject("modelVarJSON", modelVarJSON);
		mv.addObject("className", className);
		return mv;
	}
	
	@RequestMapping(value = "/getModelVars/{modelId}", method = { RequestMethod.POST })
	@ResponseBody
	public List<TreeGridDto> modelSimulation(@PathVariable Integer modelId){
		List<TreeGridDto> list = new ArrayList<TreeGridDto>();
		System.out.println("--modelSimulation modelId: " + modelId);
//		TreeGridDto tree = coprocessorService.getFlowModelByFlowModelId(modelId);
//		list.add(tree);
		list.addAll(coprocessorService.getFlowComponentListByFlowModelId(modelId));
		return list;
	}
	@RequestMapping(value = "/getModelVarsToJSON/{modelId}")
	@ResponseBody
	public String modelSimulationToJSON(@PathVariable Integer modelId){
		List<TreeGridDto> list = new ArrayList<TreeGridDto>();
		System.out.println("--modelSimulationToJSON modelId: " + modelId);
//		TreeGridDto tree = coprocessorService.getFlowModelByFlowModelId(modelId);
//		list.add(tree);
		list.addAll(coprocessorService.getFlowComponentListByFlowModelId(modelId));
		String modelVarJSON = JSON.toJSONString(list);
		return modelVarJSON;
	}		
	
}
