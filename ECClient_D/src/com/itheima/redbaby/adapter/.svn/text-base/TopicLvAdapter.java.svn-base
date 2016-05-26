package com.itheima.redbaby.adapter;

import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.util.ImageUtil;
import com.itheima.redbaby.util.ImageUtil.ImageCallback;
import com.itheima.redbaby.vo.TopicListVo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TopicLvAdapter extends ImageAsyncLoaderAdpter<TopicListVo> {

	


	private Context context;
	private List<TopicListVo> topicList;
	private ListView lv;

	public TopicLvAdapter(Context context, AbsListView absListView,
			List<TopicListVo> objects) {
		super(context, absListView, objects);
		this.context=context;
		this.topicList = objects;
	}
	
	/*
	public TopicLvAdapter(Context context, ListView lv,
			List<TopicListVo> topicList) {
		super();
		this.context = context;
		this.topicList = topicList;
		this.lv = lv;
	}
*/
	@Override
	public int getCount() {
		return topicList.size();
	}


	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		TopicListVo info = topicList.get(position);
		if (convertView == null) {
			
			convertView = View.inflate(context,
					R.layout.topicproduct_list_items, null);
		} 
			holder.goodsIv = (ImageView) convertView
					.findViewById(R.id.goodsIconIv);
			holder.tvName = (TextView) convertView
					.findViewById(R.id.textClothesName);
			holder.tvPrice = (TextView) convertView
					.findViewById(R.id.textClothesPrice);
			holder.tvMkPrice = (TextView) convertView
					.findViewById(R.id.textMarketPrice);
	//		convertView.setTag("holder",holder);
		
		holder.tvName.setText(info.getName());
		holder.tvPrice.setText(info.getPrice() + "");
		holder.tvMkPrice.setText(info.getMarketprice() + "");

		
		String imageUrl = info.getPic();
//		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		convertView.setTag(position);//给图片打标识
		
		/*
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, callback);
		if(bitmap==null){//从网站加载
			//设置默认图片
			holder.goodsIv.setImageBitmap(bitmap);
		}else{
			//设置本地缓存图片
			holder.goodsIv.setImageResource(R.drawable.product_01);
		}*/
		loadImage(position, imageUrl);
		
		return convertView;
	}

	@Override
	public void onImageLoadFinish(Integer t, Drawable drawable) {
		View view = mListView.findViewWithTag(t);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.goodsIconIv);
			iv.setImageDrawable(drawable);
		}
	}
	
	ImageCallback callback = new ImageCallback(){

		@Override
		public void loadImage(Bitmap bitmap, String imagePath) {
			ImageView imgView = (ImageView) lv.findViewWithTag(imagePath);//用标识识别ImageView
			if(imgView!=null){
				imgView.setImageBitmap(bitmap);
			}
		}
		
	};
		
	

	static class Holder {
		ImageView goodsIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
		TextView commNum;
	}

}
