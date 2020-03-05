package cn.miaosha.miaoshauser.server.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import cn.miaosha.miaoshauser.server.dao.MiaoshaUserDao;
import cn.miaosha.miaoshauser.server.domain.MiaoshaUser;
import cn.miaosha.miaoshauser.server.exception.GlobalException;
import cn.miaosha.miaoshauser.server.form.LoginForm;
import cn.miaosha.miaoshauser.server.redis.MiaoshaUserKey;
import cn.miaosha.miaoshauser.server.result.CodeMsg;
import cn.miaosha.miaoshauser.server.result.Result;
import cn.miaosha.miaoshauser.server.tools.RedisTools;
import cn.miaosha.miaoshauser.server.utils.MD5Util;
import cn.miaosha.miaoshauser.server.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
@Service
public class MiaoshauserService {
	public static final String COOKIE_NAME = "token";
	@Autowired
	RedisTools rs;
@Autowired
MiaoshaUserDao msud;
public MiaoshaUser getByid(long id){
	MiaoshaUser msu=rs.get(MiaoshaUserKey.getById, ""+id, MiaoshaUser.class);
	System.out.println("id:"+id);	
	if(msu!=null)
		return msu;
	msu=msud.getByid(id);
	System.out.println("msu:"+msu.toString());
	rs.set(MiaoshaUserKey.getById, ""+id, msu);
	return msu;
}
public boolean updatePassword(HttpServletResponse response,String token,MiaoshaUser msu,String password){
	if(msu==null)
		throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
	MiaoshaUser msuu=new MiaoshaUser();
	msuu.setId(msu.getId());
	password= MD5Util.formPassToDBPass(password, msu.getSalt());
	msuu.setPassword(password);
	msud.updatePassword(msuu);
	msu.setPassword(password);
//	rs.set(MiaoshaUserKey.getById, ""+msu.getId(), msu);
	rs.delete(MiaoshaUserKey.getById, msu.getId()+"");
	rs.set(MiaoshaUserKey.token, token, msu);
	addUserCookie(response, msuu);
	return true;
}
public boolean updatePassword(HttpServletResponse response,String token,long id,String password){
	return updatePassword(response,token, getByid(id), password);
}
public MiaoshaUser getByToken(HttpServletResponse response,String uuid){
	if(StringUtils.isEmpty(uuid))
		return null;
	MiaoshaUser msu=rs.get(MiaoshaUserKey.token,uuid, MiaoshaUser.class);
	System.out.println(response.getHeaderNames());
	addUserCookie(response, msu);
	return msu;
}
public Result<Boolean> login(HttpServletResponse response, LoginForm lf) {
	if(lf==null){	
			throw new GlobalException(CodeMsg.SERVER_ERROR);
	}
	String m=lf.getMobile();
	String password=lf.getPassword();
	long mobile=Long.parseLong(m);
	MiaoshaUser msu=getByid(mobile);
	if(msu==null)
		throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
	if(!password.equals(msu.getPassword())){
		throw new GlobalException(CodeMsg.PASSWORD_ERROR);
				
	}
	addUserCookie(response,msu);
	return new Result<Boolean>(false);
}
private void addUserCookie(HttpServletResponse response, MiaoshaUser msu) {
	// TODO Auto-generated method stub
	String uuid= UUIDUtil.uuid();
	rs.set(MiaoshaUserKey.token, uuid, msu);
	Cookie cookie = new Cookie(COOKIE_NAME, uuid);
	cookie.setMaxAge(MiaoshaUserKey.token.existseconds());
	cookie.setPath("/");
	response.addCookie(cookie);
}

}
