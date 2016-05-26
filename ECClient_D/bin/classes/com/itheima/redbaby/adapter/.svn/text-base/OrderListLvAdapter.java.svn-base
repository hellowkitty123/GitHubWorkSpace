package com.itheima.redbaby.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.OrderListActivity;
import com.itheima.redbaby.R;
import com.itheima.redbaby.vo.OrderList;

public class OrderListLvAdapter extends BaseAdapter{
	private static final String TAG = "OrderListLvAdapter";
	private Context context;
	private List<OrderList> paramList;
	private List<OrderList> cancelablelist;
	private List<OrderList> uncancelablelist;
	private OrderListActivity activity;
	
	private OrderList orderVo = null;
	public OrderListLvAdapter(Context context, List<OrderList> paramList
			, List<OrderList> cancelablelist, List<OrderList> uncancelablelist){
		this.context = context;
		this.activity = (OrderListActivity) context;
		this.paramList = paramList;
		this.cancelablelist = cancelablelist;
		this.uncancelablelist = uncancelablelist;
		Log.i(TAG, "cancelablelist可以取消的数目为:"+cancelablelist.size());
		Log.i(TAG, "uncancelablelist不可以取消的数目为:"+uncancelablelist.size());
	}
	@Override
	public int getCount() {
		return paramList.size();
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "当前列表下总订单大小为："+ paramList.size());
		Log.i(TAG, "可取消订单的数目为："+ cancelablelist.size());
		Log.i(TAG, "不可取消订单的数目为："+ uncancelablelist.size());
		if(cancelablelist.size()>0 && position<=cancelablelist.size()-1){
			return cancelablelist.get(position);
		}else if(position>cancelablelist.size()-1 && position<=paramList.size()-1){
			return uncancelablelist.get(position-cancelablelist.size());
		}
		return 0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "当前位置为："+ position);
		View view = null;
		if(cancelablelist.size()>0 && position<=cancelablelist.size()-1){
			view = View.inflate(context, R.layout.my_order_listitem_cancelable, null);
			Holder.orderId_text = (TextView) view.findViewById(R.id.orderId_text);
			Holder.orderPrice_text = (TextView) view.findViewById(R.id.orderPrice_text);
			Holder.orderTime_text = (TextView) view.findViewById(R.id.orderTime_text);
			Holder.orderState_text = (TextView)view.findViewById(R.id.orderState_text);
			Holder.textCancelOrder = (TextView)view.findViewById(R.id.textCancelOrder);
			Holder.textCancelOrder.setOnClickListener(activity);
			
			Holder.textCancelOrder.setTag(position);
		}else if(position>cancelablelist.size()-1 && position<=paramList.size()-1){
			view = View.inflate(context, R.layout.my_order_listitem, null);
			Holder.orderId_text = (TextView) view.findViewById(R.id.orderId_text);
			Holder.orderPrice_text = (TextView) view.findViewById(R.id.orderPrice_text);
			Holder.orderTime_text = (TextView) view.findViewById(R.id.orderTime_text);
			Holder.orderState_text = (TextView)view.findViewById(R.id.orderState_text);
		}else{
			Log.i(TAG, "XXXXXXXXXXXXXXXX");
		}
		
//		orderVo = this.paramList.get(position);
		if(cancelablelist.size()>0 && position<=cancelablelist.size()-1){
			orderVo = cancelablelist.get(position);
		}else if(position>cancelablelist.size()-1 && position<=paramList.size()-1){
			orderVo = uncancelablelist.get(position-cancelablelist.size());
		}else{
			Log.i(TAG, "XXXXXXXXXXXXXXXX");
		}
		Log.i(TAG, orderVo.getFlag()+"-可取消的上-"+orderVo.getStatus());
		paint(orderVo);
		return view;
	}
	private void paint(OrderList orderVo) {
		Holder.orderId_text.setText(orderVo.getOrderid()+"");
		Holder.orderPrice_text.setText(orderVo.getPrice()+"");
		Holder.orderTime_text.setText(orderVo.getTime()+"");
		Holder.orderState_text.setText(orderVo.getStatus()+"");
//		if("1".equals(orderVo.getFlag()+"")){
//			Holder.textCancelOrder.setText("取消订单");
//		}else if("2".equals(orderVo.getFlag()+"")){
//			Holder.textCancelOrder.setText("待确认");
//		}else if("3".equals(orderVo.getFlag()+"")){
//			Holder.textCancelOrder.setText("已完成");
//		}
		if("1".equals(orderVo.getFlag()+"")){
			Holder.textCancelOrder.setText("取消订单");
		}
	}
	static class Holder{
		//订单编号
		static TextView orderId_text;
		//总价
		static TextView orderPrice_text;
		//下单时间
		static TextView orderTime_text;
		//状态
		static TextView orderState_text;
		//取消订单
		static TextView textCancelOrder;
	}
}
