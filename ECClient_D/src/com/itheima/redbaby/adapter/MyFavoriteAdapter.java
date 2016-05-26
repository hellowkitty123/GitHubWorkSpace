package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.vo.Product;
import com.itheima.redbaby.vo.ProductListVo;

public class MyFavoriteAdapter extends ImageAsyncLoaderAdpter<Product> {

	public MyFavoriteAdapter(Context context, AbsListView absListView) {
		super(context, absListView);
	}

	public MyFavoriteAdapter(Context context, AbsListView absListView, List<Product> list) {
		super(context, absListView, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ProductViewHolder holder;
		if (convertView == null) {
			view = inflate(R.layout.my_favorite_listitem, null);
			holder = new ProductViewHolder();
			holder.goodsIv = (ImageView) view.findViewById(R.id.myfavorite_product_img);
			holder.tvName = (TextView) view.findViewById(R.id.myfavorite_title_text);
			holder.tvPrice = (TextView) view.findViewById(R.id.myfavorite_price_text);
			holder.tvMkPrice = (TextView) view.findViewById(R.id.myfavorite_nostock_text);
			view.setTag(R.layout.my_favorite_listitem, holder);
		} else {
			view = convertView;
			holder = (ProductViewHolder) view.getTag(R.layout.my_favorite_listitem);
		}
		
		Product item = getItem(position);
		holder.goodsIv.setBackgroundResource(R.drawable.product_loading);
		holder.tvName.setText(item.getName());
		holder.tvPrice.setText(item.getPrice() + "");
		holder.tvMkPrice.setText(item.getMarketprice() + "");
		view.setTag(position);
		loadImage(position, item.getPic());
		return view;
	}
	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		View view = mListView.findViewWithTag(position);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.myfavorite_product_img);
			iv.setBackgroundDrawable(drawable);
		}
	}
	

	public class ProductViewHolder {
		ImageView goodsIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
	}
}
