package GridCP.core.modelMeta.commonMeta;

import java.util.List;

public class PackageTreeMeta {

	private int id;
	
	private String name;
					
	private List<PackageTreeMeta> children;


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

	public List<PackageTreeMeta> getChildren() {
		return children;
	}

	public void setChildren(List<PackageTreeMeta> children) {
		this.children = children;
	}
	
	
	
}
