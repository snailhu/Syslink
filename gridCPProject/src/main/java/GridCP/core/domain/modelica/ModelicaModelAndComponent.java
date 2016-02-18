package GridCP.core.domain.modelica;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Modelica模型组件实体
 * @author Snail
 *
 */
@Entity
@Table(name="t_modelicaModelAndComponent")
public class ModelicaModelAndComponent {
	/**模型组件id*/
	@Id
	@GeneratedValue
	private int id ;
	
	/**模型组件父id*/
	@Column(nullable = false)
	private int parentId;
	
	/**所属父包id*/
	private int parentPkgId;
	
	/**被哪些模型所引用*/
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "t_modelica_isQuoted", joinColumns = @JoinColumn(name = "modelica_id"), inverseJoinColumns = @JoinColumn(name = "idQuotedModelica_id"))
	private Set<ModelicaModelAndComponent> isQuotedModelicaModel;
	
	/**所引用的模型*/
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "t_modelica_isQuoted", joinColumns = @JoinColumn(name = "modelica_id"), inverseJoinColumns = @JoinColumn(name = "idQuotedModelica_id"))
	private Set<ModelicaModelAndComponent> QuotedModelicaModel;
	
	/**模型类型
	 * type=model||component
	 * */
	@Column(length=100,nullable = true)
	private String type;
	
	/**模型名称*/
	@Column(length=100,nullable = true)
	private String modelName;
	
	/**模型的文本路径*/
	@Column(length=100,nullable = true)
	private String textUrl;
	
	/**模型的图标路径*/
	@Column(length=100,nullable = true)
	private String IconUrl;
	
	/**模型说明*/
	@Column(length=2500,nullable = true)
	private String info;
	
	/**模型当前版本号*/
	@Column(nullable = true)
	private int version;
	
	/**版本库路径*/
	@Column(length=100,nullable = true)
	private String Url;
	
	/**SVG路径*/
	@Column(length=500,nullable = true)
	private String svgPath;

	/**该模型所拥有的求解计算参数配置表*/
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="modelicaModelandComponent")    
	private Set<ModelicaCalculateConfig> modelicaCalulateConfigs;
	
	/**该模型所拥有的仿真参数表*/
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="modelicaModelandComponent")    
	private Set<ModelicaVar> modelicaVars;
	
	
	public Set<ModelicaModelAndComponent> getQuotedModelicaModel() {
		return QuotedModelicaModel;
	}

	public void setQuotedModelicaModel(Set<ModelicaModelAndComponent> quotedModelicaModel) {
		QuotedModelicaModel = quotedModelicaModel;
	}

	
	public Set<ModelicaCalculateConfig> getModelicaCalulateConfigs() {
		return modelicaCalulateConfigs;
	}

	public void setModelicaCalulateConfigs(
			Set<ModelicaCalculateConfig> modelicaCalulateConfigs) {
		this.modelicaCalulateConfigs = modelicaCalulateConfigs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Set<ModelicaModelAndComponent> getIsQuotedModelicaModel() {
		return isQuotedModelicaModel;
	}

	public void setIsQuotedModelicaModel(Set<ModelicaModelAndComponent> isQuotedModelicaModel) {
		this.isQuotedModelicaModel = isQuotedModelicaModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTextUrl() {
		return textUrl;
	}

	public void setTextUrl(String textUrl) {
		this.textUrl = textUrl;
	}

	public String getIconUrl() {
		return IconUrl;
	}

	public void setIconUrl(String iconUrl) {
		IconUrl = iconUrl;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getParentPkgId() {
		return parentPkgId;
	}

	public void setParentPkgId(int parentPkgId) {
		this.parentPkgId = parentPkgId;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getSvgPath() {
		return svgPath;
	}

	public void setSvgPath(String svgPath) {
		this.svgPath = svgPath;
	}

	public Set<ModelicaVar> getModelicaVars() {
		return modelicaVars;
	}

	public void setModelicaVars(Set<ModelicaVar> modelicaVars) {
		this.modelicaVars = modelicaVars;
	}

	@Override
	public String toString() {
		return "ModelicaModelAndComponent [id=" + id + ", parentId=" + parentId
				+ ", parentPkgId=" + parentPkgId + ", type=" + type
				+ ", modelName=" + modelName + ", info=" + info + "]";
	}

	
	
	
}
