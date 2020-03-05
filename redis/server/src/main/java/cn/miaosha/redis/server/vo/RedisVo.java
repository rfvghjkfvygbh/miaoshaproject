package cn.miaosha.redis.server.vo;

import cn.miaosha.redis.server.BasePrefix;
import cn.miaosha.redis.server.KeyPrefix;

import java.io.Serializable;

public class RedisVo<T> implements Serializable {
    private BasePrefix basePrefix;
    private T value;

//
//    public void setClazz(Class<T> clazz) {
//        this.clazz = clazz;
//    }

    public RedisVo() {
    }

    public RedisVo(BasePrefix basePrefix, T value) {
        this.basePrefix = basePrefix;
        this.value = value;
    }

    public T getValue() {
        return value;
    }



    public void setValue(T value) {
        this.value = value;

    }

    public BasePrefix getBasePrefix() {
        return basePrefix;
    }

    public void setBasePrefix(BasePrefix basePrefix) {
        this.basePrefix = basePrefix;
    }
}
