package cn.miaosha.goods.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.miaosha.goods.server.domain.MiaoshaUser;
import cn.miaosha.goods.server.prefix.BasePrefix;
import cn.miaosha.goods.server.prefix.GoodsKey;
import cn.miaosha.goods.server.result.Result;
import cn.miaosha.goods.server.service.GoodsService;
import cn.miaosha.goods.server.tools.RedisTools;
import cn.miaosha.goods.server.vo.GoodsDetailvo;
import cn.miaosha.goods.server.vo.Goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	ThymeleafViewResolver tvr;
	@Autowired
	RedisTools rs;
	@Autowired
	GoodsService gs;
	@Autowired
	ApplicationContext appctx;
public String htmlcache(HttpServletRequest request, HttpServletResponse response, Model model, BasePrefix prefix, String key){
	String html=rs.get(prefix, key, String.class);
	if(StringUtils.isEmpty(html)){
		WebContext ctx=new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
//		SpringWebContext ctx=new
//				SpringWebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap(),appctx);
		//key?
		html=tvr.getTemplateEngine().process(key,ctx);
		if(!StringUtils.isEmpty(html))
			rs.set(prefix, key, html);
	}
	return html;
}
@RequestMapping("/to_list")
@ResponseBody
	public String tiaozhuan(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser msu){
	List<Goodsvo> l=gs.getGoodsvos();
	model.addAttribute("goods",l );
	String html=htmlcache(request, response, model, GoodsKey.goodsKey, "goods_list");
	return html;
}
@RequestMapping("/to_detail/{id}")
//@PathVariable("...")  ...为路径上{...}中的...
@ResponseBody
public String goods_detail(HttpServletRequest request,HttpServletResponse response,Model model,MiaoshaUser msu,@PathVariable("id")long id){
	model.addAttribute("user", msu);
	Goodsvo gsv=gs.getGoodsvoByid(id);
	gsv.setStartDate(gs.getStartTimeByid(id));
	gsv.setEndDate(gs.getEndTimeByid(id));
	model.addAttribute("goods", gsv);
	long start=gs.getStartTimeByid(id).getTime();
	long end=gs.getEndTimeByid(id).getTime();
	long now=System.currentTimeMillis();
	long julishijian=0;
	int status=0;
	if(now<start){
		julishijian=start-now;
	}else if(now<end){
		status=1;
	}
	else{
		status=2;
		julishijian=-1;
	}
	model.addAttribute("remainSeconds", julishijian);
	model.addAttribute("status", status);
	String html=htmlcache(request, response, model, GoodsKey.goodsKey, "goods_detail");
return html;
}
@RequestMapping("/detail/{id}")
//@PathVariable("...")  ...为路径上{...}中的...
@ResponseBody
public Result<GoodsDetailvo> goods_detail2(HttpServletRequest request, HttpServletResponse response, Model model, MiaoshaUser msu, @PathVariable("id")long id){
	System.out.println(id);
	Goodsvo gsv=gs.getGoodsvoByid(id);
	gsv.setStartDate(gs.getStartTimeByid(id));
	gsv.setEndDate(gs.getEndTimeByid(id));
	long start=gs.getStartTimeByid(id).getTime();
	long end=gs.getEndTimeByid(id).getTime();
	long now=System.currentTimeMillis();
	long julishijian=0;
	int status=0;
	if(now<start){
		julishijian=start-now;
	}else if(now<end){
		status=1;
	}
	else{
		status=2;
		julishijian=-1;
	}
	GoodsDetailvo goods=new GoodsDetailvo();
	goods.setGoods(gsv);
	goods.setUser(msu);
	goods.setRemainSeconds(julishijian);
	goods.setStatus(status);
return Result.success(goods);
}
}
