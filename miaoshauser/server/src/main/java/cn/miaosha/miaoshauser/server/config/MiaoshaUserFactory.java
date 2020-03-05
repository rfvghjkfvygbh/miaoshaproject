package cn.miaosha.miaoshauser.server.config;


import cn.miaosha.miaoshauser.server.domain.MiaoshaUser;
import cn.miaosha.miaoshauser.server.service.MiaoshauserService;
import com.alibaba.druid.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserFactory {
	@Autowired
	MiaoshauserService msus;
	public MiaoshaUser getMiaoshaUser(HttpServletRequest request,
									  HttpServletResponse response) {
		// TODO Auto-generated method 
		String requesttoken=request.getParameter(MiaoshauserService.COOKIE_NAME);
		String cookievalue=getCookieValue(request,MiaoshauserService.COOKIE_NAME);
		if(StringUtils.isEmpty(requesttoken)&&StringUtils.isEmpty(cookievalue))
			return null;
		String token=StringUtils.isEmpty(cookievalue)?requesttoken:cookievalue;
		return msus.getByToken(response, token);
	}
	private static String getCookieValue(HttpServletRequest request, String cookieName) {
		// TODO Auto-generated method stub
		
		Cookie[] cookies=request.getCookies();
		if(cookies==null||cookies.length==0)
			return null;
		for(Cookie c:cookies){
			if(c.getName().equals(cookieName))
				return c.getValue();
		}
		return null;
	}
}
