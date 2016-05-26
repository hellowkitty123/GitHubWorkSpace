package com.itheima.redbaby;

import java.util.HashMap;
import java.util.List;
import com.itheima.redbaby.adapter.ProductFilterAdapter;
import com.itheima.redbaby.parser.ProductFilterParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.FilterCategory;
import com.itheima.redbaby.vo.ProductFilterVo;
import com.itheima.redbaby.vo.RequestVo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProductFilterActivity extends BaseWapperActivity {

	private static final String TAG = "ProductFilterActivity";
 	private ListView lv_sift_list;
	private String cId;
	private List<FilterCategory> filterInfos;
	private SharedPreferences sp;

	private int filterSize=0;
	
	@Override
	public void onClick(View v) {
	 
	}

	@Override
	protected void findViewById() {
		/**
		 * productlist_sift categoryList
		 */
 		lv_sift_list = (ListView) findViewById(R.id.categoryList);
		
 	}
	
	@Override
	protected void onHeadRightButton(View v) {
		StringBuilder sb = new StringBuilder();
		for(int i =0;i<filterInfos.size();i++){
			String fid = sp.getString("filter"+filterInfos.get(i).getKey(), null);
					if(fid!=null)
						sb.append(fid);
						sb.append("-");
						filterSize+=1;
		}
		
		Intent resultIntent = new Intent();
		resultIntent.putExtra("filterSize", filterSize);
		resultIntent.putExtra("filter", sb.toString());
		setResult(121,resultIntent);
		finish();
	}
	
	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.sift_prod_activity);
		setTitle("筛选");
		setHeadRightText("筛选");
		setHeadRightVisibility(View.VISIBLE);
		selectedBottomTab(Constant.CLASSIFY);
		cId = getIntent().getStringExtra("cId");
		sp = getSharedPreferences("config", MODE_PRIVATE);
	}

	@Override
	protected void processLogic() {
		RequestVo prodReqVo = new RequestVo();
		prodReqVo.requestUrl = R.string.prodList;
		prodReqVo.context = context;
		HashMap<String, String> prodMap = new HashMap<String, String>();
		 cId = getIntent().getStringExtra("cId");
		 Logger.i(TAG, cId);
		 prodMap.put("cId", cId);

		prodReqVo.requestDataMap = prodMap;

		prodReqVo.jsonParser = new ProductFilterParser();
		super.getDataFromServer(prodReqVo,
				new DataCallback<List<FilterCategory>>() {

					@Override
					public void processData(List<FilterCategory> paramObject,
							boolean paramBoolean) {
						filterInfos = paramObject;
						Logger.i(TAG, paramObject.size() + "");
						ProductFilterAdapter adapter = new ProductFilterAdapter(
								context, paramObject, lv_sift_list);
						lv_sift_list.setAdapter(adapter);
					}
				});
	}


	@Override
	protected void setListener() {

		lv_sift_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					 int position, long id) {
				
				final TextView tv =(TextView) view.findViewById(R.id.categoryTitle);
				final FilterCategory fc = filterInfos.get(position);
				final List<ProductFilterVo> productFilter = fc.getValue();
				
				StringBuilder bf = new StringBuilder();
				for (ProductFilterVo vo : productFilter) {
					bf.append(vo.getName());
					bf.append("-");
				}
				String a = bf.toString();
				String[] arr = a.split("-");

				new AlertDialog.Builder(ProductFilterActivity.this)
						// .setIconAttribute(android.R.attr.alertDialogIcon)
						.setTitle("选择")
						.setSingleChoiceItems(arr, 0,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Logger.i(TAG, whichButton+"");	
										String id = productFilter.get(whichButton).getId();
										Editor editor = sp.edit();
										editor.putString("filter"+fc.getKey(), id);
										editor.commit();
										tv.setText(productFilter.get(whichButton).getName());
										tv.setTextColor(Color.RED);
									}
								})
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Editor editor = sp.edit();
										editor.putString("filter"+fc.getKey(),"null");
										editor.commit();
										tv.setText("全部");
										tv.setTextColor(Color.BLACK);
									}
								}).create().show();
			}

		});
	}

}
