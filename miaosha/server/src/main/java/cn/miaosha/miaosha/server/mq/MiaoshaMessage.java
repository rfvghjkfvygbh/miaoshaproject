 
package cn.miaosha.miaosha.server.mq;

import cn.miaosha.miaosha.server.domain.MiaoshaUser;

public class MiaoshaMessage {

private MiaoshaUser msu;
private long goodsId;
public MiaoshaUser getMsu() {
	return msu;
}
public void setMsu(MiaoshaUser msu2) {
	this.msu = msu2;
}
public long getGoodsId() {
	return goodsId;
}
public void setGoodsId(long goodsId) {
	this.goodsId = goodsId;
}
	

}
