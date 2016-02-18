package GridCP.core.domain.coprocessor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 流程组件包实体
 * @author Snail
 *
 */
@Entity
@Table(name="t_coprocesorPKg")
public class CoprocesorPKg {
	@Id
	@GeneratedValue
	private  int id;
	
	@Column
	private  int parentId;
	
	@Column(nullable=false)
	private String URL;

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

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	
	
}
