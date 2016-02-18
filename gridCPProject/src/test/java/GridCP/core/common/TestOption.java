package GridCP.core.common;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import GridCP.core.dto.commonDto.ModelVarDto;

public class TestOption {

	@Test
	public void test1(){
//		System.out.println(Option.getMWorksPath());
		Set<ModelVarDto> set = new HashSet<ModelVarDto>();
		ModelVarDto var1 = new ModelVarDto();
		var1.setId(1);
		var1.setValue("1");
		set.add(var1);
		
		ModelVarDto var2 = new ModelVarDto();
		var2.setId(1);
		var2.setValue("1");
		System.out.println(set.contains(var1));
	}
	@Test
	public void test2(){
		long count = 4L;
		System.out.println((int)count);
	}
	@Test
	public void test3(){
		String str = "gridCPProject";
		//String path = "C:\Development\JAVA\Tomacat\apache-tomcat-8.0.28-windows-x64\apache-tomcat-8.0.28\me-webapps\gridCPProject\";
		
	}
}
