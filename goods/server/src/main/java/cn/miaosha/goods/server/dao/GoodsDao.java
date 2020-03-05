package cn.miaosha.goods.server.dao;

import java.util.Date;
import java.util.List;

import cn.miaosha.goods.server.vo.Goodsvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface GoodsDao {
@Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_time,mg.end_time from miaosha_goods mg left join goods g on mg.goods_id=g.id")
public List<Goodsvo> getGoodsvo();
@Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_time,mg.end_time from miaosha_goods mg left join goods g on mg.goods_id=g.id where g.id=#{id}")
public Goodsvo getGoodsvoByid(@Param("id") long id);
@Select("select mg.start_time from miaosha_goods mg left join goods g on mg.goods_id=g.id where g.id=#{id}")
public Date getStartTimeByid(@Param("id") long id);
@Select("select mg.end_time from miaosha_goods mg left join goods g on mg.goods_id=g.id where g.id=#{id}")
public Date getEndTimeByid(long id);
@Update("update miaosha_goods set stock_count=stock_count-1 where goods_id=#{id} and stock_count>0")
public int reduce_stock(long id);
}
