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
import com.itheima.redbaby.vo.BulletinVo;

public class BulletinLvAdapter extends ImageAsyncLoaderAdpter<BulletinVo> {

 	private Context context;
	private List<BulletinVo> bulletinInfos;

	public BulletinLvAdapter(Context context, AbsListView absListView, List<BulletinVo> bulletinInfos) {
		super(context, absListView, bulletinInfos);
		this.context = context;
		this.bulletinInfos = bulletinInfos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
 		Holder holder = new Holder();
		BulletinVo info = bulletinInfos.get(position);
		final View view;
		if (convertView == null) {
			view = View.inflate(context, R.layout.prom_bulletin_item, null);
		} else {
			view = convertView;
		}
		view.setTag(position);
		holder.tvContext = (TextView) view.findViewById(R.id.textContent);
		holder.imIcon = (ImageView) view.findViewById(R.id.imgIcon);
		holder.imIcon.setImageResource(R.drawable.image5);
		holder.tvContext.setText(info.getName());
		loadImage(position, info.getPic());
		return view;
	}

	@Override
	public void onImageLoadFinish(Integer t, Drawable drawable) {
		View view = mListView.findViewWithTag(t);
 		if (view != null) {
			ImageView iv = (ImageView) view.findViewById(R.id.imgIcon);
			iv.setImageDrawable(drawable);
		}
	}

	static class Holder {
		TextView tvContext;
		ImageView imIcon;
	}

}
