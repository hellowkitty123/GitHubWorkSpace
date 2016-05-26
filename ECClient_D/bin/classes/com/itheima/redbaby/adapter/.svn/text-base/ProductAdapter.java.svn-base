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
import com.itheima.redbaby.vo.ProductListVo;

public class ProductAdapter extends ImageAsyncLoaderAdpter<ProductListVo> {

	public ProductAdapter(Context context, AbsListView absListView) {
		super(context, absListView);
	}

	public ProductAdapter(Context context, AbsListView absListView, List<ProductListVo> list) {
		super(context, absListView, list);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ProductViewHolder holder;
		if (convertView == null) {
			view = inflate(R.layout.product_list_items, null);
			holder = new ProductViewHolder();
			holder.goodsIv = (ImageView) view.findViewById(R.id.goodsIconIv);
			holder.tvName = (TextView) view.findViewById(R.id.textClothesName);
			holder.tvPrice = (TextView) view.findViewById(R.id.textClothesPrice);
			holder.tvMkPrice = (TextView) view.findViewById(R.id.textMarketPrice);
			holder.commNum = (TextView) view.findViewById(R.id.textProductCommentNum);
			view.setTag(R.layout.product_list_items, holder);
		} else {
			view = convertView;
			holder = (ProductViewHolder) view.getTag(R.layout.product_list_items);
		}
		
		ProductListVo item = getItem(position);
		holder.goodsIv.setBackgroundResource(R.drawable.product_loading);
		holder.tvName.setText(item.getName());
		holder.tvPrice.setText("会员价:" + item.getPrice() + "");
		holder.tvMkPrice.setText("市场价:" + item.getMarketprice() + "");
		holder.commNum.setText(item.getComment_count() + "");
		view.setTag(position);
		loadImage(position, item.getPic());
		return view;
	}
	@Override
	public void onImageLoadFinish(Integer position, Drawable drawable) {
		View view = mListView.findViewWithTag(position);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.goodsIconIv);
			iv.setBackgroundDrawable(drawable);
		}
	}
	

	public class ProductViewHolder {
		ImageView goodsIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
		TextView commNum;
	}
}
