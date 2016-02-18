package GridCP.core.common;

public class Config {
	
	/**
	 * 提示信息配置文件名
	 */
	public static final String MESSAGE = "messages";
	/**
	 * 配置文件中系统版本号的key名称
	 */
	public static final String VERSION_NUMBER_KEY = "version_number";
	
	/**
	 * 配置文件中系统版本发布时间的key名称
	 */
	public static final String VERSION_DATE_KEY = "version_date";
	
	/**
	 * 系统提示信息ResultInfo对象key
	 */
	public static final String RESULTINFO_KEY = "resultInfo";
	
		
	/**
	 * 基础模块存放页面路径 ，在/WEB-INF/jsp下
	 */
	public static final String PAGE_PATH_BASE = "/base";
	
	/**
	 * 业务模块存放页面路径 ，在/WEB-INF/jsp下
	 */
	public static final String PAGE_PATH_BUSINESS = "/business";
	
		

	/**
	 * 一般错误提示页面,该路径需要匹配页面前后缀
	 */
	public static final String ERROR_PAGE = "/base/error";
	/**
	 * 登录页面地址,该路径需要匹配页面前后缀
	 */
	public static final String LOGIN_PAGE = "/base/login";
	
	/**
	 * 无权提示页面地址,该路径需要匹配页面前后缀
	 */
	public static final String REFUSE_PAGE = "/base/refuse";
	
		
}
