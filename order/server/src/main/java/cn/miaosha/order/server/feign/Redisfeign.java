package cn.miaosha.order.server.feign;

import cn.miaosha.order.server.redis.BasePrefix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("redis")
public interface Redisfeign {
    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public void s(@RequestBody BasePrefix prefix, @RequestParam("key") String key, @RequestParam("value") String value);
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public String get(@RequestBody BasePrefix prefix, @RequestParam("key") String key);
    @RequestMapping(value="/decr",method = RequestMethod.POST)
    public <T> Long decr(@RequestBody BasePrefix prefix, @RequestParam("key") String key);

    @RequestMapping(value="/exists",method = RequestMethod.POST)
    public <T> boolean exists(@RequestBody BasePrefix prefix, @RequestParam("key") String key);
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public <T> boolean delete(@RequestBody BasePrefix keyPrefix, @RequestParam("key") String key);
}