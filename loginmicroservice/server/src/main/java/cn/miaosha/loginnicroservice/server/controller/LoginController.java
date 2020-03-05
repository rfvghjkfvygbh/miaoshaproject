package cn.miaosha.loginnicroservice.server.controller;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import cn.miaosha.loginnicroservice.server.feign.Redisfeign;
import cn.miaosha.loginnicroservice.server.feign.miaoshauser;
import cn.miaosha.loginnicroservice.server.form.LoginForm;
import cn.miaosha.loginnicroservice.server.result.Result;
import cn.miaosha.loginnicroservice.server.service.MiaoshauserService;
import cn.miaosha.loginnicroservice.server.tools.RedisTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;


@Controller
public class LoginController {
	private Logger log= LoggerFactory.getLogger(LoginController.class);
	@Autowired
	MiaoshauserService msus;
//	@Autowired
//	RedisTools rs;
//	@Autowired
//	MQSend mqs;
//	@Autowired
//	OrderService os;
@Autowired
miaoshauser m;
@Autowired
	Redisfeign rf;
@Autowired
RedisTools rt;
@RequestMapping("/")
public String haha(){
	System.out.println(rt.test());return "login";
}
@RequestMapping("/do_login")
@ResponseBody
public Result<Boolean> login(HttpServletResponse response, @Valid LoginForm lf){

	Result<Boolean> r= msus.login(response,lf);
	log.info(JSON.toJSONString(r));
return r;
}
}
