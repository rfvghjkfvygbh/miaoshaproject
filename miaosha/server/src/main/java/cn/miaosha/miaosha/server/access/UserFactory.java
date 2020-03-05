package cn.miaosha.miaosha.server.access;

import cn.miaosha.miaosha.server.domain.MiaoshaUser;

public class UserFactory {
private static ThreadLocal<MiaoshaUser> tl=new ThreadLocal<>();
public static void setUser(MiaoshaUser msu){
	tl.set(msu);
}
public static MiaoshaUser getUser() {
	return tl.get();
}
}
