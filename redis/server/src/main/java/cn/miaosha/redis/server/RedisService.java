package cn.miaosha.redis.server;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Service
public class RedisService {
@Autowired
JedisPool jp;
public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
	Jedis j=null;
	try{
		j=jp.getResource();
		String value=j.get(prefix.getPrefix()+key);

		T t=stringToBean(value,clazz);
		return t;
	}
	finally{
		closePool(j);
	}
}
public <T> void set(KeyPrefix prefix,String key,T value){  
	Jedis j=null;
	try{
		j=jp.getResource();
		String v=beanToString(value);
		if(prefix.existseconds()<=0)
		j.set(prefix.getPrefix()+key,v);
		else
			j.setex(prefix.getPrefix()+key, prefix.existseconds(), v);
	
	}
	finally{
		closePool(j);
	}
}
public <T> String beanToString(T value) {
	if(value == null) {
		return null;
	}
	Class<?> clazz = value.getClass();
	if(clazz == int.class || clazz == Integer.class) {
		 return ""+value;
	}else if(clazz == String.class) {
		 return (String)value;
	}else if(clazz == long.class || clazz == Long.class) {
		return ""+value;
	}else {
		return JSON.toJSONString(value);
	}
}
public <T> Long decr(KeyPrefix prefix, String key) {
	 Jedis jedis = null;
	 try {
		 jedis =  jp.getResource();
		//鐢熸垚鐪熸鐨刱ey
		 String realKey  = prefix.getPrefix() + key;
		return  jedis.decr(realKey);
	 }finally {
		 closePool(jedis);
	 }
}
public <T> boolean exists(KeyPrefix prefix, String key) {
	 Jedis jedis = null;
	 try {
		 jedis =  jp.getResource();
		//鐢熸垚鐪熸鐨刱ey
		 String realKey  = prefix.getPrefix() + key;
		return  jedis.exists(realKey);
	 }finally {
		  closePool(jedis);
	 }
}


@SuppressWarnings("unchecked")
public <T> T stringToBean(String str, Class<T> clazz) {
	if(str == null || str.length() <= 0 || clazz == null) {
		 return null;
	}
	if(clazz == int.class || clazz == Integer.class) {
		 return (T)Integer.valueOf(str);
	}else if(clazz == String.class) {
		 return (T)str;
	}else if(clazz == long.class || clazz == Long.class) {
		return  (T)Long.valueOf(str);
	}else {
		return JSON.toJavaObject(JSON.parseObject(str), clazz);
	}
}
public <T> boolean delete(KeyPrefix keyPrefix,String key){
	Jedis j=null;
	try{
		j=jp.getResource();
		long ret=j.del(keyPrefix.getPrefix()+key);
		return ret > 0;
	}
	finally{
		closePool(j);
	}
}
public void closePool(Jedis jedis){
	if(jedis!=null)
		jedis.close();
}
public <T> Long incr(AccessKey accessKey, String key) {
	// TODO Auto-generated method stub
	Jedis jedis = null;
	 try {
		 jedis =  jp.getResource();
		//鐢熸垚鐪熸鐨刱ey
		 String realKey  = accessKey.getPrefix() + key;
		return  jedis.incr(realKey);
	 }finally {
		 closePool(jedis);
	 }
}
}
