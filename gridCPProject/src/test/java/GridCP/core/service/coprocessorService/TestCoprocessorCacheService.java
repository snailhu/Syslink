package GridCP.core.service.coprocessorService;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import GridCP.core.dto.commonDto.ModelCacheDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestCoprocessorCacheService {

	@Resource
	private CoprocessorCacheService coprocessorCacheService;
    @Autowired
    private CacheManager cacheManager;

    private Cache modelCache;
	
    @Before
    public void setUp() {
    	modelCache = cacheManager.getCache("coprocessor");
    }
    
	@Test
	public void test(){
		ModelCacheDto model = new ModelCacheDto();
		model.setSessionId("AA");
		coprocessorCacheService.save(model);
		
		Object obj = modelCache.get("BB").get();
		System.out.println(obj);
	}
}
