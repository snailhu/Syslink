package GridCP.core.domain.common;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 模型包实体
 * @author shenwp
 *
 */
@Entity
@Table(name="t_packageType")
public class ModelPackageType {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 30, nullable = true)
	private String name;
	
	@Column(length = 258, nullable = true)
	private String description;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="packageType") 
	private Set<ModelPackage> modelPackage;
	
	@Column(nullable = true)
	private int userId;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<ModelPackage> getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(Set<ModelPackage> modelPackage) {
		this.modelPackage = modelPackage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
