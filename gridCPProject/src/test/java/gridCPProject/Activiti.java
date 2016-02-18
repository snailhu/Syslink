package gridCPProject;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

import GridCP.util.ResourcesUtil;
@ContextConfiguration(value = {"classpath:applicationContext.xml", "classpath:applicationContext-hibernate.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class Activiti {
	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	@Test
	public void deployProcessSubmit() throws Exception {
		// 第一步：上传文件
		// springmvc通过文件上传的参数解析器将页面提交 的file赋值给形参
		// resource_bpmn和resource_png存储了上传的文件

		// 第二步：调用activiti的service执行流程定义部署
		// 部署bpmn文件和png文件
		// bpmn文件名

		// 使用RespositoryService

		// 部署bpmn文件和png文件
		// bpmn文件名
		String resourceName_bpmn = "test.bpmn";
		InputStream inputStream_bpmn = this.getClass().getClassLoader()
				.getResourceAsStream("test.bpmn");

		String resourceName_png = "test.png";
		InputStream inputStream_png = this.getClass().getClassLoader()
				.getResourceAsStream("test.png");

		// 部署对象
		Deployment deployment = repositoryService.createDeployment()
				.addInputStream(resourceName_bpmn, inputStream_bpmn)// 部署bpmn文件
				.addInputStream(resourceName_png, inputStream_png)// 部署png文件
				.deploy();

		// 部署对象id
		System.out.println("部署id：" + deployment.getId());

		System.out.println("部署时间：" + deployment.getDeploymentTime());
	}

	
	
	@Test
	public void test() {
		// 流程定义的key
				String processDefinitionKey = ResourcesUtil.getValue(
						"purchas", "purchas");// 采购流程标识
				
				// 创建查询对象
				ProcessDefinitionQuery processDefinitionQuery = repositoryService
						.createProcessDefinitionQuery();

				// 设置查询条件
				// 只查询采购流程的定义
				processDefinitionQuery.processDefinitionKey(processDefinitionKey);

				// 查询列表
				List<ProcessDefinition> list = processDefinitionQuery.list();
				// 分页查询
				// processDefinitionQuery.listPage(firstResult, maxResults)
				// 根据流程定义的id查询一条记录
				// processDefinitionQuery.processDefinitionId(definitionId).singleResult();
			
				  for (ProcessDefinition processDefinition : list) {
				  System.out.println("================================");
				  System.out.println("流程定义id：" + processDefinition.getId());
				  System.out.println("流程定义部署id：" +
				  processDefinition.getDeploymentId()); System.out.println("流程定义的key："
				  + processDefinition.getKey()); System.out.println("流程定义的名称：" +
				  processDefinition.getName()); System.out.println("bpmn资源名称：" +
				  processDefinition.getResourceName()); System.out.println("png资源名称：" +
				  processDefinition.getDiagramResourceName()); }			
		
	}

	@Test
	public void startProcessInstance() {

		// 根据流程定义的key（标识 ）来启动一个实例，activiti找该key下版本最高的流程定义
		// 一般情况下为了方便开发使用该方法启动一个流程实例。
		String processDefinitionKey = ResourcesUtil.getValue(
				"purchas", "purchas");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey);

		// 根据流程定义的id来启动一个实例，这种方法一般不用
		// runtimeService.startProcessInstanceById(processDefinitionId);

		System.out.println("流程实例所属流程定义id :"
				+ processInstance.getProcessDefinitionId());
		System.out.println("流程实例的id：" + processInstance.getProcessInstanceId());
		System.out.println("流程实例的执行id：" + processInstance.getId());
		System.out.println("流程当前的活动（结点）id：" + processInstance.getActivityId());
		System.out.println("业务标识 ：" + processInstance.getBusinessKey());
		// System.out.println("流程变量："+processInstance.getProcessVariables());

	}
}
