package GridCP.core.service.commonService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.service.common.ModelPackageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestCommonService {

	@Resource
	private ModelPackageService modelPackageService;
	
	@Test
	public void test(){
//		modelPackageService.deletePackage(4);
	}
	
	@Test
	public void testFastJson(){

	}
}
