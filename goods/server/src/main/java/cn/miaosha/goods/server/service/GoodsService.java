package cn.miaosha.goods.server.service;

import java.util.Date;
import java.util.List;

import cn.miaosha.goods.server.dao.GoodsDao;
import cn.miaosha.goods.server.vo.Goodsvo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GoodsService {
	@Autowired
	GoodsDao goodsDao;
	public List<Goodsvo> getGoodsvos(){
		return goodsDao.getGoodsvo();
	}
	public Goodsvo getGoodsvoByid(long id){
		return goodsDao.getGoodsvoByid(id);
	}
	public Date getStartTimeByid(long id){
		return goodsDao.getStartTimeByid(id);
	}
	public Date getEndTimeByid(long id){
		return goodsDao.getEndTimeByid(id);
	}
	//xiaci
	public boolean reduce_stock(long id){
		int ret=goodsDao.reduce_stock(id);
		return ret>0;
	}
}
