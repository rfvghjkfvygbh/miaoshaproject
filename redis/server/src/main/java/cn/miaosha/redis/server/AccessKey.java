package cn.miaosha.redis.server;

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
