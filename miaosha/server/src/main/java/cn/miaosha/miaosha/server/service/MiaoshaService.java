package cn.miaosha.miaosha.server.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import cn.miaosha.miaosha.server.dao.MiaoshaDao;
import cn.miaosha.miaosha.server.domain.*;
import cn.miaosha.miaosha.server.feign.GoodsService;
import cn.miaosha.miaosha.server.feign.OrderService;
import cn.miaosha.miaosha.server.prefix.GoodsKey;
import cn.miaosha.miaosha.server.prefix.MiaoshaKey;
import cn.miaosha.miaosha.server.tools.RedisTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MiaoshaService {
	@Autowired
	RedisTools rs;
	@Autowired
	GoodsService gs;
@Autowired
MiaoshaDao msd;
@Autowired
OrderService os;

	public MiaoshaGoods getMiaoshaGoodsBuid(long goodsId){
	return msd.getMiaoshaGoodsByid(goodsId);
}
@Transactional
public OrderInfo miaosha(MiaoshaUser msu, Goods goods) {
	// TODO Auto-generated method stub
	if(gs.reduce_stock(goods.getId())){

OrderInfo oi=new OrderInfo();
oi.setUserId(msu.getId());
oi.setCreateDate(new Date());
oi.setDeliveryAddrId(0l);
oi.setGoodsId(goods.getId());
oi.setGoodsCount(goods.getGoodsStock());
oi.setGoodsName(goods.getGoodsName());
oi.setGoodsPrice(goods.getGoodsPrice());
oi.setStatus(0);
oi.setOrderChannel(0);
oi.setPayDate(null);
os.insertorderinfo(oi);
MiaoshaOrder mso=new MiaoshaOrder();
mso.setGoods_id(goods.getId());
mso.setOrder_id(oi.getId());
mso.setUser_id(msu.getId());
os.insertmgoods(mso);

	return oi;}
	setGoodsOver(goods.getId());
	return null;
}
public long getMiaoshaResult(MiaoshaUser msu, long goodsId) {
	// TODO Auto-generated method stub
	MiaoshaOrder miaoshaOrder=os.getMiaoshaOrderByuserIdAndgoodsId(msu.getId(), goodsId);

	if(null!=miaoshaOrder){	
		return os.getMiaoshaOrderIdByuserIdAndgoodsId(msu.getId(), goodsId);
	}
		if(getGoodsOver(goodsId))
			return -1;
		return 0;
	
	
	
}
private void setGoodsOver(long goodsId){
	rs.set(GoodsKey.goodsOver, ""+goodsId, true);
}
private boolean getGoodsOver(long goodsId){
	return rs.exists(GoodsKey.goodsOver, ""+goodsId);
}


public BufferedImage createVerifyCode(MiaoshaUser user, long goodsId) {
	if(user == null || goodsId <=0) {
		return null;
	}
	int width = 80;
	int height = 32;
	//create the image
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	// set the background color
	g.setColor(new Color(0xDCDCDC));
	g.fillRect(0, 0, width, height);
	// draw the border
	g.setColor(Color.black);
	g.drawRect(0, 0, width - 1, height - 1);
	// create a random instance to generate the codes
	Random rdm = new Random();
	// make some confusion
	for (int i = 0; i < 50; i++) {
		int x = rdm.nextInt(width);
		int y = rdm.nextInt(height);
		g.drawOval(x, y, 0, 0);
	}
	// generate a random code
	String verifyCode = generateVerifyCode(rdm);
	g.setColor(new Color(0, 100, 0));
	g.setFont(new Font("Candara", Font.BOLD, 24));
	g.drawString(verifyCode, 8, 24);
	g.dispose();
	//把验证码存到redis中
	int rnd = calc(verifyCode);
	rs.set(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, rnd);
	//输出图片	
	return image;
}

public boolean checkVerifyCode(MiaoshaUser user, long goodsId, int verifyCode) {
	if(user == null || goodsId <=0) {
		return false;
	}
	Integer codeOld = rs.get(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId, Integer.class);
	if(codeOld == null || codeOld - verifyCode != 0 ) {
		return false;
	}
	rs.delete(MiaoshaKey.getMiaoshaVerifyCode, user.getId()+","+goodsId);
	return true;
}

private static int calc(String exp) {
	try {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		return (Integer)engine.eval(exp);
	}catch(Exception e) {
		e.printStackTrace();
		return 0;
	}
}

private static char[] ops = new char[] {'+', '-', '*'};
/**
 * + - * 
 * */
private String generateVerifyCode(Random rdm) {
	int num1 = rdm.nextInt(10);
    int num2 = rdm.nextInt(10);
	int num3 = rdm.nextInt(10);
	char op1 = ops[rdm.nextInt(3)];
	char op2 = ops[rdm.nextInt(3)];
	String exp = ""+ num1 + op1 + num2 + op2 + num3;
	return exp;
}
}
