package com.itheima.redbaby;

import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.adapter.ProductAdapter;
import com.itheima.redbaby.parser.SearchParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.vo.ProductListVo;
import com.itheima.redbaby.vo.RequestVo;

public class SearchProductListActivity extends BaseWapperActivity implements OnItemClickListener {
	private TextView textRankSale;
	private TextView textRankPrice;
	private TextView textRankGood;
	private TextView textRankTime;
	private ListView productList;
	private RequestVo vo;
	private HashMap<String, String> map;
	private String priceOrder = "price_up";
	private DataCallback<List<ProductListVo>> callback;
	private ProductAdapter adapter;

	public SearchProductListActivity() {
		DataCallback<List<ProductListVo>> callback = new DataCallback<List<ProductListVo>>() {


			@Override
			public void processData(List<ProductListVo> paramObject, boolean paramBoolean) {
				adapter = new ProductAdapter(context, productList, paramObject);
				productList.setAdapter(adapter);
				setTitle("搜索结果 (" + paramObject.size() + "条)");
			}
		};

		this.callback = callback;

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backTv:
			Intent intent = new Intent(SearchProductListActivity.this, SearchActivity.class);
			finish();
			startActivity(intent);

			break;
		case R.id.textRankSale:
			map.put("orderby", "sale_down");
			vo.requestDataMap = map;
			super.getDataFromServer(vo, callback);
			showProgressDialog();
			break;
		case R.id.textRankPrice:
			if (priceOrder == "price_up") {
				map.put("orderby", priceOrder);
				vo.requestDataMap = map;
				priceOrder = "price_down";
				super.getDataFromServer(vo, callback);
				showProgressDialog();

			} else {
				map.put("orderby", priceOrder);
				vo.requestDataMap = map;
				priceOrder = "price_up";
				super.getDataFromServer(vo, callback);
				showProgressDialog();

			}
			break;
		case R.id.textRankGood:
			map.put("orderby", "comment_down");
			vo.requestDataMap = map;
			super.getDataFromServer(vo, callback);
			showProgressDialog();

			break;
		case R.id.textRankTime:
			map.put("orderby", "shelves_down");
			vo.requestDataMap = map;
			super.getDataFromServer(vo, callback);
			showProgressDialog();

			break;
		}
	}

	@Override
	protected void findViewById() {
		textRankSale = (TextView) findViewById(R.id.textRankSale);
		textRankPrice = (TextView) findViewById(R.id.textRankPrice);
		textRankGood = (TextView) findViewById(R.id.textRankGood);
		textRankTime = (TextView) findViewById(R.id.textRankTime);
		productList = (ListView) findViewById(R.id.productList);

	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.search_product_list);
		selectedBottomTab(Constant.SEARCH);
	}

	@Override
	protected void processLogic() {
		String keyWord = getIntent().getStringExtra("keyWord");
		vo = new RequestVo();
		vo.context = SearchProductListActivity.this;
		map = new HashMap<String, String>();
		map.put("keyword", keyWord);
		map.put("page", 1 + "");
		map.put("pageNum", 10 + "");
		map.put("orderby", "sale_down");
		vo.requestDataMap = map;
		vo.requestUrl = R.string.search;
		vo.jsonParser = new SearchParser();
		super.getDataFromServer(vo, callback);

	}

	@Override
	protected void setListener() {
		textRankSale.setOnClickListener(this);
		textRankPrice.setOnClickListener(this);
		textRankGood.setOnClickListener(this);
		textRankTime.setOnClickListener(this);
		productList.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 		Intent intent = new Intent(this, ProductDetailActivity.class);
		intent.putExtra("id", adapter.getItem(position).getId());
		startActivity(intent);
	}

}
