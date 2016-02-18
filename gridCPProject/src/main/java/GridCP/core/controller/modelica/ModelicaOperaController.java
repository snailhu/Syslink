package GridCP.core.controller.modelica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.domain.modelica.ModelicaPkg;
import GridCP.core.dto.modelicaDto.ModelTreeDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiDataGridJson;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.service.coprocessorService.CoprocessorService;
import GridCP.core.service.modelicaService.ModelicaModelAndComponentService;

@Controller
@RequestMapping(value = "/modelica", produces = "application/json;charset=utf-8")
public class ModelicaOperaController {
	
	@Resource 
	private ModelicaModelAndComponentService modelicaModelAndComponentService;
	
	@Resource
	private CoprocessorService coprocessorService;
	
	@RequestMapping(value = "/showTypes", method = { RequestMethod.GET })
	public ModelAndView  showTypes() {
		 ModelAndView mav = new ModelAndView("modelTree");
        mav.addObject("flag", 0);
        return mav;
	}
	
	@RequestMapping(value="/getModelTree",method = { RequestMethod.POST })
	@ResponseBody
	public List<ModelTreeDto> getModelTree(@RequestParam("parentId") String parentId)throws Exception{
		
		return null;
	}
	@RequestMapping(value = "/upload", method = { RequestMethod.POST })
	public String upload(@RequestParam(value = "uploadfile", required = false) MultipartFile file) throws IOException {

		modelicaModelAndComponentService.saveModelicaModelByParseXML(file.getInputStream());
		return "modelTree";
	}
	@RequestMapping("/modelTree")
	@ResponseBody
	public List<EasyuiTreeNode> modelTree() {
		List<EasyuiTreeNode> treeNodes = modelicaModelAndComponentService.getModelTree();
		return treeNodes;
	}

	@RequestMapping(value="/getModelTreetest",method = { RequestMethod.GET })
	public String getModelTreetest(@RequestParam("parentId") String parentId)throws Exception{
		
		return "testTree";
	}
	
	@RequestMapping(value="/getPackge",method = { RequestMethod.GET })
	@ResponseBody
	public List<ModelTreeDto> getPackge(@RequestParam("parentPkgId") int parentPkgId)throws Exception{		
		List<ModelTreeDto> items = new ArrayList<ModelTreeDto>();
		ModelTreeDto Pkgtree = new ModelTreeDto();
		ModelicaPkg modelicaPkg = modelicaModelAndComponentService.getModelicaPkg(parentPkgId);
		Pkgtree.setLabel(modelicaPkg.getName());
		Pkgtree.setId(modelicaPkg.getId());
		Pkgtree.setParentId(0);
		if(modelicaModelAndComponentService.getModelsByPkgId(modelicaPkg.getId())!=null){
			List<ModelTreeDto> itemstest = new ArrayList<ModelTreeDto>();
			ModelTreeDto treeDto = new ModelTreeDto();
			treeDto.setValue("getModelBypkgId?parentPkgId="+modelicaPkg.getId());
			treeDto.setLabel("Loading...");
			itemstest.add(treeDto);
			Pkgtree.setItems(itemstest);			
		}		
		items.add(Pkgtree);
		return items;		
	}
	
	@RequestMapping(value="/getModelBypkgId",method = { RequestMethod.GET })
	@ResponseBody
	public List<ModelTreeDto> getModelBypkgId(@RequestParam("parentPkgId") int parentPkgId)throws Exception{	
		List<ModelTreeDto> items = new ArrayList<ModelTreeDto>();
		List<ModelicaModelAndComponent> mmac =modelicaModelAndComponentService.getModelsByPkgId(parentPkgId);			
		for(ModelicaModelAndComponent mc : mmac){
			ModelTreeDto Pkgtree = new ModelTreeDto();	
			Pkgtree.setLabel(mc.getModelName());
			Pkgtree.setId(mc.getId());
			Pkgtree.setParentId(mc.getParentId());
			if(modelicaModelAndComponentService.getModelsByParentId(mc.getId())!=null){
				List<ModelTreeDto> itemstest = new ArrayList<ModelTreeDto>();
				ModelTreeDto treeDto = new ModelTreeDto();
				treeDto.setValue("getChild?parentId="+mc.getId());
				treeDto.setLabel("Loading...");
				itemstest.add(treeDto);
				Pkgtree.setItems(itemstest);			
			}		
			items.add(Pkgtree);			
		}return items;		
	}
	
	
	
	@RequestMapping(value="/getChild",method = { RequestMethod.GET })
	@ResponseBody
	public List<ModelTreeDto> getChriend(@RequestParam("parentId") int parentId)throws Exception{
		
		List<ModelTreeDto> items = new ArrayList<ModelTreeDto>();
		List<ModelicaModelAndComponent> mmac =modelicaModelAndComponentService.getModelsByParentId(parentId);			
		for(ModelicaModelAndComponent mc : mmac){
			ModelTreeDto Pkgtree = new ModelTreeDto();	
			Pkgtree.setLabel(mc.getModelName());
			Pkgtree.setId(mc.getId());
			Pkgtree.setParentId(mc.getParentId());
			if(modelicaModelAndComponentService.getModelsByParentId(mc.getId())!=null){
				List<ModelTreeDto> itemstest = new ArrayList<ModelTreeDto>();
				ModelTreeDto treeDto = new ModelTreeDto();
				treeDto.setValue("getChild?parentId="+mc.getId());
				treeDto.setLabel("Loading...");
				itemstest.add(treeDto);
				Pkgtree.setItems(itemstest);			
			}		
			items.add(Pkgtree);			
		}return items;		
				
	}
	

	//获取用户列表
	@RequestMapping(value = "/componentVar", method = RequestMethod.POST)
	@ResponseBody
	public EasyuiDataGridJson getComponetVar(WebRequest request) {
		EasyuiDataGridJson json = new EasyuiDataGridJson();
//		String orgId = request.getParameter("orgId");
//		System.out.println("orgId: " + orgId);
//		if(orgId != null){
//			List<ModelicaComponentVariableDto> vars = modelicaModelAndComponentService.getComponentVars(Integer.parseInt(orgId));
//			if(vars != null){
//				json.setTotal((long)vars.size());
//				json.setRows(vars);
//			}			
//		}
		return json;
	}

	/**
	 * 获取组件变量树
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getComponetVarTree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeGridDto> getComponetVarTree(@RequestParam(value="orgId",required = false) Integer parentId) throws Exception {
		List<TreeGridDto> dtos = null;
		if(parentId == null || parentId==0){
			return dtos =  new ArrayList<TreeGridDto>();
		}
		return dtos = modelicaModelAndComponentService.getModelsByParentIdAndType(parentId, "component");			
	}
	
	@RequestMapping(value = "/showTest", method = { RequestMethod.GET })
	public String  showTest() {
		
        return "My";
	}
}
