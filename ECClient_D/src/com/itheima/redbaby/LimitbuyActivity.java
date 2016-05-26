package com.itheima.redbaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itheima.redbaby.adapter.LimitbuyAdapter;
import com.itheima.redbaby.parser.LimitbuyParser;
import com.itheima.redbaby.vo.Limitbuy;
import com.itheima.redbaby.vo.RequestVo;
/**
 * 限时抢购
 * @author csl
 *
 */
public class LimitbuyActivity extends BaseWapperActivity {
	private List<Limitbuy> limitbuyList;
	private ListView listView;
	LimitbuyAdapter limitbuyAdapter;
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findViewById() {
		listView = (ListView) findViewById(R.id.promBulldtinLv);
	}

	
	//加载布局文件
	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.prom_bulletin_activity);
		//setContentView(R.layout.product_list_activity);
		limitbuyList = new ArrayList<Limitbuy>();
		setTitle("限时抢购");
		
		
	}
	//执行逻辑
	@Override
	protected void processLogic() {
		RequestVo reqVo = new RequestVo();
		reqVo.requestUrl = R.string.url_limitbuy;
		reqVo.context = context;
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("page", "");
		requestDataMap.put("pageNum", "");
		//requestDataMap.put("orderby", "sale_down");
		reqVo.requestDataMap = requestDataMap;
		
		reqVo.jsonParser = new LimitbuyParser();
		
		super.getDataFromServer(reqVo, new DataCallback<List<Limitbuy>>() {

			@Override
			public void processData(List<Limitbuy> paramObject,
					boolean paramBoolean) {
				limitbuyList = paramObject;
				limitbuyAdapter = new LimitbuyAdapter(limitbuyList, listView, LimitbuyActivity.this);
				listView.setAdapter(limitbuyAdapter);
				limitbuyAdapter.start();
			}

			
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (limitbuyAdapter != null)
			limitbuyAdapter.start();
	}
	
	@Override
	protected void onPause() {
 		super.onPause();
 		if (limitbuyAdapter != null)
			limitbuyAdapter.stop();
	}
	 
	//设置监听事件
	@Override
	protected void setListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Limitbuy vo = (Limitbuy) listView.getItemAtPosition(position);
				Intent producutlistIntent = new Intent(LimitbuyActivity.this,ProductDetailActivity.class);
				//将ID传递到商品分类显示中，显示相关内容
				producutlistIntent.putExtra("id", vo.getId());
				//跳转到新的activity
				startActivity(producutlistIntent);
			}
		});
		
	}

	
}


