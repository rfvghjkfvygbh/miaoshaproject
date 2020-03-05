package cn.miaosha.goods.server.vo;

import java.util.Date;

import cn.miaosha.goods.server.domain.Goods;

public class Goodsvo extends Goods {
	private Double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	
	public Double getMiaoshaPrice() {
		return miaoshaPrice;
	}
	public void setMiaoshaPrice(Double miaoshaPrice) {
		this.miaoshaPrice = miaoshaPrice;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return super.toString()+"Goodsvo [miaoshaPrice=" + miaoshaPrice + ", stockCount="
				+ stockCount + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}
