package cn.miaosha.redis.server.controller;

import cn.miaosha.redis.server.*;
import cn.miaosha.redis.server.vo.RedisVo;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
public class RedisController {
    Logger log= LoggerFactory.getLogger("redis");
    @Autowired
    RedisService rs;
    @PostMapping("/get")
    public String get(@RequestBody BasePrefix prefix, @RequestParam("key") String key)
    {
     KeyPrefix keyPrefix=prefix;
        return rs.get(keyPrefix,key,String.class);
    }
    @PostMapping("/set")
    public void set(@RequestBody BasePrefix prefix, @RequestParam("key") String key,@RequestParam("value") String value){
        KeyPrefix keyprefix= prefix;

//        LoggerFactory.getILoggerFactory().getLogger("setsethahahaha").info(rv.getClazz().getClass().getSimpleName());
        rs.set(keyprefix,key,value);
    }
    @PostMapping("/beanToString")
    public <T> String beanToString(@RequestBody T value){
       return rs.beanToString(value);
    }
    @PostMapping("/stringToBean")
    public <T> T stringToBean(@RequestParam("str") String str, T t){
        LoggerFactory.getLogger("hahahahaha").info(t.toString());
        return (T) rs.stringToBean(str,t.getClass());
    }
  @PostMapping("/decr")
  public <T> Long decr(@RequestBody BasePrefix prefix, @RequestParam("key") String key){
        return rs.decr(prefix,key);
  }
  @PostMapping("/exists")
  public <T> boolean exists(@RequestBody BasePrefix prefix, @RequestParam("key") String key){
return rs.exists(prefix,key);
  }
  @PostMapping("/delete")
    public <T> boolean delete(@RequestBody BasePrefix keyPrefix,@RequestParam("key") String key){
        return rs.delete(keyPrefix,key);
  }
  @PostMapping("/incr")
    public <T> Long incr(@RequestBody AccessKey accessKey,@RequestParam("key") String key){
        return rs.incr(accessKey,key);
  }
  @PostMapping("/test")
    public String test(){
        return "test";
  }
}
