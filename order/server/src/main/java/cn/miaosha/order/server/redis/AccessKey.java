package cn.miaosha.order.server.redis;

public class AccessKey extends BasePrefix{
public static final String prefix="Access";
	private AccessKey(int seconds, String prefix) {
		super(seconds, prefix);
		// TODO Auto-generated constructor stub
	}
public static AccessKey accessKey(int expireSeconds){
	return new AccessKey(expireSeconds,prefix);
}
}
