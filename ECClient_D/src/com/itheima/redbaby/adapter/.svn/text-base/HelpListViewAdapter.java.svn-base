package com.itheima.redbaby.adapter;

import java.util.ArrayList;
import java.util.Map;

import com.itheima.redbaby.R;
import com.itheima.redbaby.vo.HelpVo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HelpListViewAdapter extends BaseAdapter {

	private Context context;
	private Map<String, Object> helpMap;
	private ListView lv_help_info;
	private ArrayList al;

	public HelpListViewAdapter(Context context, Map<String, Object> helpMap,
			ListView lv_help_info) {
		this.context = context;
		this.helpMap = helpMap;
		this.lv_help_info = lv_help_info;
		al = (ArrayList) helpMap.get("help");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return al.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView tv_help_text;
		RelativeLayout rl_item;

		if (convertView == null) {

			rl_item = (RelativeLayout) View.inflate(context,
					R.layout.category_child_item, null);
		} else {
			rl_item = (RelativeLayout) convertView;
		}

		tv_help_text = (TextView) rl_item.findViewById(R.id.textContent);
		HelpVo helpVo = (HelpVo) al.get(position);
		tv_help_text.setText(helpVo.getTitle());
		return rl_item;
	}

}
