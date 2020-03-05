package cn.miaosha.miaoshauser.server.controller;

import javax.servlet.http.HttpServletResponse;

import cn.miaosha.miaoshauser.server.domain.MiaoshaUser;
import cn.miaosha.miaoshauser.server.service.MiaoshauserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MiaoshaServiceController {
@Autowired
MiaoshauserService msus;
	@RequestMapping("/getCookiename")
@ResponseBody
String getCookiename(){
	return MiaoshauserService.COOKIE_NAME;
}
@RequestMapping("/getByToken")
@ResponseBody
MiaoshaUser getByToken(@RequestBody HttpServletResponse response, @RequestParam("uuid")String uuid){
	return msus.getByToken(response, uuid);
}
}
