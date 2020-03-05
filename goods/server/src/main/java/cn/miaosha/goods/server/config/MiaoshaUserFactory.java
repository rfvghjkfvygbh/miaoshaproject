package cn.miaosha.goods.server.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.miaosha.goods.server.domain.MiaoshaUser;
import cn.miaosha.goods.server.feign.miaoshauser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.alibaba.druid.util.StringUtils;
@Service
public class MiaoshaUserFactory {
	@Autowired
	miaoshauser msus;
	public MiaoshaUser getMiaoshaUser(HttpServletRequest request,
									  HttpServletResponse response) {
		// TODO Auto-generated method 
		String requesttoken=request.getParameter(msus.getCookiename());
		String cookievalue=getCookieValue(request,msus.getCookiename());
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
