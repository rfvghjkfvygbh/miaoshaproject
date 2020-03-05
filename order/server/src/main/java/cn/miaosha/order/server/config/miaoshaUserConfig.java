package cn.miaosha.order.server.config;

import cn.miaosha.order.server.domain.MiaoshaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class miaoshaUserConfig implements HandlerMethodArgumentResolver{
//@Autowired
//MiaoshauserService msus;
@Autowired
MiaoshaUserFactory muf;
	@Override
	public Object resolveArgument(MethodParameter arg0,
			ModelAndViewContainer arg1, NativeWebRequest arg2,
			WebDataBinderFactory arg3) throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response=arg2.getNativeResponse(HttpServletResponse.class);
		HttpServletRequest request=arg2.getNativeRequest(HttpServletRequest.class);
		return muf.getMiaoshaUser(request, response);
	}
	@Override
	public boolean supportsParameter(MethodParameter arg0) {
		// TODO Auto-generated method stub
		return arg0.getParameterType()== MiaoshaUser.class;
	}

}
