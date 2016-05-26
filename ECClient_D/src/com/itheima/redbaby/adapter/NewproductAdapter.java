package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.util.ImageUtil;
import com.itheima.redbaby.util.ImageUtil.ImageCallback;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.Limitbuy;
import com.itheima.redbaby.vo.ProductListVo;

public class NewproductAdapter extends ArrayWapperAdapter<ProductListVo> {
	private List<ProductListVo> list;
	private ListView listView;
	private Context context;
	
	public NewproductAdapter(List<ProductListVo> list, ListView listView,
			Context context) {
		super(context,list);
		this.list = list;
		this.listView = listView;
		this.context = context;
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		HolderView holderView;
		if(convertView!=null){
			holderView = (HolderView) convertView.getTag();
		}else{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService 
				      (Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.product_list_items2, null);
			holderView = new HolderView();
			convertView.setTag(holderView);
			//建立对应
			holderView.goodsIconIv = (ImageView) convertView.findViewById(R.id.goodsIconIv);
			holderView.textClothesName = (TextView) convertView.findViewById(R.id.textClothesName);
			holderView.textClothesPrice = (TextView) convertView.findViewById(R.id.textClothesPrice);
			holderView.textMarketPrice = (TextView) convertView.findViewById(R.id.textMarketPrice);
			holderView.textProductCommentNum = (TextView) convertView.findViewById(R.id.textProductCommentNum);
		
		}
		//System.out.println(list.get(position).name);
		holderView.textClothesName.setText(list.get(position).getName() + "");
		holderView.textClothesPrice.setText("￥" + String.valueOf(list.get(position).getPrice() + ""));
		holderView.textMarketPrice.setText("￥" + String.valueOf(list.get(position).getMarketprice()));
		holderView.textProductCommentNum.setText(String.valueOf(list.get(position).getComment_count()+""));
		String imageUrl = list.get(position).getPic();
		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		holderView.goodsIconIv.setTag(imagePath);//给图片打标识
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, callback);
		if(bitmap==null){//从网站加载
			//设置默认图片
			holderView.goodsIconIv.setImageResource(R.drawable.product_loading);
			
		}else{
			//设置本地缓存图片
			holderView.goodsIconIv.setImageBitmap(bitmap);
		}
		return convertView;
	}

	ImageCallback callback = new ImageCallback(){

		@Override
		public void loadImage(Bitmap bitmap, String imagePath) {
			ImageView imgView = (ImageView) listView.findViewWithTag(imagePath);//用标识识别ImageView
			if(imgView!=null){
				imgView.setImageBitmap(bitmap);
			}
		}
		
	};
	public class HolderView{
		public ImageView goodsIconIv;
		public TextView textClothesName;
		public TextView textClothesPrice;
		public TextView textMarketPrice;
		public TextView textProductCommentNum;
	}
}


 