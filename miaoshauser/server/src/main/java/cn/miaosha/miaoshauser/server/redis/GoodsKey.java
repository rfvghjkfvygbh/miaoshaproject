package cn.miaosha.miaoshauser.server.redis;

public class GoodsKey extends BasePrefix{

	private GoodsKey(int seconds, String prefix) {
		super(seconds, prefix);
		// TODO Auto-generated constructor stub
	}
public static GoodsKey goodsKey=new GoodsKey(60, "goods");
public static GoodsKey getStock=new GoodsKey(0, "getStock");
public static GoodsKey goodsOver=new GoodsKey(0, "goodsOver");

}
