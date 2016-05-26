package com.itheima.redbaby.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.vo.Limitbuy;

public class LimitbuyAdapter extends ImageAsyncLoaderAdpter<Limitbuy> {
	private static final String TAG = "LimitbuyAdapter";
	private List<Limitbuy> list;
 	private Context context;
 	private Drawable[] drawables;
 	private boolean isPlay;
 	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			if (!isPlay)
				return ;
 			handler.postDelayed(this, 1000);
			notifyDataSetChanged();
 		}
	};
 	private Handler handler = new Handler();
	private SimpleDateFormat simpleDateFormat;
	public LimitbuyAdapter(List<Limitbuy> list, ListView listView, Context context) {
		super(context, listView, list);
		this.list = list;
 		this.context = context;
 		drawables = new Drawable[list.size()];
 		 
 		simpleDateFormat = new SimpleDateFormat("dd 天  HH:mm:ss");
	}
	public void start() {
		isPlay = true;
		runnable.run();
	}
	
	public void stop(){
		isPlay = false;
	}
	// product_list_timelimit_items.xml
	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		HolderView holderView;
		View view;
		if (convertView != null) {
			view = convertView;
			holderView = (HolderView) convertView.getTag(R.layout.product_list_timelimit_items);
		} else {
 			view = inflate(R.layout.product_list_timelimit_items, null);
			holderView = new HolderView();
			// 建立对应
			holderView.goodsIconIv = (ImageView) view.findViewById(R.id.goodsIconIv);
			holderView.textClothesName = (TextView) view.findViewById(R.id.textClothesName);
			holderView.textClothesPrice = (TextView) view.findViewById(R.id.textClothesPrice);
			holderView.textMarketPrice = (TextView) view.findViewById(R.id.textMarketPrice);
			// holderView.textProductComment = (TextView)
			// convertView.findViewById(R.id.textProductComment);
			holderView.textProductCommentNum = (TextView) view.findViewById(R.id.textProductCommentNum);
			view.setTag(R.layout.product_list_timelimit_items, holderView);
		}

		holderView.textClothesName.setText(list.get(position).getName() + "");
		holderView.textClothesPrice.setText("￥" + String.valueOf(list.get(position).getLimitprice() + ""));
		holderView.textMarketPrice.setText("原价：￥" + String.valueOf(list.get(position).getPrice()));
 		// 定时器
		Limitbuy item = getItem(position);
		long v = item.getLefttime() - System.currentTimeMillis();
		Date date = new Date(v);
		holderView.textProductCommentNum.setText(simpleDateFormat.format(date));
		Drawable d = drawables[position];
		if (d == null) {
			view.setTag(position);
			loadImage(position, list.get(position).getPic());
		} else {
			holderView.goodsIconIv.setBackgroundDrawable(d);
		}
		return view;
	}

	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		drawables[position] = drawable;
	}

	public class HolderView {
		public ImageView goodsIconIv;
		public TextView textClothesName;
		public TextView textClothesPrice;
		public TextView textMarketPrice;
		// public TextView textProductComment;
		public TextView textProductCommentNum;
	}
}
