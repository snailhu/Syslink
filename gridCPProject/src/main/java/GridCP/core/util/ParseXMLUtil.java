package GridCP.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXMLUtil {

	/**
	 * 获取文件路径
	 * 
	 * @return 配置文件路径
	 */
	public static String getConfigPath(String fileName){
		String path = null;
		String configPath = null;
		try {
			path = ParseXMLUtil.class.getClassLoader().getResource("").getPath();
			System.out.println("path: " + path);
			File file = new File(path);
			configPath = file.getPath() + "\\" + fileName ;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return configPath;
	}
	/**
	 * 获取根组件
	 * @param path 文件全名
	 * @return Element 根Element
	 */
	public static Element getRootElement(String path) {

		SAXReader saxReader = new SAXReader();

		File file = new File(path); // 打开文件
		
		Document doc = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream,"UTF-8");
			BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
			// 读取配置文件构造dom
			doc = saxReader.read(bufferedreader);
			return doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取根组件
	 * @param path 文件全名
	 * @return Element 根Element
	 */
	public static Element getRootElement(InputStream in) {

		SAXReader saxReader = new SAXReader();		
		Document doc = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(in,"UTF-8");
			BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
			// 读取配置文件构造dom
			doc = saxReader.read(bufferedreader);
			return doc.getRootElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
