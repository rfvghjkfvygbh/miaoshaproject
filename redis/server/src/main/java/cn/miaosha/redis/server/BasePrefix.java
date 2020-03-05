package cn.miaosha.redis.server;

public class BasePrefix implements KeyPrefix{
	private int seconds;
	private String prefix;
	public BasePrefix(){}
	 public BasePrefix(String prefix) {
		 seconds=0;
		this.prefix = prefix;
	}

	public BasePrefix(int seconds, String prefix) {
		this.seconds = seconds;
		this.prefix = prefix;
	}

	@Override
	public int existseconds() {
		// TODO Auto-generated method stub
		return seconds;
	}
	@Override
	public String getPrefix() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName()+":"+prefix;
	}

	
}
