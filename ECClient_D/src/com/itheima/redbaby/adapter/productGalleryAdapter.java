package com.itheima.redbaby.adapter;

import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.util.ImageUtil;
import com.itheima.redbaby.util.ImageUtil.ImageCallback;
import com.itheima.redbaby.vo.ProductDetail;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class productGalleryAdapter extends BaseAdapter {
	private Context context;
	private ProductDetail productDetail;
	private List<String> pic;
	private Gallery gallery;
	public productGalleryAdapter(Context context, ProductDetail productDetail,Gallery gallery) {

		this.context = context;
		this.productDetail = productDetail;
		this.gallery = gallery;
		this.pic = productDetail.getPic();
	}

	@Override
	public int getCount() {
		 return pic.size();
		
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
		} else {
			imageView = (ImageView) convertView;
		}
		String imageUrl = pic.get(position%pic.size());
		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		imageView.setTag(imagePath);
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, new ImageCallback() {
			
			@Override
			public void loadImage(Bitmap bitmap, String imagePath) {
				ImageView iView = (ImageView) imageView.findViewWithTag(imagePath);
				if(iView!=null){
					iView.setImageBitmap(bitmap);
				}				
			}
		} );
		if(bitmap==null){
			imageView.setImageResource(R.drawable.product_loading);
		}else{
			imageView.setImageBitmap(bitmap);
		}
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		
		return imageView;
	}

}
