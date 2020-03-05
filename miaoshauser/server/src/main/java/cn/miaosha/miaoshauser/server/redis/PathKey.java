package cn.miaosha.miaoshauser.server.redis;


public class PathKey extends BasePrefix {

	public PathKey(int seconds, String prefix) {
		super(seconds, prefix);
		// TODO Auto-generated constructor stub
	}
public static final PathKey path=new PathKey(60, "Path:");
}
