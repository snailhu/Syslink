package GridCP.core.modelMeta.modellicaModelMeta;

import java.util.List;

public class ModelicaPackageMeta {

	private String name;
	
	private String classRestriction;
	
	private String description;
	
	private String URL;
	
	private int modelPackageId;
	
	private String pkgSVGUrl;
	
	private List<ModelicaModelMeta> models;
	
	private List<ModelicaPackageMeta> paskages;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassRestriction() {
		return classRestriction;
	}

	public void setClassRestriction(String classRestriction) {
		this.classRestriction = classRestriction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public String getPkgSVGUrl() {
		return pkgSVGUrl;
	}

	public void setPkgSVGUrl(String pkgSVGUrl) {
		this.pkgSVGUrl = pkgSVGUrl;
	}

	public List<ModelicaModelMeta> getModels() {
		return models;
	}

	public void setModels(List<ModelicaModelMeta> models) {
		this.models = models;
	}

	public List<ModelicaPackageMeta> getPaskages() {
		return paskages;
	}

	public void setPaskages(List<ModelicaPackageMeta> paskages) {
		this.paskages = paskages;
	}




	
}
