package cn.miaosha.order.server.controller;

import cn.miaosha.order.server.domain.MiaoshaOrder;
import cn.miaosha.order.server.domain.OrderInfo;
import cn.miaosha.order.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class OrderServiceController {
@Autowired
    OrderService orderService;
    @GetMapping("/getMiaoshaOrderByuserIdAndgoodsId")
    public MiaoshaOrder getMiaoshaOrderByuserIdAndgoodsId(@RequestParam("id") long id, @RequestParam("goodsId") long goodsId) {

        return orderService.getMiaoshaOrderByuserIdAndgoodsId(id,goodsId);
    }
    @PostMapping("/insertorderinfo")
    public long insertorderinfo(@RequestBody OrderInfo oi){
        return orderService.insertorderinfo(oi);
    }
   @PostMapping("/insertmgoods")
    public void insertmgoods(@RequestBody MiaoshaOrder mso) {
        orderService.insertmgoods(mso);
    }
    @PostMapping("/getMiaoshaOrderIdByuserIdAndgoodsId")
    public long getMiaoshaOrderIdByuserIdAndgoodsId(@RequestParam("id") long id, @RequestParam("goodsId") long goodsId){
        return orderService.getMiaoshaOrderIdByuserIdAndgoodsId(id,goodsId);
    }
}
