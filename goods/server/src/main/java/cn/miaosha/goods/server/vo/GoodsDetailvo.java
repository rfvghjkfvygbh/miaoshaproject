package cn.miaosha.goods.server.vo;

import cn.miaosha.goods.server.domain.MiaoshaUser;

public class GoodsDetailvo {
private Goodsvo goods;
private MiaoshaUser user;
private int status;
private long remainSeconds;
public Goodsvo getGoods() {
	return goods;
}
public void setGoods(Goodsvo goods) {
	this.goods = goods;
}
public MiaoshaUser getUser() {
	return user;
}
public void setUser(MiaoshaUser user) {
	this.user = user;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public long getRemainSeconds() {
	return remainSeconds;
}
public void setRemainSeconds(long remainSeconds) {
	this.remainSeconds = remainSeconds;
}

}
