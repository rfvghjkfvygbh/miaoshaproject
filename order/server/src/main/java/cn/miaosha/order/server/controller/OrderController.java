package cn.miaosha.order.server.controller;

//import javax.xml.ws.Action;

import cn.miaosha.order.server.domain.MiaoshaUser;
import cn.miaosha.order.server.domain.OrderInfo;
import cn.miaosha.order.server.feign.GoodsService;
import cn.miaosha.order.server.result.CodeMsg;
import cn.miaosha.order.server.result.Result;
import cn.miaosha.order.server.service.OrderService;
import cn.miaosha.order.server.vo.Goodsvo;
import cn.miaosha.order.server.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService os;
	@Autowired
	GoodsService gs;
@RequestMapping("/detail")
@ResponseBody
public Result<OrderDetailVo> detail(MiaoshaUser msu, @RequestParam("orderId")long orderId){
	if(msu==null)
		return Result.fail(CodeMsg.SERVER_ERROR);
	OrderInfo oi=os.getOrderById(orderId);
	if(oi==null)
		return Result.fail(CodeMsg.MOBILE_ERROR);
	Goodsvo gv=gs.getGoodsvoByid(oi.getGoodsId());
	OrderDetailVo odv=new OrderDetailVo();
	odv.setGoods(gv);
	odv.setOrder(oi);
	return Result.success(odv);
}
}
