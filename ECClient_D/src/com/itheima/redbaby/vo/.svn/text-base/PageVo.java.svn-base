package com.itheima.redbaby.vo;

public class PageVo {
	public int startIndex;
	public int endIndex;
	public int pageLenth = 10;
	public int totalPageNum;
	public int wantedPageNum=1;
	public PageVo(){
		
	}
	public PageVo(int pageLenth,
			int totalNum, int wantedPageNum) {
		super();
		this.wantedPageNum = wantedPageNum;
		if(pageLenth!=0||pageLenth>0){
			this.pageLenth = pageLenth;
		}
		if(wantedPageNum>0){
			this.wantedPageNum = wantedPageNum;
		}
		if(totalNum%this.pageLenth==0){
			this.totalPageNum = totalNum/this.pageLenth;
		}else{
			this.totalPageNum = totalNum/this.pageLenth+1;
		}
		if(this.wantedPageNum==1){
			this.endIndex = this.wantedPageNum*this.pageLenth;
			this.startIndex = 1;
		}else {
			this.endIndex = this.wantedPageNum*this.pageLenth;
			this.startIndex = (this.wantedPageNum-1)*this.pageLenth;
		}
	}
}
