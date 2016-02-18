/**
 * 字符串操作类
 */
package GridCP.core.util;

/**
 * @author lvly
 *
 */
public class StringHelper {

	
	/**
	 * 
	 */
	public StringHelper() {
	
	}
	
	/**
	 * 除去尾部字符
	 * @param text	输入字符串
	 * @param trim	去除的字符集合
	 * @return	返回字符串
	 */
	public static  String trimEnd(String text, char[] trim){
		
		//将字符串数组转换成String类型
		String strim = "";
		strim = String.valueOf(trim);
		
		//去掉尾部字符
		text=trimEnd(text, strim);
 
		return text;
	}
	
	/**
	 * 去掉尾部字符
	 * @param text
	 * @param trim
	 * @return
	 */
	public static  String trimEnd(String text, String trim){
		
		for(int i = trim.length()-1; i >= 0; i--) 
		{
			char ctrim = trim.charAt(i); //取trim中的第i个字符
			
			String strim = "";
			strim = String.valueOf(ctrim);
			
			while (text.endsWith(strim)) //如果以第i个字符结尾 阶段最后
			{
				text = text.substring(0, text.lastIndexOf(strim));
			}
		}
		return text;
	}
	
	/**
	 * 除去前端字符
	 * @param text	输入字符
	 * @param trim	除去的字符集合
	 * @return	返回字符串
	 */
	public static  String trimStart(String text, char[] trim){
		
		//将字符串数组转换成String类型
		String strim = "";
		strim=String.valueOf(trim);
        
		//去掉前端字符
		text=trimStart(text, strim);
		return text;
	}
	
	/**
	 * 去掉前端字符
	 * @param text
	 * @param trim
	 * @return
	 */
	public static String trimStart(String text, String trim){
		
		for(int i = 0; i < trim.length(); i++) 
		{
			char ctrim = trim.charAt(i); //取第i个字符
			
			String strim = "";
			strim = String.valueOf(ctrim);
			
			while (text.startsWith(strim)) //如果text以第i个字符开始，截断字符串
			{
				//text = text.replaceFirst(strim, "");
				text = text.substring(strim.length());
			}
		}
		
		return text;

	}
	
	/**
	 * 除去两端字符
	 * @param text	输入字符
	 * @param trim	除去的字符集合
	 * @return	返回字符串
	 */
	public  static String trim(String text, char[] trim){    
		
		text = trimStart(text, trim);
		text = trimEnd(text, trim);
		return text;
	}

	/**
	 * 除去两端字符
	 * @param text	输入字符
	 * @param trim	除去的字符集合
	 * @return	返回字符串
	 */
	public static String trim(String text,String trim){
		
		text = trimStart(text, trim);
		text = trimEnd(text, trim);
		return text;
	}
	
	/**
	 * 将字符串拆分成字符串数组 ,
	 * @param text	输入字符
	 * @return	返回字符串
	 */
	public static String[] StringToArray(String text){
		text = text.trim();
		String[] str = text.split(",");
		return str;
		
	}
	
	public static void main(String[] args) {
		String text1 = "0eb21beb034f4c4a8be690f47a6d6dfb";
		String text = "0eb21beb034f4c4a8be690f47a6d6dfb,0f1130d054dd43a7b7f7677a28eb7ff6,d5c40e7a6d6a4e08924b5f9ce6ed6087,e9fbc2330d2d45b3a7abe94ae3e7a85e,f4d5e8a01a374841bdebf12645a13473";
		String[] str = StringHelper.StringToArray(text1);
		for (String string : str) {
			System.out.println(string);
		}
	}
} // end class
