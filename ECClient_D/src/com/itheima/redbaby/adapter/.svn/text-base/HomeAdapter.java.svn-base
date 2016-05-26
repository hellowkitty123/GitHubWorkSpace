package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.vo.HomeCategory;

/**
 * 首页栏目Adapter
 * @author liu
 *
 */
public class HomeAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<HomeCategory> categroy;

	public HomeAdapter(Context context, List<HomeCategory> categroy) {
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.categroy = categroy;
	}

	@Override
	public int getCount() {
		return categroy.size();
	}
 
	
	@Override
	public Object getItem(int position) {
		return categroy.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 
		View view;
		HomeCategory item = categroy.get(position);
		if (convertView == null)
			view = inflater.inflate(R.layout.home_item, null);
		else
			view = convertView;
		((TextView) view.findViewById(R.id.textContent)).setText(item.getTitle());
		((ImageView)view.findViewById(R.id.imgIcon)).setBackgroundResource(item.getImgresid());
		return view;
	}
	
 
}
