package cn.miaosha.goods.server.controller;

import cn.miaosha.goods.server.service.GoodsService;
import cn.miaosha.goods.server.vo.Goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoodsServiceController {
    @Autowired
    GoodsService goodsService;
    @RequestMapping(value = "/getGoodsvoByid",method = RequestMethod.POST)
    @ResponseBody
    Goodsvo getGoodsvoByid(@RequestParam("id") long id){
        return goodsService.getGoodsvoByid(id);
    }
    @PostMapping("/reduce_stock")
    @ResponseBody
   boolean reduce_stock(@RequestParam("id") long id){
        return goodsService.reduce_stock(id);
    }
    @PostMapping("/getGoodsvos")
    @ResponseBody
    List<Goodsvo> getGoodsvos(){return goodsService.getGoodsvos();}
}
