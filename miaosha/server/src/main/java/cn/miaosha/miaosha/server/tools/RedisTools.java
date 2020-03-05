package cn.miaosha.miaosha.server.tools;

import cn.miaosha.miaosha.server.feign.Redisfeign;
import cn.miaosha.miaosha.server.prefix.AccessKey;
import cn.miaosha.miaosha.server.prefix.BasePrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class RedisTools {
    @Autowired
    Redisfeign rf;
    public <T> T get(BasePrefix keyPrefix, String key, Class<?> clazz){
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
    public <T> Long incr(AccessKey accessKey,  String key){return rf.incr(accessKey,key);}
}
