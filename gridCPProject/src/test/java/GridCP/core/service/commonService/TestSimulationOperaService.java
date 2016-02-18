package GridCP.core.service.commonService;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import GridCP.core.service.common.SimulationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestSimulationOperaService {

	@Resource
	private SimulationService simulationOperaService;
	
	@Test
	public void test(){
		System.out.println(simulationOperaService);

	}
}
