package cn.miaosha.loginnicroservice.server.domain;

public class MiaoshaOrder {
private long id;
private long user_id;
private long order_id;
private long goods_id;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public long getUser_id() {
	return user_id;
}
public void setUser_id(long user_id) {
	this.user_id = user_id;
}
public long getOrder_id() {
	return order_id;
}
public void setOrder_id(long order_id) {
	this.order_id = order_id;
}
public long getGoods_id() {
	return goods_id;
}
public void setGoods_id(long goods_id) {
	this.goods_id = goods_id;
}
@Override
public String toString() {
	return "MiaoshaOrder [id=" + id + ", user_id=" + user_id + ", order_id="
			+ order_id + ", goods_id=" + goods_id + "]";
}

}
