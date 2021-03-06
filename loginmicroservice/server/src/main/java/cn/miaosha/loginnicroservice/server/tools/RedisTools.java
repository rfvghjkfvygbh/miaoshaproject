package cn.miaosha.loginnicroservice.server.tools;

import cn.miaosha.loginnicroservice.server.feign.Redisfeign;
import cn.miaosha.loginnicroservice.server.feign.miaoshauser;
import cn.miaosha.loginnicroservice.server.redis.BasePrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
public class RedisTools {
    @Autowired
    Redisfeign rf;
    @Autowired
    miaoshauser m;
    public String test(){
        return rf.test();
    }
    @GetMapping("/sb")
    public <T> T get(BasePrefix keyPrefix,String key,Class<?> clazz){
        System.out.println(m);
String str=rf.get(keyPrefix,key);
T t= (T) BeanStrTools.stringToBean(str,clazz);
return t;
    }
    public <T> void set(BasePrefix basePrefix,String key,T value){
        String str=BeanStrTools.beanToString(value);
        rf.s(basePrefix,key,str);
    }
    public <T> Long decr(BasePrefix prefix, String key){
        return rf.decr(prefix,key);
    }


    public <T> boolean exists( BasePrefix prefix,  String key){
        return rf.exists(prefix,key);
    }

    public <T> boolean delete( BasePrefix keyPrefix, String key){
        return rf.delete(keyPrefix,key);
    }

}
