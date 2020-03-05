package cn.miaosha.order.server.dao;

import cn.miaosha.order.server.domain.MiaoshaOrder;
import cn.miaosha.order.server.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface OrderDao {
@Select("select * from miaosha_order where user_id=#{id} and goods_id=#{goodsId}")
	public MiaoshaOrder getMiaoshaOrderByuserIdAndgoodsId(@Param("id") long id, @Param("goodsId") long goodsId);
@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_time)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
@SelectKey(before=false,keyColumn="id",resultType=long.class,statement="select last_insert_id()",keyProperty="id")
public long insertorderinfo(OrderInfo oi);
@Insert("insert into miaosha_order(user_id,order_id,goods_id)values(#{user_id},#{order_id},#{goods_id})")
void insertmgoods(MiaoshaOrder mso);
@Select("select * from order_info where id = #{orderId}")
OrderInfo getOrderById(long orderId);
@Select("select order_id from miaosha_order where user_id=#{id} and goods_id=#{goodsId}")
public long getMiaoshaOrderIdByuserIdAndgoodsId(@Param("id") long id, @Param("goodsId") long goodsId);
}
