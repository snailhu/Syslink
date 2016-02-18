package GridCP.core.dto.modelicaDto;

import java.util.List;


public class ModelTreeDto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String label;
	
	private int parentId;
	
	private int id;
	
	private String value;
	
	private String extend;
	
	private List<ModelTreeDto> items;
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public List<ModelTreeDto> getItems() {
		return items;
	}

	public void setItems(List<ModelTreeDto> items) {
		this.items = items;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	
	
	
}
