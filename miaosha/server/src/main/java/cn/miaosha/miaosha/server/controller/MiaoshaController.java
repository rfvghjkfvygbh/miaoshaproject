package cn.miaosha.miaosha.server.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import cn.miaosha.miaosha.server.access.AccessLimit;
import cn.miaosha.miaosha.server.domain.MiaoshaOrder;
import cn.miaosha.miaosha.server.domain.MiaoshaUser;
import cn.miaosha.miaosha.server.feign.GoodsService;
import cn.miaosha.miaosha.server.feign.OrderService;
import cn.miaosha.miaosha.server.mq.MQSend;
import cn.miaosha.miaosha.server.mq.MiaoshaMessage;
import cn.miaosha.miaosha.server.prefix.GoodsKey;
import cn.miaosha.miaosha.server.prefix.PathKey;
import cn.miaosha.miaosha.server.result.CodeMsg;
import cn.miaosha.miaosha.server.result.Result;
import cn.miaosha.miaosha.server.service.MiaoshaService;
import cn.miaosha.miaosha.server.tools.RedisTools;
import cn.miaosha.miaosha.server.utils.MD5Util;
import cn.miaosha.miaosha.server.utils.UUIDUtil;
import cn.miaosha.miaosha.server.vo.Goodsvo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean{
	@Autowired
	RedisTools rs;
	@Autowired
	GoodsService gs;
	@Autowired
	OrderService os;
	@Autowired
	MiaoshaService mss;
	@Autowired
    MQSend mqs;
	@AccessLimit(maxCount=5,seconds=10)
@RequestMapping(value="/{path}/do_miaosha",method=RequestMethod.POST)
//@RequestParam注解用法
@ResponseBody
public Result<Long> miaosha(Model model, MiaoshaUser msu, @RequestParam("goodsId")long goodsId, @PathVariable("path")String path){

		model.addAttribute("user", msu);
	if(msu==null)
		return Result.fail(CodeMsg.SESSION_ERROR);
	if(path==null||StringUtils.isEmpty(path)||!rs.get(PathKey.path, msu.getId()+""+goodsId, String.class).equals(path))
		return Result.fail(CodeMsg.PATH_ERROR);
	
	MiaoshaOrder mso=os.getMiaoshaOrderByuserIdAndgoodsId(msu.getId(),goodsId);
	if(mso!=null){
		return Result.fail(CodeMsg.REPEAT_ORDER_ERROR);
	}
	long stock=rs.decr(GoodsKey.getStock, ""+goodsId);
	if(stock<0){
		return Result.fail(CodeMsg.STOCK_ERROR);
	}
	
	
	
//	MiaoshaGoods msd=mss.getMiaoshaGoodsBuid(goodsId);
//	if(msd.getStockCount()<=0){
//		return Result.fail(CodeMsg.STOCK_ERROR);
//	}
	MiaoshaMessage mm=new MiaoshaMessage();
	mm.setGoodsId(goodsId);
	mm.setMsu(msu);
	mqs.sendMessage(mm);
	return Result.success(0l);
}
@RequestMapping("/result")
@ResponseBody
public Result<Long> result(MiaoshaUser msu,Model model,@RequestParam("goodsId")long goodsId){
	long result=mss.getMiaoshaResult(msu,goodsId);
	return Result.success(result);
}
@AccessLimit(maxCount=5,needLogin=true,seconds=10)
@RequestMapping("/path")
@ResponseBody
public Result<String> path(MiaoshaUser msu,Model model,@RequestParam("goodsId")long goodsId,@RequestParam(value="verifyCode",defaultValue="0")int vc){
	
	if(!mss.checkVerifyCode(msu, goodsId, vc))
		return Result.fail(CodeMsg.YANZHENGMA_ERROR);
	String str= MD5Util.md5("haha"+ UUIDUtil.uuid());
	rs.set(PathKey.path, msu.getId()+""+goodsId, str);
	return Result.success(str);
}
@Override
public void afterPropertiesSet() throws Exception {
	// TODO Auto-generated method stub
	List<Goodsvo> lg=gs.getGoodsvos();
	for(Goodsvo gv:lg){
		rs.set(GoodsKey.getStock,""+gv.getId(), gv.getStockCount());
	}
}

@RequestMapping(value="/verifyCode", method=RequestMethod.GET)
@ResponseBody
public Result<String> getMiaoshaVerifyCod(HttpServletResponse response,MiaoshaUser user,
		@RequestParam("goodsId")long goodsId) {
	if(user == null) {
		return Result.fail(CodeMsg.SESSION_ERROR);
	}
	try {
		BufferedImage image  = mss.createVerifyCode(user, goodsId);
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();
		return null;
	}catch(Exception e) {
		e.printStackTrace();
		return Result.fail(CodeMsg.MIAOSHA_FAIL);
	}
}

}
