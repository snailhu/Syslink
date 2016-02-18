package GridCP.core.domain.modelica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

/**
 * 包类
 * @author Snail
 *
 */
@Entity
@Table(name="t_modelicaPkg")
public class ModelicaPkg {
	@Id
	@GeneratedValue
	private int id;
	
	/**父类包的id*/
	@Column
	private int parentId;
	
	/**模型包Id*/
	@Column(nullable = true)
	private int modelPackageId;
	
	/**包类型*/
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 20)
	private ClassRestricitonType classRestriction; 
		
	/**包路径*/
	@Column(nullable = true, length = 250)
	private String URL ;
	
	/**包名称*/
	@Column(nullable = true, length = 64)
	private String Name ;
	
	/**包描述*/
	@Column(nullable = true, length = 1000)
	private String Description ;
	
	/**包SVG*/
	@Column(nullable = true, length = 500)
	private String svgPath ;

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

	public int getModelPackageId() {
		return modelPackageId;
	}

	public void setModelPackageId(int modelPackageId) {
		this.modelPackageId = modelPackageId;
	}

	public ClassRestricitonType getClassRestriction() {
		return classRestriction;
	}

	public void setClassRestriction(ClassRestricitonType classRestriction) {
		this.classRestriction = classRestriction;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getSvgPath() {
		return svgPath;
	}

	public void setSvgPath(String svgPath) {
		this.svgPath = svgPath;
	}

	
}
