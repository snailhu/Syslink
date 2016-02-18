package GridCP.core.controller.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import GridCP.core.common.Config;
import GridCP.core.gogsDomain.User;
import GridCP.core.result.SubmitResultInfo;
import GridCP.core.service.userOperateService.UserService;
import GridCP.util.PBKDF2SHA256;
import GridCP.util.ResultUtil;

@Controller
@RequestMapping(value = "/common", produces = "application/json;charset=utf-8")
public class CommonController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/goIndex", method = { RequestMethod.GET })
	public String goIndex() {
		System.out.println("test success goIndex");
		return "showModel_new";
	}
	
	@RequestMapping(value = "/goIndex2", method = { RequestMethod.GET })
	public String goIndex2() {
		System.out.println("test success goIndex2");
		return "redirect:/common/goIndex";
	}
	
	@RequestMapping(value = "/goShowModel", method = { RequestMethod.GET })
	public String goShowModel() {
//		System.out.println("test success goShowModel");
		return "showPkg";
	}
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String loginPost(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value="username",required = false) String username,
			@RequestParam(value="password",required = false) String password
			) throws InvalidKeyException, NoSuchAlgorithmException {	
		if(!("".equals(username))&& username!=null){
			User user = userService.getUserByName(username);
			String EncryptedPassword =PBKDF2SHA256.getEncryptedPassword(password, user.getSalt());
			if(user != null && EncryptedPassword.equals(user.getPasswd())){
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("base_path", request.getContextPath());	
				Cookie c = new Cookie("gogs_awesome",username);
				c.setDomain(".syslink.cn");
				c.setMaxAge(3600);
				c.setPath("/");
				response.addCookie(c);
				return "showModel_new";
			}else{
				return "login";
			}
		}else{
			return "login";
		}
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET })
	@ResponseBody
	public SubmitResultInfo logout(
			HttpServletResponse response,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		session.invalidate();
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		if(cookies != null&&cookies.length>0){
			for(Cookie cookie : cookies){
				Cookie new_cookie = new Cookie(cookie.getName(), null); 
				new_cookie.setDomain(".syslink.cn");
				new_cookie.setPath("/");
				new_cookie.setMaxAge(0);
				response.addCookie(new_cookie);  
			}	
		}			
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 100, null));		
	}

}
