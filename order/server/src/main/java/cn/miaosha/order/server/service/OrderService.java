package cn.miaosha.order.server.service;

import cn.miaosha.order.server.dao.OrderDao;
import cn.miaosha.order.server.domain.MiaoshaOrder;
import cn.miaosha.order.server.domain.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
@Autowired
OrderDao od;
	public MiaoshaOrder getMiaoshaOrderByuserIdAndgoodsId(long id, long goodsId) {
		// TODO Auto-generated method stub
		MiaoshaOrder miaoshaOrder=od.getMiaoshaOrderByuserIdAndgoodsId(id,goodsId);
		return miaoshaOrder;		
	}
	public long insertorderinfo(OrderInfo oi){
		return od.insertorderinfo(oi);
	}
	public void insertmgoods(MiaoshaOrder mso) {
		// TODO Auto-generated method stub
		od.insertmgoods(mso);
	}
	public OrderInfo getOrderById(long orderId) {
		// TODO Auto-generated method stub
		return od.getOrderById(orderId);
	}
	public long getMiaoshaOrderIdByuserIdAndgoodsId(long id, long goodsId) {
		// TODO Auto-generated method stub试一试哦
		System.out.println(od.getMiaoshaOrderIdByuserIdAndgoodsId(id,goodsId));
		long miaoshaOrder=od.getMiaoshaOrderIdByuserIdAndgoodsId(id,goodsId);
		
		return miaoshaOrder;		
	}
}
