package GridCP.core.service.commonService;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class TestSpringEhcache {

	@Resource
	private CacheManager cacheManager;

	private Cache cache;

	@Before
	public void setUp() {
		//根据缓存名字获取Cache
		cache = cacheManager.getCache("ehcache");
	}
	
	@Test
	public void test(){
        int id = 1;
        String userName = "AAA";
        //往缓存写数据
        cache.put(id, "AAA");
        cache.put(id, "BBB");
        //从缓存读数据
      System.out.println(cache.get(id).get());
	}
}
