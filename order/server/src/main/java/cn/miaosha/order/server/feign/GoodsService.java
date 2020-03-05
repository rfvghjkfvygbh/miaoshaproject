package cn.miaosha.order.server.feign;

import cn.miaosha.order.server.vo.Goodsvo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("goods")
public interface GoodsService {
@RequestMapping(value = "/getGoodsvoByid",method = RequestMethod.POST)
    Goodsvo getGoodsvoByid(@RequestParam("id") long id);
}
