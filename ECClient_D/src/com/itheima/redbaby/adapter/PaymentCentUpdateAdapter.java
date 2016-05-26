package com.itheima.redbaby.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.ProductAdapter.ProductViewHolder;
import com.itheima.redbaby.vo.CartProduct;
import com.itheima.redbaby.vo.ProductListVo;

public class PaymentCentUpdateAdapter extends ImageAsyncLoaderAdpter<CartProduct> {
	private static final String TAG = "PaymentCentUpdateAdapter";
	public PaymentCentUpdateAdapter(Context context, AbsListView absListView) {
		super(context, absListView);
	}

	public PaymentCentUpdateAdapter(Context context, AbsListView absListView, List<CartProduct> list) {
		super(context, absListView, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		Holder holder;
		if (convertView == null) {
			view = inflate(R.layout.payment_center_items, null);
			holder = new Holder();
			holder.shopcar_item_prodImage_img = (ImageView) view.findViewById(R.id.shopcar_item_prodImage_img);
			holder.shopcar_item_prodName_text = (TextView) view.findViewById(R.id.shopcar_item_prodName_text);
			holder.shopcar_item_prodId_text = (TextView) view.findViewById(R.id.shopcar_item_prodId_text);
			holder.shopcar_item_prodPrice_text = (TextView) view.findViewById(R.id.shopcar_item_prodPrice_text);
			view.setTag(R.layout.payment_center_items, holder);
		} else {
			view = convertView;
			holder = (Holder) view.getTag(R.layout.payment_center_items);
		
		}
		CartProduct item = getItem(position);
		holder.shopcar_item_prodImage_img.setBackgroundResource(R.drawable.product_loading);
		holder.shopcar_item_prodName_text.setText(item.getName()+"");
		holder.shopcar_item_prodPrice_text.setText(item.getPrice() + "");
		holder.shopcar_item_prodId_text.setText(item.getId() + "");
		Log.i(TAG, item.getName()+"");
		Log.i(TAG, item.getPrice()+"");
		Log.i(TAG, item.getId()+"");
		view.setTag(position);
		loadImage(position, item.getPic());
		return view;
	}
	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		View view = mListView.findViewWithTag(position);
 		if (view != null) {
 			ImageView iv = (ImageView) view.findViewById(R.id.shopcar_item_prodImage_img);
			iv.setBackgroundDrawable(drawable);
		}
	}
	

	public static class Holder{
		ImageView shopcar_item_prodImage_img;
		TextView shopcar_item_prodName_text;//prodName
		TextView shopcar_item_prodId_text;//编码：
		TextView shopcar_item_prodPrice_text;//单价：
	}
}
