package com.itheima.redbaby.adapter;

import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.dao.AreaDao;
import com.itheima.redbaby.vo.AddressDetail;
import com.itheima.redbaby.vo.Area;

public class AddressManageAdapter extends ArrayWapperAdapter<AddressDetail> implements OnClickListener {
	private LayoutInflater inflater;
	private OnItemButtonListener listener;
	private String[] areaDetail;
	private AreaDao areaDao;
	public static class AddressManageViewHoler {
		TextView receiver;
		TextView phone;
		TextView ads;
		TextView update;
		TextView delete;
	}

	public AddressManageAdapter(Context context) {
		super(context);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		areaDao = new AreaDao(context);
	}
	
	 @Override
	public void addAll(Collection<? extends AddressDetail> collection) {
		 super.addAll(collection);
		 areaDetail = new String[collection.size()];
	 }

	public void setListener(OnItemButtonListener listener) {
		this.listener = listener;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
 		View view;
		AddressManageViewHoler holer;
		if (convertView == null) {
			view = inflater.inflate(R.layout.address_manage_listitem, null);
			holer = new AddressManageViewHoler();
			holer.receiver = (TextView) view.findViewById(R.id.address_listitem_receiver_text);
			holer.phone = (TextView) view.findViewById(R.id.address_listitem_phone_text);
			holer.ads = (TextView) view.findViewById(R.id.address_listitem_ads_text);
			holer.update = (TextView) view.findViewById(R.id.address_manage_update_btn);
			holer.update.setOnClickListener(this);
			holer.delete = (TextView) view.findViewById(R.id.address_manage_delete_btn);
			holer.delete.setOnClickListener(this);
			view.setTag(holer);
		} else {
			view = convertView;
			holer = (AddressManageViewHoler) view.getTag();
		}
		AddressDetail item = getItem(position);
		holer.update.setTag(position);
		holer.delete.setTag(position);
		holer.receiver.setText(item.getName());
		holer.phone.setText(item.getPhonenumber());
		
		String string = areaDetail[position];
		if (string == null) {
			StringBuilder builder = new StringBuilder();
			Area area = areaDao.findByCantCode(item.getProvinceid());
			builder.append(area.getValue());
			area = areaDao.findByCantCode(item.getCityid());
			builder.append(area.getValue());
			area = areaDao.findByCantCode(item.getAreaid());
			builder.append(area.getValue());
			builder.append(" " + item.getAreadetail());
			areaDetail[position] = builder.toString();
		} 
		holer.ads.setText(areaDetail[position]);
		return view;
	}

	public static interface OnItemButtonListener {
		void onItemClick(View view, int position);
	}

	@Override
	public void onClick(View v) {
		if (listener != null) {
			listener.onItemClick(v, (Integer) v.getTag());
		}
	}
}
