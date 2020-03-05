package cn.miaosha.order.server.vo;


import cn.miaosha.order.server.domain.OrderInfo;

public class OrderDetailVo {
	private Goodsvo goods;
	private OrderInfo order;
	public Goodsvo getGoods() {
		return goods;
	}
	public void setGoods(Goodsvo goods) {
		this.goods = goods;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderDetailVo [goods=" + goods.toString() + ", order=" + order.toString() + "]";
	}
	
}
