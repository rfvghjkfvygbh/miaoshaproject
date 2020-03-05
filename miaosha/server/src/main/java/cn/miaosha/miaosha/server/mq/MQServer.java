package cn.miaosha.miaosha.server.mq;

import cn.miaosha.miaosha.server.domain.MiaoshaOrder;
import cn.miaosha.miaosha.server.feign.GoodsService;
import cn.miaosha.miaosha.server.feign.OrderService;
import cn.miaosha.miaosha.server.service.MiaoshaService;
import cn.miaosha.miaosha.server.tools.BeanStrTools;
import cn.miaosha.miaosha.server.tools.RedisTools;
import cn.miaosha.miaosha.server.vo.Goodsvo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQServer {
	@Autowired
	OrderService os;
	@Autowired
	GoodsService gs;
	@Autowired
	RedisTools rs;

	@Autowired
	MiaoshaService miaoshaService;
	Logger log=LoggerFactory.getLogger(MQServer.class);
@RabbitListener(queues=MQConfig.QUEUE)
public void recive(String msg){
	MiaoshaMessage mm= BeanStrTools.stringToBean(msg, MiaoshaMessage.class);
	Goodsvo gv=gs.getGoodsvoByid(mm.getGoodsId());
	if(gv.getGoodsStock()<=0)
		return ;
	MiaoshaOrder miaoshaOrder=os.getMiaoshaOrderByuserIdAndgoodsId(mm.getMsu().getId(), mm.getGoodsId());
	if(miaoshaOrder!=null)
		return ;
	miaoshaService.miaosha(mm.getMsu(), gv);
}
}
