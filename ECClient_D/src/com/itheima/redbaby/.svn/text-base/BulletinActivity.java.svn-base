package com.itheima.redbaby;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.itheima.redbaby.adapter.BulletinLvAdapter;
import com.itheima.redbaby.parser.BulletinParser;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.BulletinVo;
import com.itheima.redbaby.vo.RequestVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class BulletinActivity extends BaseWapperActivity {

	protected static final String TAG = "BulletinActivity";
	private ListView ll_bulletin_prom;
	private List<BulletinVo> bulletinInfos;
	
	
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		ll_bulletin_prom = (ListView) findViewById(R.id.promBulldtinLv);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.prom_bulletin_activity);
		bulletinInfos = new ArrayList<BulletinVo>();
		setTitle("促销快报");
	}

	@Override
	protected void processLogic() {
		RequestVo bulletinReqVo = new RequestVo();
		bulletinReqVo.requestUrl =R.string.topic;
		bulletinReqVo.context= context;
		HashMap<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("page", "");
		paramMap.put("pageNum", "");
		bulletinReqVo.requestDataMap = paramMap;
		
		bulletinReqVo.jsonParser = new BulletinParser();
		
		super.getDataFromServer(bulletinReqVo, new DataCallback<List<BulletinVo>>(){

			
			@Override
			public void processData(List<BulletinVo> paramObject,
					boolean paramBoolean) {
				//获得数据后进行listView数据的填充
				bulletinInfos = paramObject;
				BulletinLvAdapter bulletinAdaper = new BulletinLvAdapter(context, ll_bulletin_prom, bulletinInfos);
				ll_bulletin_prom.setAdapter(bulletinAdaper);
			}
			
		});
		
		
		
	}

	@Override
	protected void setListener() {
		ll_bulletin_prom.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
 				BulletinVo vo = (BulletinVo) ll_bulletin_prom.getItemAtPosition(position);
				String prodId = vo.getId();
				Intent producutlistIntent = new Intent(BulletinActivity.this,TopicProductListActivity.class);
				//将ID传递到商品分类显示中，显示相关内容
				producutlistIntent.putExtra("cId", prodId);
				//跳转到新的activity
				startActivity(producutlistIntent);
				Logger.i(TAG, prodId);
			//	finish();//是否需要，还是直接在配置中配置？貌似不需要，有待解决
			}
		});
	}

}
