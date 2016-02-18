package GridCP.core.util.flowUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dom4j.Element;

import GridCP.core.modelMeta.flowModelMeta.FlowComponentMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowComponentVariableMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowModelMeta;
import GridCP.core.util.ParseXMLUtil;

public class ParseFlowXMLUtil {

	public static FlowModelMeta getFlowModel(String path){
		path = path.replace('\\', '/');
		//获取流程组件xml根目录
		Element rootElem = ParseXMLUtil.getRootElement(path);
		return getFlowModel(rootElem);
	}
	public static FlowModelMeta getFlowModel(InputStream in){
		//获取流程组件xml根目录
		Element rootElem = ParseXMLUtil.getRootElement(in);
		return getFlowModel(rootElem);
	}
	public static FlowModelMeta getFlowModel(Element rootElem){
		FlowModelMeta flowModel = new FlowModelMeta();
		//获取流程模型节点
		Element flowElem = rootElem.element("Flow");
		//获取流程模型节点 类型属性
		String flowType = flowElem.attributeValue("FlowType");
		flowModel.setFlowType(flowType);
		//流程模型名称？
		flowModel.setFlowName(flowElem.getName());
		//获取组件信息
		List<Element> componentElemList = flowElem.element("Components").elements();
		if(componentElemList != null && componentElemList.size() > 0){
			List<FlowComponentMeta> componentList = new ArrayList<FlowComponentMeta>();
			FlowComponentMeta component = null;
			List<FlowComponentVariableMeta> componentVarList = null;
			FlowComponentVariableMeta var = null;
			for (Element componentElem : componentElemList) {
				component = new FlowComponentMeta();
				Element componentInfoElem = componentElem.element("GeneralInfo");
				String componentName = componentInfoElem.attributeValue("Name");
				String componentShowName = componentInfoElem.attributeValue("ShowName");
				String componentDescription = componentInfoElem.attributeValue("Description");
				component.setName(componentName);
				component.setShowName(componentShowName);
				component.setDescription(componentDescription);
				component.setType(componentElem.getName());
				List<Element> componentVarElemList = componentElem.element("Variables").elements();
				componentVarList = new ArrayList<FlowComponentVariableMeta>();
				for (Element componentVarElem : componentVarElemList) {
					var = new FlowComponentVariableMeta();
					String varName = componentVarElem.attributeValue("Name");
					String varFullName = componentVarElem.attributeValue("FullName");
					String varType = componentVarElem.attributeValue("Type");
					String varValue = componentVarElem.attributeValue("Value");
//					String varExpression = componentVarElem.attributeValue("Expression");
					String varUnit = componentVarElem.attributeValue("Unit");
					String varDescription = componentVarElem.attributeValue("Description");
					String varMaxValue = componentVarElem.attributeValue("MaxValue");
					String varMinValue = componentVarElem.attributeValue("MinValue");
					var.setVarName(varName);
					var.setFullName(varFullName);
					var.setType(varType);
					var.setValue(varValue);
					var.setUnits(varUnit);
					var.setDescription(varDescription);
					var.setMaxValue(varMaxValue);
					var.setMinValue(varMinValue);
					componentVarList.add(var);
				}
				component.setComponentVars(componentVarList);
				componentList.add(component);
			}
			flowModel.setComponents(componentList);
		}
		//获取全局变量信息
		Element globalVarElems = flowElem.element("Variables");
		if(globalVarElems != null){
			List<Element> globalVarElemList = globalVarElems.elements();
			if(globalVarElemList != null && globalVarElemList.size() > 0){
				List<FlowComponentVariableMeta> globalVarList = new ArrayList<FlowComponentVariableMeta>();
				FlowComponentVariableMeta var = null;
				for (Element globalVarElem : globalVarElemList) {
					var = new FlowComponentVariableMeta();
					String varName = globalVarElem.attributeValue("Name");
					String varFullName = globalVarElem.attributeValue("FullName");
					String varType = globalVarElem.attributeValue("Type");
					String varValue = globalVarElem.attributeValue("Value");
//			String varExpression = componentVarElem.attributeValue("Expression");
					String varUnit = globalVarElem.attributeValue("Unit");
					String varDescription = globalVarElem.attributeValue("Description");
					String varMaxValue = globalVarElem.attributeValue("MaxValue");
					String varMinValue = globalVarElem.attributeValue("MinValue");
					var.setVarName(varName);
					var.setFullName(varFullName);
					var.setType(varType);
					var.setValue(varValue);
					var.setUnits(varUnit);
					var.setDescription(varDescription);
					var.setMaxValue(varMaxValue);
					var.setMinValue(varMinValue);
					globalVarList.add(var);
				}
				flowModel.setGlobalVars(globalVarList);
			}
		}
		//流程模型描述信息
//		List<Element> annotationList = flowElem.element("Annotations").elements();
		return flowModel;
	}

	// 获取组件信息
	public static FlowComponentMeta getFlowComponent(String path) {
		Element rootElem = ParseXMLUtil.getRootElement(path);
		return getFlowComponent(rootElem);
	}
	// 获取组件信息
	public static FlowComponentMeta getFlowComponent(InputStream in) {
		Element rootElem = ParseXMLUtil.getRootElement(in);
		return getFlowComponent(rootElem);
	}
	private static FlowComponentMeta getFlowComponent(Element rootElem){
		FlowComponentMeta component = new FlowComponentMeta();
		Element componentInfoElem = rootElem.element("GeneralInfo");
		String componentName = componentInfoElem.element("Name").getText();
		String componentShowName = componentInfoElem.element("Author").getText();
		String componentDescription = componentInfoElem.element("Description").getText();
//		String componentRunOS = componentInfoElem.element("RunOS").getText();
		component.setName(componentName);
		component.setShowName(componentShowName);
		component.setDescription(componentDescription);
		component.setType(rootElem.attributeValue("Source"));
		
		Set<String> varNameSet = new HashSet<String>();
		List<Element> fileElemList = rootElem.element("Files").elements("File");
		if(fileElemList != null && fileElemList.size() > 0){
			List<FlowComponentVariableMeta> componentVarList = new ArrayList<FlowComponentVariableMeta>();
			FlowComponentVariableMeta var = null;
			for (Element fileElem : fileElemList) {
				List<Element> componentVarElemList = fileElem.elements("Variables");
				if(componentVarElemList != null){
					for (Element componentVarElem : componentVarElemList) {
						String varName = componentVarElem.attributeValue("Name");
						if(!varNameSet.contains(varName)){
							var = new FlowComponentVariableMeta();
							String varType = componentVarElem.attributeValue("Type");
							String varValue = componentVarElem.attributeValue("Value");
							String varUnit = componentVarElem.attributeValue("Unit");
							String varDescription = componentVarElem.attributeValue("Description");
							String varMaxValue = componentVarElem.attributeValue("MaxValue");
							String varMinValue = componentVarElem.attributeValue("MinValue");
							var.setVarName(varName);
//				var.setFullName(varFullName);
							var.setType(varType);
							var.setValue(varValue);
							var.setUnits(varUnit);
							var.setDescription(varDescription);
							var.setMaxValue(varMaxValue);
							var.setMinValue(varMinValue);
							componentVarList.add(var);
						}
					}
				}
			}
			component.setComponentVars(componentVarList);
		}
		return component;
	}
}
