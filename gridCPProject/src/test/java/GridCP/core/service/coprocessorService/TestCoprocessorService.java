package GridCP.core.service.coprocessorService;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestCoprocessorService {

	@Resource
	private CoprocessorService coprocessorService;
	
	@Test
	public void getModelTree(){
		List<EasyuiTreeNode> tree = coprocessorService.getModelTree();
		for (EasyuiTreeNode node : tree) {
			System.out.println(node);
			List<EasyuiTreeNode> treeChildren =  node.getChildren();
			if(treeChildren != null && treeChildren.size() > 0){
				for (EasyuiTreeNode treeChild : treeChildren) {
					System.out.println("--" + treeChild);
				}
			}
			System.out.println();
		}
	}
	
	@Test
	public void getFlowComponentListByFlowModelId(){
		int flowModelId = 1;
		List<TreeGridDto> trees = coprocessorService.getFlowComponentListByFlowModelId(flowModelId );
		for (TreeGridDto tree : trees) {
			System.out.println(tree);
			List<TreeGridDto> treeChildren = tree.getChildren();
			if(treeChildren != null && treeChildren.size() > 0){
				for (TreeGridDto treeChild : treeChildren) {
					System.out.println(treeChild);
				}				
			}
			System.out.println();
		}
	}
}
