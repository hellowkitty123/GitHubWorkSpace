package com.itheima.redbaby.adapter;

import java.util.List;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.sax.StartElementListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.BrandActivity;
import com.itheima.redbaby.PlistproductActivity;
import com.itheima.redbaby.R;
import com.itheima.redbaby.util.ImageUtil;
import com.itheima.redbaby.util.ImageUtil.ImageCallback;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.BrandCategory;

public class BrandAdapter extends BaseExpandableListAdapter{
	
	private List<BrandCategory> list;
	private ExpandableListView listView;
	private Context context;
	public BrandAdapter(List<BrandCategory> list, ExpandableListView listView,
			Context context) {
		this.list = list;
		this.listView = listView;
		this.context = context;
	}
	
	@Override
	public int getGroupCount() {
		return list.size();
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		
		return 1;
	}
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService 
			      (Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.brand_item, null);
		TextView tv = (TextView) convertView.findViewById(R.id.textParent);
		tv.setText(list.get(groupPosition).getKey());
		return convertView;
	}
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService 
			      (Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.brand_child_list, null);
		GridView gv = (GridView) convertView.findViewById(R.id.nineGv);
		gv.setAdapter(new MyGridViewAdapter(context,list.get(groupPosition),listView));
		
		return convertView;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	
}


class MyGridViewAdapter extends BaseAdapter implements OnClickListener{
	private Context context;
	private BrandCategory brandCategory;
	private ExpandableListView listView;
	private String id;
	public MyGridViewAdapter(Context context, BrandCategory brandCategory,ExpandableListView listView) {
		super();
		this.context = context;
		this.brandCategory = brandCategory;
		this.listView = listView;
	}

	@Override
	public int getCount() {
		return brandCategory.getValue().size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService 
			      (Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.brand_child_item, null);
		ImageView iv = (ImageView) convertView.findViewById(R.id.brandIconIv);
		//iv.setImageResource(brandCategory.getValue().get(position));
		
		id = brandCategory.getValue().get(position).getId() + "";
		String imageUrl = brandCategory.getValue().get(position).getPic();
		String imagePath = ImageUtil.getCacheImgPath().concat(ImageUtil.md5(imageUrl));
		Bitmap bitmap = ImageUtil.loadImage(imagePath, imageUrl, callback);
		if(bitmap==null){//从网站加载
			//设置默认图片
			iv.setImageResource(R.drawable.product_loading);
			
		}else{
			//设置本地缓存图片
			iv.setImageBitmap(bitmap);
		}
		
		iv.setOnClickListener(this);
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
	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(context,PlistproductActivity.class);
		intent.putExtra("id", id);
		context.startActivity(intent);
	}
	
}


 