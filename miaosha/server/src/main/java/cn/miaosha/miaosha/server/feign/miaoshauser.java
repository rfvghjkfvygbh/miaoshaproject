package cn.miaosha.miaosha.server.feign;

import cn.miaosha.miaosha.server.domain.MiaoshaUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient("miaoshauser")
public interface miaoshauser {
    @RequestMapping("/getCookiename")
    String getCookiename();
    @RequestMapping(value = "/getByToken",method = RequestMethod.POST)
    MiaoshaUser getByToken(@RequestBody HttpServletResponse response, @RequestParam("uuid") String uuid);
}
