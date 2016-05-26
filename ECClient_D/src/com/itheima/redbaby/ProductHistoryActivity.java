package com.itheima.redbaby;

import java.util.List;

import com.itheima.redbaby.adapter.ProductAdapter;
import com.itheima.redbaby.service.IECManager;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.vo.ProdcutHistory;
import com.itheima.redbaby.vo.ProductListVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 浏览记录
 * @author liu
 *
 */
public class ProductHistoryActivity extends BaseWapperActivity implements OnItemClickListener {

	private ListView mListView;
	private IECManager ecManager;
	private ProductAdapter productAdapter;

	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		mListView = (ListView) findViewById(R.id.simple_list_view);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.simple_listview);
		setHeadRightText(R.string.clear);
		setHeadRightVisibility(View.VISIBLE);
		selectedBottomTab(Constant.MORE);
		ecManager = getECManager();
	}

	@Override
	protected void processLogic() {
		 
		 	
	}
	public void loadData() {
		List<ProdcutHistory> histories = ecManager.getAllProductHistory();
		if (histories.size() > 0) {
			productAdapter = new ProductAdapter(this, mListView);
			productAdapter.addAll(histories);
			mListView.setAdapter(productAdapter);
		} else {
			setContentView(R.layout.empty);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadData();
	}
	
	@Override
	protected void onHeadRightButton(View v) {
		ecManager.clearProductHistory();
		setContentView(R.layout.empty);
	}
	
	@Override
	protected void setListener() {
		mListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ProductListVo item = productAdapter.getItem(position);
		Intent intent = new Intent(this, ProductDetailActivity.class);
		intent.putExtra("id", item.getId());
		startActivity(intent );
	}
}
