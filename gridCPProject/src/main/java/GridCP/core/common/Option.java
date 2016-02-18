package GridCP.core.common;

import javax.servlet.ServletContext;

import org.dom4j.Element;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import GridCP.core.util.ParseXMLUtil;

public class Option {

	private static String filePath = ParseXMLUtil.getConfigPath("config.xml");
	
	private static String MWorksPath ;
	
	// 缓存目录
	private static String cache = "";

	public static String getCache() {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        cache = servletContext.getRealPath("/").replace('\\', '/');
//		cache = Option.class.getClassLoader().getResource("").getPath() + "/svg";
//		cache = "C:/Development/JAVA/Tomacat/apache-tomcat-8.0.28-windows-x64/apache-tomcat-8.0.28/me-webapps/gridCPProject";
//		cache = "D:/tongyuan/works/configuration/syslink/Cache";
//		cache = Option.class.getClassLoader().getResource("").getPath() + "svg";
		//cache = "C:/Development/JAVA/Tomacat/apache-tomcat-8.0.28-windows-x64/apache-tomcat-8.0.28/me-webapps/gridCPProject/static/svg";
//		cache = "D:/tongyuan/works/configuration/syslink/Cache";
//        System.out.println("getCache: " + cache);
		return cache;
	} 

	
//	static{
//		loadConfigFile("D:/xml/config.xml");
//	}
//	public static String getMWorksPath() {
//		return MWorksPath;
//	}
//	
//	/**
//	 * 加载config.xml文件
//	 * 
//	 * @param path	文件全名
//	 * @return true=加载成功
//	 */
//	private static boolean loadConfigFile(String path){
//		Element rootElem = ParseXMLUtil.getRootElement(path);
//		MWorksPath = rootElem.element("MWorks").element("Path").getText();
//		return false;
//	}
	
}
