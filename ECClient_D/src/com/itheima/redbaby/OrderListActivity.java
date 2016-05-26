package com.itheima.redbaby;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.adapter.OrderListLvAdapter;
import com.itheima.redbaby.parser.OrderListParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.vo.OrderList;
import com.itheima.redbaby.vo.PageVo;
import com.itheima.redbaby.vo.RequestVo;

public class OrderListActivity extends BaseWapperActivity implements
		OnItemClickListener {
	private static final String TAG = "OrderListActivity";
	private ListView my_order_list;
	private TextView my_order_month;
	private TextView my_order_all;
	private TextView my_order_notsend;
	private int flag = 0;
	private View pageView;

	private List<OrderList> orderlist = new ArrayList<OrderList>();
	private List<OrderList> orderlistInMonth = new ArrayList<OrderList>();
	private List<OrderList> ordercanceled = new ArrayList<OrderList>();
	private List<OrderList> cancelablelist;
	private List<OrderList> uncancelablelist;
	private OrderListLvAdapter adapter;
	private PageVo pageVo;
	TextView previousPage ;
	TextView nextPage ;
	TextView textPage ;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textCancelOrder:
			int position = my_order_list.getPositionForView(v);
			TextView cancelorder = (TextView) my_order_list.findViewWithTag(position);
			OrderList order = (OrderList) my_order_list.getItemAtPosition(position);
			Toast.makeText(context, "要取消的订单编号为："+order.getOrderid(), 0).show();
			processCancelOrder(order.getOrderid());
			break;
		case R.id.my_order_month:
			flag = 0;
			my_order_month
					.setBackgroundResource(R.drawable.segment_selected_1_bg);
			my_order_all.setBackgroundResource(R.drawable.segment_normal_2_bg);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_normal_3_bg);
			processLogic();
			break;
		case R.id.my_order_all:
			flag = 1;
			my_order_month
					.setBackgroundResource(R.drawable.segment_normal_1_bg);
			my_order_all
					.setBackgroundResource(R.drawable.segment_selected_2_bg);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_normal_3_bg);
			processLogic();
			break;
		case R.id.my_order_notsend:
			flag = 2;
			my_order_month
					.setBackgroundResource(R.drawable.segment_normal_1_bg);
			my_order_all.setBackgroundResource(R.drawable.segment_normal_2_bg);
			my_order_notsend
					.setBackgroundResource(R.drawable.segment_selected_3_bg);
			processLogic();
			break;
		case R.id.my_page_previous:
			Log.i(TAG, "显示前一页------1");
			Log.i(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum<=1){
				previousPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum -= 1;
			Log.i(TAG, "显示前一页------2");
			Log.i(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		case R.id.my_page_next:
			Log.i(TAG, "显示下一页-------1");
			Log.i(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum>=pageVo.totalPageNum){
				nextPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum += 1;
			Log.i(TAG, "显示下一页-------2");
			Log.i(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		}
	}

	private void processCancelOrder(String orderid) {
		//TODO  删除订单操作
		RequestVo vo = new RequestVo();
		vo.context = this;
		
	}

	@Override
	protected void findViewById() {
		my_order_list = (ListView) this.findViewById(R.id.my_order_list);
		my_order_month = (TextView) this.findViewById(R.id.my_order_month);
		my_order_all = (TextView) this.findViewById(R.id.my_order_all);
		my_order_notsend = (TextView) this.findViewById(R.id.my_order_notsend);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_order_activity);
		setHeadLeftVisibility(View.VISIBLE);
		setTitle(R.string.my_ordr_title);
		setHeadBackgroundResource(R.drawable.head_bg);
		selectedBottomTab(Constant.HOME);
		int totoalOrderCount = getIntent().getIntExtra("totoalOrderCount",0);
		Log.i(TAG, totoalOrderCount+"");
		pageVo = new PageVo(0, totoalOrderCount, 1);
	}

	@Override
	protected void processLogic() {
		RequestVo vo = new RequestVo();
		vo.context = this;
		vo.requestDataMap = new HashMap<String, String>();
		// TODO listview内容的分页处理
		vo.requestDataMap.put("type", flag + 1 + "");
		vo.requestDataMap.put("page", pageVo.wantedPageNum+"");
		vo.requestDataMap.put("pageNum",pageVo.pageLenth+"");

		vo.jsonParser = new OrderListParser();
		vo.requestUrl = R.string.orderlist;
		Log.i(TAG, R.string.orderlist+"");
		getDataFromServer(vo, new DataCallback<List<OrderList>>() {
			@Override
			public void processData(List<OrderList> paramObject,
					boolean paramBoolean) {
				if (paramObject != null) {
					System.out.println("paramObject的大小是：" + paramObject.size());
					divide(paramObject);
					if(pageView!=null){
						my_order_list.removeFooterView(pageView);
					}
					setAdapterForDivide();
				}
			}
		});
	}
	private void setAdapterForDivide() {
		switch (flag) {
		case 0:
			cancelableInit(orderlistInMonth);
			adapter = new OrderListLvAdapter(
					OrderListActivity.this, orderlistInMonth,cancelablelist,cancelablelist);
			
			//分页处理
			if(orderlistInMonth.size()>=pageVo.pageLenth){
				showPageBar();
			}
			my_order_list.setAdapter(adapter);
			break;
		case 1:
			cancelableInit(orderlist);
			adapter = new OrderListLvAdapter(
					OrderListActivity.this, orderlist,cancelablelist,uncancelablelist);
			//分页处理
			if(orderlist.size()>=pageVo.pageLenth){
				showPageBar();
			}
			my_order_list.setAdapter(adapter);
			break;
		case 2:
			adapter = new OrderListLvAdapter(
					OrderListActivity.this, ordercanceled,cancelablelist,uncancelablelist);
			//分页处理
			if(ordercanceled.size()==pageVo.pageLenth){
				showPageBar();
			}
			my_order_list.setAdapter(adapter);
			break;
		}
	}

	private void cancelableInit(List<OrderList> list) {
		//TODO ADD BY LQT 20120421 -----BEGIN--------------
		cancelablelist = new ArrayList<OrderList>();
		uncancelablelist = new ArrayList<OrderList>();
		//对订单是否可以取消做不同的处理
		for (OrderList item : list) {
			if(item.getFlag()==1){
				cancelablelist.add(item);
				Log.i(TAG, item.getFlag()+"-可取消的上-"+item.getStatus());
			}else{
				uncancelablelist.add(item);
				Log.i(TAG, item.getFlag()+"-不 可取消的上-"+item.getStatus());
			}
		}
		Log.i(TAG, "cancelablelist可以取消的数目为:"+cancelablelist.size());
		Log.i(TAG, "uncancelablelist不可以取消的数目为:"+uncancelablelist.size());
		//ADD BY LQT 20120421 -----END--------------
	}

	private void showPageBar() {
		pageView = View.inflate(OrderListActivity.this, R.layout.page, null);
		previousPage = (TextView) pageView.findViewById(R.id.my_page_previous);
	    nextPage = (TextView) pageView.findViewById(R.id.my_page_next);
        textPage = (TextView) pageView.findViewById(R.id.my_page_text);
		previousPage.setOnClickListener(this);
		nextPage.setOnClickListener(this);
		textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
		
		my_order_list.addFooterView(pageView);
	}
	protected void divide(List<OrderList> paramObject) {
		this.orderlist = paramObject;
		for (OrderList item : orderlist) {
			// 将已取消的条目筛选出来
			if ("已取消".equals(item.getStatus())) {
				ordercanceled.add(item);
			}
			// 将订单中近一个月内的订单放到orderlistInMonth中
			try {
				Calendar now = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = format.parse(item.getTime());
				Calendar ordertime = Calendar.getInstance();
				ordertime.setTime(date);
				ordertime.add(Calendar.MONTH, 1);
				if (ordertime.compareTo(now) >= 0) {
					System.out.println("是一个月内的");
					orderlistInMonth.add(item);
				} else {
					System.out.println("不是一个月内的");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void setListener() {
		my_order_month.setOnClickListener(this);
		my_order_all.setOnClickListener(this);
		my_order_notsend.setOnClickListener(this);
		my_order_list.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(pageView!=null&&view.equals(pageView)){
			view.setClickable(false);
			return;
		}
		TextView text = (TextView) view.findViewById(R.id.orderId_text);
		String orderId = text.getText().toString().trim();
		Log.i(TAG, "你点击了订单号"+orderId+"的订单，跳转订单详情页面");
		Intent orderDetailIntent = new Intent(context,OrderDetailActivity.class);
		orderDetailIntent.putExtra("orderId", orderId);
		startActivity(orderDetailIntent);
	
	}
}
