package com.itheima.redbaby;

import java.util.List;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.adapter.CategoryAdaper;
import com.itheima.redbaby.parser.CategoryParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.DivideCategoryList;
import com.itheima.redbaby.util.Logger;


import com.itheima.redbaby.vo.CategoryVo;
import com.itheima.redbaby.vo.RequestVo;





import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class CategoryActivity extends BaseWapperActivity {

	private static final String TAG = "CategoryActivity";
	private ListView lv_category_list;
	private TextView tv_category_empty;
	private List<CategoryVo> categoryInfos;
	List<CategoryVo> oneInfos ;

	private DivideCategoryList divide;
//	private 
	@Override
	public void onClick(View v) {

	}

	@Override
	protected void findViewById() {
		/**
		 * categoryList
		 * categoryEmptyListTv
		
		 */
		lv_category_list = (ListView) findViewById(R.id.categoryList);
		tv_category_empty = (TextView) findViewById(R.id.categoryEmptyListTv);
		
		

	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.category_activity);
	//	setTitle(R.string.category_view);
		setTitle(R.string.category_view);
		setHeadLeftVisibility(View.INVISIBLE);
		selectedBottomTab(Constant.CLASSIFY);
	}

	@Override
	protected void processLogic() {
		RequestVo categoryReqVo = new RequestVo();
		categoryReqVo.requestUrl =R.string.category;
		categoryReqVo.context= context;
		
		
		
		categoryReqVo.jsonParser = new CategoryParser();
		super.getDataFromServer(categoryReqVo, new DataCallback<List<CategoryVo>>(){

			@Override
			public void processData(List<CategoryVo> paramObject,
					boolean paramBoolean) {
				categoryInfos = paramObject;
				divide = new DivideCategoryList(categoryInfos);
				oneInfos = divide.getOneLevel();
				Logger.i(TAG, categoryInfos.size()+"");
				CategoryAdaper adapter = new CategoryAdaper(context, oneInfos);
				tv_category_empty.setVisibility(View.INVISIBLE);
				lv_category_list.setAdapter(adapter);
				
			}
		});
		
	}

	
	
	@Override
	protected void setListener() {
		lv_category_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String oneLevelID = (String) view.getTag();
				Logger.i(TAG, oneLevelID);
				if(oneLevelID!=null){
					if(categoryInfos.get(position).isIsleafnode()){
						Intent prodIntent = new Intent(
								CategoryActivity.this,
								ProductListActivity.class);
						prodIntent.putExtra("cId", oneLevelID);
						startActivity(prodIntent);
						
					}else{
						Intent twoLevelIntent = new Intent(CategoryActivity.this, CategoryTwoLevelActivity.class);
						twoLevelIntent.putExtra("oneLevelID", oneLevelID);
						startActivity(twoLevelIntent);
					}
					
				}else{
					Toast.makeText(getApplicationContext(), "数值没有传递成功", 0).show();
				}
			}
		});
	}

	
}
