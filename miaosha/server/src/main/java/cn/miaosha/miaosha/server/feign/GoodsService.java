package cn.miaosha.miaosha.server.feign;

import cn.miaosha.miaosha.server.vo.Goodsvo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("goods")
public interface GoodsService {
@RequestMapping(value = "/getGoodsvoByid",method = RequestMethod.POST)
Goodsvo getGoodsvoByid(@RequestParam("id") long id);
    @RequestMapping(value = "/reduce_stock",method = RequestMethod.POST)
    boolean reduce_stock(@RequestParam("id") long id);
    @RequestMapping(value = "/getGoodsvos",method = RequestMethod.POST)
    List<Goodsvo> getGoodsvos();
}
