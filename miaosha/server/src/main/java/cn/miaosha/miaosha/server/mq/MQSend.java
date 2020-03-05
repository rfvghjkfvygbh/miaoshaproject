package cn.miaosha.miaosha.server.mq;

import cn.miaosha.miaosha.server.tools.BeanStrTools;
import cn.miaosha.miaosha.server.tools.RedisTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSend {
Logger log=LoggerFactory.getLogger(MQSend.class);
@Autowired
RedisTools rs;
@Autowired
AmqpTemplate at;
public void sendMessage(MiaoshaMessage mm) {
	// TODO Auto-generated method stub
	String msg = BeanStrTools.beanToString(mm);
	at.convertAndSend(MQConfig.QUEUE, msg);
}
}
