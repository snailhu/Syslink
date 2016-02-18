package GridCP.core.service.modelicaService;

import java.io.IOException;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import GridCP.core.domain.modelica.ClassRestricitonType;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestModelicaModelAndComponentServiceImpl {

	
	@Test
	public void test(){
		System.out.println(ClassRestricitonType.valueOf("package".toUpperCase()).getName());
	}
//	@Test
//	public void saveModelicaModelByParseXML(){
//		ApplicationContext ac  = new ClassPathXmlApplicationContext("applicationContext-hibernate.xml");
//		ModelicaModelAndComponentService service = (ModelicaModelAndComponentService) ac.getBean("modelicaModelAndComponentServiceImpl");
//		boolean flag = service.saveModelicaModelByParseXML("d:/xml/modelica.xml");
//		System.out.println("flag: " + flag);
////		System.out.println(ac.getBean("sessionFactory"));
////		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
////		System.out.println("session: " + sessionFactory.openSession());
////		System.out.println("session: " + sessionFactory.getCurrentSession());
//		
//	}
	@Test
	public void runTest(){
		String cmd = "";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ClassRestricitonType.valueOf("package".toUpperCase()).getName());
	}
}
