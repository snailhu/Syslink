package GridCP.core.domain.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 模型包实体
 * @author shenwp
 *
 */
@Entity
@Table(name="t_package")
public class ModelPackage {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private int parentId;
	
	@Column(length = 30, nullable = true)
	private String name;
	
	@Column(length = 258, nullable = true)
	private String description;

	/**包类型*/
	@ManyToOne()
	@JoinColumn(name = "packageTypeId")
	private ModelPackageType packageType;
	
	@Column(nullable = true)
	private int userId;
	
	@Column(nullable = true)
	private Date createDate;
	
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

	public ModelPackageType getPackageType() {
		return packageType;
	}

	public void setPackageType(ModelPackageType packageType) {
		this.packageType = packageType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ModelPackage [id=" + id + ", parentId=" + parentId + ", name="
				+ name + ", description=" + description + ", packageType="
				+ packageType + ", userId=" + userId + ", createDate="
				+ createDate + "]";
	}

	

}
