package GridCP.core.service.commonService;

import java.io.IOException;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.core.io.ClassPathResource;

public class TestEhcache {

    @Test
    public void test() throws IOException {
        //创建底层Cache
        net.sf.ehcache.CacheManager ehcacheManager
                = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());

        //创建Spring的CacheManager
        EhCacheCacheManager cacheCacheManager = new EhCacheCacheManager();
        //设置底层的CacheManager
        cacheCacheManager.setCacheManager(ehcacheManager);

        Long id = 1L;
        String userName = "AAA";

        //根据缓存名字获取Cache
        Cache cache = cacheCacheManager.getCache("ehcache");
        //往缓存写数据
        cache.put(id, userName);
        //从缓存读数据
//        Assert.assertNotNull();
        System.out.println(cache.get(id).get());
    }
	
    @Test
    public void test2(){
    	CacheManager cacheManager = CacheManager.create();
    	// 或者
//    	cacheManager = CacheManager.getInstance();
    	// 或者
    	cacheManager = CacheManager.create("classpath:/ehcache.xml");
    	// 或者
//    	cacheManager = CacheManager.create("http://localhost:8080/test/ehcache.xml");
    	// .......
    	 
    	// 获取ehcache配置文件中的一个cache
    	net.sf.ehcache.Cache sample = cacheManager.getCache("ehcache");
    	// 获取页面缓存
    	BlockingCache cache = new BlockingCache(cacheManager.getEhcache("SimplePageCachingFilter"));
    	// 添加数据到缓存中
    	Element element = new Element("key", "val");
    	sample.put(element);
    	// 获取缓存中的对象，注意添加到cache中对象要序列化 实现Serializable接口
    	Element result = sample.get("key");
    	// 删除缓存
//    	sample.remove("key");
//    	sample.removeAll();
    	 
//    	System.out.println(result.getValue());
    	// 获取缓存管理器中的缓存配置名称
    	System.out.println(" 获取缓存管理器中的缓存配置名称:");
    	for (String cacheName : cacheManager.getCacheNames()) {
    	    System.out.println("--" + cacheName);
    	}
    	// 获取所有的缓存对象
    	System.out.println("获取所有的缓存对象: ");
    	for (Object key : cache.getKeys()) {
    	    System.out.println("--" + key);
    	}
    	 
    	// 得到缓存中的对象数
    	cache.getSize();
    	// 得到缓存对象占用内存的大小
    	cache.getMemoryStoreSize();
    	// 得到缓存读取的命中次数
    	cache.getStatistics().getCacheHits();
    	// 得到缓存读取的错失次数
    	cache.getStatistics().getCacheMisses();
    }
}
