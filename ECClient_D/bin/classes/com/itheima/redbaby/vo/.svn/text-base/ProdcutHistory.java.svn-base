package com.itheima.redbaby.vo;

/**
 * 浏览记录
 * 
 * @author liu
 * 
 */
public class ProdcutHistory extends ProductListVo implements Comparable<ProdcutHistory>{
	/** 浏览时间 */
	private long time;

	public ProdcutHistory() {
	}
	
	public ProdcutHistory(int id, String name, String pic, double marketprice, double price, int comment_count,
			long time) {
		super(id, name, pic, marketprice, price, comment_count);
		this.time = time;
	}

	public ProdcutHistory(ProductListVo productListVo) {
		super(productListVo.getId(), productListVo.getName(), productListVo.getPic(), productListVo.getMarketprice(),
				productListVo.getPrice(), productListVo.getComment_count());
		this.time = System.currentTimeMillis();
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public int compareTo(ProdcutHistory another) {
		return time > another.time ? 1 : -1;
	}
	
	

	
}
