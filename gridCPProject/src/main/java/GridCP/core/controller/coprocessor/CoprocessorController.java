package GridCP.core.controller.coprocessor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.result.ExceptionResultInfo;
import GridCP.core.service.coprocessorService.CoprocessorService;

@Controller
@RequestMapping(value = "/coprocessor", produces = "application/json;charset=utf-8")
public class CoprocessorController {

	@Resource
	private CoprocessorService coprocessorService;
	
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String goTest() {
		return "test";
	}
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	public String upload(@RequestParam(value = "uploadfile", required = false) MultipartFile file) throws Exception {
		coprocessorService.saveCoprocesorByParseXML(file.getInputStream());
		return "flowTree";
	}
	
	/**
	 * 获取流程模型(包模型树)
	 * @return
	 */
	@RequestMapping(value = "/modelTree")
	@ResponseBody
	public List<EasyuiTreeNode> getTree() {				
		List<EasyuiTreeNode> trees =coprocessorService.getModelTree();
		return trees;
	}
	/**
	 * 获取流程组件变量树
	 * @return
	 * @throws ExceptionResultInfo 
	 */
	@RequestMapping(value = "/getComponetVarTree", method = RequestMethod.POST)
	@ResponseBody
	public  List<TreeGridDto> getComponetVarTree(@RequestParam(value="orgId",required = false) Integer modelId ){				
		List<TreeGridDto> dtos = null;
		if(modelId == null || modelId == 0){			
			return dtos =new ArrayList<TreeGridDto>();
		}else{
			return coprocessorService.getFlowComponentListByFlowModelId(modelId);	
		}
		
	}
	/**
	 * 进入流程模型操作界面
	 * @return
	 */
	@RequestMapping(value = "/showTypes")
	public String showTypes() {				
		
		return "flowTree";
	}
}
