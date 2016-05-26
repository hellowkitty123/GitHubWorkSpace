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
import com.itheima.redbaby.vo.Product;

public class SearchResultAdapter extends BaseAdapter {
	private Context context;
	private List<Product> products;
	private ListView lv;
	private ImageCallback callback = new ImageCallback(){

		@Override
		public void loadImage(Bitmap bitmap, String imagePath) {
			ImageView imgView = (ImageView) lv.findViewWithTag(imagePath);//�ñ�ʶʶ��ImageView
			if(imgView!=null){
				imgView.setImageBitmap(bitmap);
			}
		}
		
	};
	public SearchResultAdapter(Context context,List<Product> products,ListView lv){
		this.context = context;
		this.products = products;
		this.lv=lv;
		
		
	}
	
	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holderView;
		if(convertView!=null){
			holderView = (HolderView) convertView.getTag();
		}else{
			LayoutInflater inflater = (LayoutInflater)context.getSystemService 
				      (Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.product_list_items, null);
			holderView = new HolderView();
			convertView.setTag(holderView);
			//������Ӧ
			holderView.goodsIconIv = (ImageView) convertView.findViewById(R.id.goodsIconIv);
			holderView.textClothesName = (TextView) convertView.findViewById(R.id.textClothesName);
			holderView.textClothesPrice = (TextView) convertView.findViewById(R.id.textClothesPrice);
			holderView.textMarketPrice = (TextView) convertView.findViewById(R.id.textMarketPrice);
		
		}
		
		holderView.textClothesName.setText(products.get(position).name + "");
		holderView.textClothesPrice.setText(products.get(position).price  + "");
		holderView.textMarketPrice.setText(products.get(position).marketprice  + "");
		String imageUrl = products.get(position).pic;
		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		holderView.goodsIconIv.setTag(imagePath);//��ͼƬ���ʶ
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, callback);
		if(bitmap!=null){
			holderView.goodsIconIv.setImageBitmap(bitmap);
		}else{
			holderView.goodsIconIv.setImageResource(R.drawable.product_loading);
		}
		return convertView;
		
		
//		View view = View.inflate(context, R.layout.product_list_items, null);
//		Product product = products.get(position);
//		ImageView goodsIconIv= (ImageView) view.findViewById(R.id.goodsIconIv);
//		TextView textClothesName= (TextView) view.findViewById(R.id.textClothesName);
//		TextView textClothesPrice = (TextView) view.findViewById(R.id.textClothesPrice);
//		TextView textMarketPrice = (TextView) view.findViewById(R.id.textMarketPrice);
//		textClothesName.setText(product.name);
//		textClothesPrice.setText(product.price);
//		textMarketPrice.setText(product.marketprice);
		
//		
//		return view;
	}

}


class HolderView{
	public ImageView goodsIconIv;
	public TextView textClothesName;
	public TextView textClothesPrice;
	public TextView textMarketPrice;
	public TextView textProductComment;
	
	
}
