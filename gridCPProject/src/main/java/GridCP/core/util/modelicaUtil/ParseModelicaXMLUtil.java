package GridCP.core.util.modelicaUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import GridCP.core.modelMeta.modellicaModelMeta.ModelicaComponentMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaComponentVariableMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaPackageMeta;
import GridCP.core.util.ParseXMLUtil;



public class ParseModelicaXMLUtil {

	public static ModelicaPackageMeta getPackageModel(InputStream stream){
		Element rootElem = ParseXMLUtil.getRootElement(stream);
		return getPackageModel(rootElem);
	}
	public static ModelicaPackageMeta getPackageModel(String path){
		path = path.replace('\\', '/');
		Element rootElem = ParseXMLUtil.getRootElement(path);
		return getPackageModel(rootElem);
	}
	public static ModelicaPackageMeta getPackageModel(Element rootElem){
		ModelicaPackageMeta mPackage = new ModelicaPackageMeta();
		String classRestriction = rootElem.attributeValue("ClassRestriction");
		String description = rootElem.attributeValue("Description");
		String URl = rootElem.attributeValue("URL");
		mPackage.setClassRestriction(classRestriction);
		mPackage.setDescription(description);
		mPackage.setURL(URl);
		mPackage.setName(rootElem.getName());
		//获取包下的模型信息
		List<Element> modelElemList = rootElem.elements("model");
		if(modelElemList != null && modelElemList.size() > 0){
			List<ModelicaModelMeta> modelList = new ArrayList<ModelicaModelMeta>();
			ModelicaModelMeta model = null;
			List<ModelicaComponentMeta> componentList = null;
			ModelicaComponentMeta component = null;
			for (Element modelElem : modelElemList) {
				model = new ModelicaModelMeta();
				String modelName = modelElem.attribute("Name").getValue();
				String modelDes = modelElem.attributeValue("Description");
				model.setName(modelName);
				model.setDescription(modelDes);
				//获取模型组件信息
				List<Element> componentElemList = modelElem.elements("Component");
				componentList = new ArrayList<ModelicaComponentMeta>();
				for (Element componentElem : componentElemList) {
					component = ParseModelicaXMLUtil.getModelicaComponent(componentElem);
					componentList.add(component);
				}
				model.setComponents(componentList);
				modelList.add(model);
			}
			mPackage.setModels(modelList);
		}
		return mPackage;
	}
	private static ModelicaComponentMeta getModelicaComponent(Element componentElem){
		ModelicaComponentMeta component = new ModelicaComponentMeta();
		String cName = componentElem.attributeValue("Name");
		String cDes = componentElem.attributeValue("Description");
		String cClassName = componentElem.attributeValue("ClassName");
		String cClassDes = componentElem.attributeValue("ClassDes");
		component.setName(cName);
		component.setDescription(cDes);
		component.setClassName(cClassName);
		component.setClassDes(cClassDes);
		List<Element> varElemList = componentElem.elements("Variable");
		if(varElemList != null && varElemList.size() > 0){
			List<ModelicaComponentVariableMeta> varList = new ArrayList<ModelicaComponentVariableMeta>();
			ModelicaComponentVariableMeta var = null;
			for (Element varElem : varElemList) {
				var = new ModelicaComponentVariableMeta();
				String varName = varElem.attributeValue("Name");
				String varPrefixes = varElem.attributeValue("Prefixes");
				String varValue  = varElem.attributeValue("Start");
				String varUnit = varElem.attributeValue("Unit");
				String varDes = varElem.attributeValue("Description");
				var.setVarName(varName);
				var.setPrefixes(varPrefixes);
				var.setValue(varValue);
				var.setUnits(varUnit);
				var.setDescription(varDes);
				varList.add(var);
			}
			component.setVars(varList);
		}
		
		List<Element> componentElemList = componentElem.elements("Component");
		if(componentElemList != null && componentElemList.size() > 0){
			List<ModelicaComponentMeta> componentChildList = new ArrayList<ModelicaComponentMeta>();
			ModelicaComponentMeta componentChild = null;
			for (Element cElem : componentElemList) {
				componentChild = ParseModelicaXMLUtil.getModelicaComponent(cElem);
				componentChildList.add(componentChild);
			}
			component.setComponents(componentChildList);
		}
		return component;
	}
}
