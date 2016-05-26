package com.itheima.redbaby;

import java.util.HashMap;
import java.util.List;

import com.itheima.redbaby.adapter.TopicLvAdapter;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.RequestVo;
import com.itheima.redbaby.vo.TopicListVo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TopicProductListActivity extends BaseWapperActivity {

	private static final String TAG = "TopicProductListActivity";


	
	private TextView tv_prodlist_ranksale;
	private TextView tv_prodlist_rankprice;
	private TextView tv_prodlist_rankgood;
	private TextView tv_prodlist_ranktime;
	private ListView lv_prodlist_listprod;
	private ImageView iv_prodlist_noresult;
	private TextView tv_prodlist_noresult;

	private List<TopicListVo> topicList;
	private SharedPreferences sp;
	boolean rankUp;
	private String cId ;
	@Override
	public void onClick(View v) {
		String orderby;
		switch (v.getId()) {
		case R.id.backTv:
			Intent backIntent = new Intent(TopicProductListActivity.this,
					BulletinActivity.class);
			startActivity(backIntent);
			break;
		case R.id.textRankSale:
			orderby = "sale_down";
			tv_prodlist_rankprice.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranksale.setBackgroundResource(R.drawable.segment_selected_1_bg);
			tv_prodlist_ranktime.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_rankgood.setBackgroundResource(R.drawable.segment_normal_2_bg);
			getRankSaleData(orderby);
			break;
		case R.id.textRankPrice:
			//可以保持用户以前设置的排序方式，提高用户体验
			
			if(rankUp){
				orderby = "price_up";
				Editor editor = sp.edit();
				editor.putBoolean("topictrank", true);
				editor.commit();
			}else{
				orderby ="price_down";
				Editor editor = sp.edit();
				editor.putBoolean("topictrank", false);
				editor.commit();
			}
			tv_prodlist_rankprice.setBackgroundResource(R.drawable.segment_selected_1_bg);
			tv_prodlist_ranksale.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranktime.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_rankgood.setBackgroundResource(R.drawable.segment_normal_2_bg);
			getRankSaleData(orderby);
			break;
		case R.id.textRankGood:
			orderby ="comment_down";
			tv_prodlist_rankprice.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranksale.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranktime.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_rankgood.setBackgroundResource(R.drawable.segment_selected_1_bg);
			getRankSaleData(orderby);
			break;
		case R.id.textRankTime:
			orderby ="shelves_down";
			tv_prodlist_rankprice.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranksale.setBackgroundResource(R.drawable.segment_normal_2_bg);
			tv_prodlist_ranktime.setBackgroundResource(R.drawable.segment_selected_1_bg);
			tv_prodlist_rankgood.setBackgroundResource(R.drawable.segment_normal_2_bg);
			getRankSaleData(orderby);
			break;
//		case R.id.productlist_sift:
//			Intent siftIntent = new Intent(ProductListActivity.this,ProductFilterActivity.class);
//			siftIntent.putExtra("cId", cId);
//			startActivityForResult(siftIntent, 121);
//			break;
		default:
			break;
		}
	}

//	@Override
//	protected void onHeadRightButton(View v) {
//		super.onHeadRightButton(v);
//		Intent siftIntent = new Intent(TopicProductListActivity.this,ProductFilterActivity.class);
//		siftIntent.putExtra("cId", cId);
//		startActivityForResult(siftIntent, 121);
//	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
	}





	private void getRankSaleData(String orderby) {
		RequestVo prodReqVo = new RequestVo();
		prodReqVo.requestUrl = R.string.topic_plist;
		prodReqVo.context = context;
		HashMap<String, String> prodMap = new HashMap<String, String>();
		prodMap.put("page", "1");
		prodMap.put("pageNum", "8");
		cId = getIntent().getStringExtra("cId");
		Logger.i(TAG, cId);
		prodMap.put("cId", cId);
		prodMap.put("orderby", orderby);
		
		prodReqVo.requestDataMap = prodMap;
		prodReqVo.jsonParser = new TopicListParser();
		super.getDataFromServer(prodReqVo, new DataCallback<List<TopicListVo>>(){

			@Override
			public void processData(List<TopicListVo> paramObject,
					boolean paramBoolean) {
				topicList = paramObject;
				if(topicList==null){
					tv_prodlist_noresult.setVisibility(View.VISIBLE);
					iv_prodlist_noresult.setVisibility(View.VISIBLE);
				}
				TopicLvAdapter adapter = new TopicLvAdapter(context, lv_prodlist_listprod, topicList);
				lv_prodlist_listprod.setAdapter(adapter);
			}
			
		});
	}

	@Override
	protected void findViewById() {
		
//		tv_prodlist_title = (TextView) findViewById(R.id.textTitle);
//		tv_prodlist_back = (TextView) findViewById(R.id.backTv);
//		bt_prodlist_sift = (Button) findViewById(R.id.productlist_sift);
		tv_prodlist_ranksale = (TextView) findViewById(R.id.textRankSale);
		tv_prodlist_rankprice = (TextView) findViewById(R.id.textRankPrice);
		tv_prodlist_rankgood = (TextView) findViewById(R.id.textRankGood);
		tv_prodlist_ranktime = (TextView) findViewById(R.id.textRankTime);
		lv_prodlist_listprod = (ListView) findViewById(R.id.productList);
		iv_prodlist_noresult = (ImageView) findViewById(R.id.listProduct);
		tv_prodlist_noresult = (TextView) findViewById(R.id.textNull);
		
	//	tv_prodlist_back.setText("返回");
	//	tv_prodlist_title.setText("产品列表");
		
//		tv_prodlist_back.setOnClickListener(this);
		tv_prodlist_ranksale.setOnClickListener(this);
		tv_prodlist_rankprice.setOnClickListener(this);
		tv_prodlist_rankgood.setOnClickListener(this);
		tv_prodlist_ranktime.setOnClickListener(this);
//		bt_prodlist_sift.setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		/**
		 * private TextView tv_prodlist_back; private TextView
		 * tv_prodlist_title; private Button bt_prodlist_sift; private TextView
		 * tv_prodlist_ranksale; private TextView tv_prodlist_rankprice; private
		 * TextView tv_prodlist_rankgood; private TextView tv_prodlist_ranktime;
		 * private ListView tv_prodlist_listprod; private ImageView
		 * iv_prodlist_noresult; private TextView tv_prodlist_noresult;
		 */
		setContentView(R.layout.product_list_activity);
		setTitle("主题列表");
		sp = getSharedPreferences("rank", MODE_PRIVATE);
		
		rankUp = sp.getBoolean("topicrank", false);
		if(rankUp == false){
			Editor editor = sp.edit();
			editor.putBoolean("topictrank", false);
			editor.commit();
		}
		
	}

	@Override
	protected void processLogic() {
		RequestVo prodReqVo = new RequestVo();
		prodReqVo.requestUrl = R.string.topic_plist;
		prodReqVo.context = context;
		HashMap<String, String> prodMap = new HashMap<String, String>();
		prodMap.put("page", "1");
		prodMap.put("pageNum", "8");
		prodMap.put("cId", getIntent().getStringExtra("cId"));
		prodReqVo.requestDataMap = prodMap;
		prodReqVo.jsonParser = new TopicListParser();
		super.getDataFromServer(prodReqVo, new DataCallback<List<TopicListVo>>(){

			@Override
			public void processData(List<TopicListVo> paramObject,
					boolean paramBoolean) {
				topicList = paramObject;
				if(topicList==null){
					tv_prodlist_noresult.setVisibility(View.VISIBLE);
					iv_prodlist_noresult.setVisibility(View.VISIBLE);
				}
				TopicLvAdapter adapter = new TopicLvAdapter(context, lv_prodlist_listprod, paramObject);
				lv_prodlist_listprod.setAdapter(adapter);
			}
			
		});
	}

	@Override
	protected void setListener() {
		lv_prodlist_listprod.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int itemID =Integer.parseInt(topicList.get(position).getId());
				Logger.i(TAG, itemID+"");
				//ProductDetailActivity
				Intent detailIntent = new Intent(TopicProductListActivity.this,ProductDetailActivity.class);
				detailIntent.putExtra("id", itemID);
				startActivity(detailIntent);
				
				
			}
			
		});
	}

}
