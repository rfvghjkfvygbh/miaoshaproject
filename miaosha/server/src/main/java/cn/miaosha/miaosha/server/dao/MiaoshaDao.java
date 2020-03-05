package cn.miaosha.miaosha.server.dao;

import cn.miaosha.miaosha.server.domain.MiaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface MiaoshaDao {
	//当参数只有一个时，不用加上@Param("...")...与#{...}中的...相同
@Select("select * from miaosha_goods where goods_id = #{goodsId}")
	public MiaoshaGoods getMiaoshaGoodsByid(long goodsId);
}
