package cn.miaosha.miaosha.server.access;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.miaosha.miaosha.server.config.MiaoshaUserFactory;
import cn.miaosha.miaosha.server.domain.MiaoshaUser;
import cn.miaosha.miaosha.server.feign.miaoshauser;
import cn.miaosha.miaosha.server.prefix.AccessKey;
import cn.miaosha.miaosha.server.result.CodeMsg;
import cn.miaosha.miaosha.server.result.Result;
import cn.miaosha.miaosha.server.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
@Lazy
@Service
public class AccessAdapter extends HandlerInterceptorAdapter{
//@Autowired
//miaoshauser msus;
@Autowired
RedisTools rs;
@Autowired
MiaoshaUserFactory muf;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if(handler instanceof HandlerMethod){
			MiaoshaUser msu=muf.getMiaoshaUser(request,response);
			UserFactory.setUser(msu);
			HandlerMethod hm=(HandlerMethod)handler;
			Access access=hm.getMethodAnnotation(Access.class);
			System.out.println(access);
			if(access==null)
				return true;
			if(!access.needLogin())
				return true;
			if(msu==null){
				render(response, CodeMsg.SESSION_ERROR);
				return false;
			}
			String key=request.getRequestURI()+msu.getId();
			AccessKey accessKey=AccessKey.accessKey(access.expireSeconds());
			Integer count=rs.get(accessKey, key, Integer.class);
			if(count==null){
				rs.set(accessKey, key, 1);
			}
			else{
				
			if(count>=access.count()){
				
				render(response, CodeMsg.XIANLIU_ERROR);
				return false;
			}
			else{
				rs.incr(accessKey, key);
			}}
		}
		return true;
	}
	private void render(HttpServletResponse response, CodeMsg cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Result.fail(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}
	
}
