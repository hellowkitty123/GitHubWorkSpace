package com.itheima.redbaby.adapter;

import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.util.ImageUtil;
import com.itheima.redbaby.util.ImageUtil.ImageCallback;
import com.itheima.redbaby.vo.ProductListVo;

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

public class ProductLvAdapter extends ImageAsyncLoaderAdpter<ProductListVo> {
	
	private Context context;
	private List<ProductListVo> prodInfos;
//	private ListView mListView;
	
	public ProductLvAdapter(Context context, AbsListView absListView,
			List<ProductListVo> objects) {
		super(context, absListView, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.prodInfos = objects;
	}

	
	
/*
	public ProductLvAdapter(Context context, List<ProductListVo> prodInfos) {
	//	super();
		this.context = context;
		this.prodInfos = prodInfos;
	}
*/
	
	@Override
	public int getCount() {
		return prodInfos.size();
	}


	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder = new Holder();
		ProductListVo prodInfo = prodInfos.get(position);
		View view;
		if(convertView == null){
			view = View.inflate(context, R.layout.product_list_items, null);
		}else{
			view = convertView;
		}
		
		holder.goodsIv = (ImageView) view.findViewById(R.id.goodsIconIv);
		holder.tvName = (TextView) view.findViewById(R.id.textClothesName);
		holder.tvPrice = (TextView) view.findViewById(R.id.textClothesPrice);
		holder.tvMkPrice = (TextView) view.findViewById(R.id.textMarketPrice);
		holder.commNum = (TextView) view.findViewById(R.id.textProductCommentNum);
		
		String imageUrl = prodInfo.getPic();
//		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		/*
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, new ImageCallback(){

			@Override
			public void loadImage(Bitmap bitmap, String imagePath) {
				holder.goodsIv.setImageBitmap(bitmap);
				notifyDataSetChanged();
			}
			
		});
		
		if(bitmap ==null){
			holder.goodsIv.setImageResource(R.drawable.product_01);
		}else{
			holder.goodsIv.setImageBitmap(bitmap);
		}
		*/
		view.setTag(position);
		holder.tvName.setText(prodInfo.getName());
		holder.tvPrice.setText(String.valueOf(prodInfo.getPrice()));
		holder.tvMkPrice.setText(String.valueOf(prodInfo.getMarketprice()));
		holder.commNum.setText(prodInfo.getComment_count()+"");
		holder.goodsIv.setImageResource(R.drawable.product_01);
		loadImage(position, imageUrl);
		return view;
		  
	}

	@Override
	public void onImageLoadFinish(Integer t, Drawable drawable) {
		View view = mListView.findViewWithTag(t);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.goodsIconIv);
			iv.setImageDrawable(drawable);
		}
	}
	
	static class Holder{
		ImageView goodsIv;
		TextView tvName;
		TextView tvPrice;
		TextView tvMkPrice;
		TextView commNum;
	}
}


