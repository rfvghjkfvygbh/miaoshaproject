package cn.miaosha.miaosha.server.feign;

import cn.miaosha.miaosha.server.domain.MiaoshaOrder;
import cn.miaosha.miaosha.server.domain.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("order")
public interface OrderService {
    @RequestMapping("/getMiaoshaOrderByuserIdAndgoodsId")
    public MiaoshaOrder getMiaoshaOrderByuserIdAndgoodsId(@RequestParam("id") long id, @RequestParam("goodsId") long goodsId);
    @RequestMapping(value = "/insertorderinfo",method = RequestMethod.POST)
    public long insertorderinfo(@RequestBody OrderInfo oi);
    @RequestMapping(value = "/insertmgoods",method = RequestMethod.POST)
    public void insertmgoods(@RequestBody MiaoshaOrder mso) ;
    @RequestMapping(value = "/getMiaoshaOrderIdByuserIdAndgoodsId",method = RequestMethod.POST)
    public long getMiaoshaOrderIdByuserIdAndgoodsId(@RequestParam("id") long id, @RequestParam("goodsId") long goodsId);
}
