package GridCP.core.dto.pageModel;

import java.util.List;
import java.util.Map;

public class EasyuiTreeNode implements java.io.Serializable {

	private String id;
	private String text;
	private String iconCls;
	private Boolean checked = false;
	private Map<String, Object> attributes;
	private List<EasyuiTreeNode> children;
	private String state = "open";
	private String nodeType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<EasyuiTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<EasyuiTreeNode> children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	@Override
	public String toString() {
		return "EasyuiTreeNode [id=" + id + ", text=" + text + ", state="
				+ state + ", nodeType=" + nodeType + "]";
	}


	
}
