package com.itheima.redbaby;

import java.util.List;

import com.itheima.redbaby.adapter.CategoryAdaper;
import com.itheima.redbaby.parser.CategoryParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.DivideCategoryList;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.CategoryVo;
import com.itheima.redbaby.vo.RequestVo;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CategoryThreeLevelActivity extends BaseWapperActivity {

	protected static final String TAG = "CategoryThreeLevelActivity";
	private List<CategoryVo> categoryInfos;
	private DivideCategoryList divide;
	private String twoLevelID ;
	private ListView lv_category_list;
	@Override
	public void onClick(View v) {
		
	}

	@Override
	protected void findViewById() {
		/**
		 * @id/categoryList
		 */
		lv_category_list = (ListView) findViewById(R.id.categoryList);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.category_child_activity);
		setTitle(R.string.category_view);
		twoLevelID = getIntent().getStringExtra("twoLevelID");
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
				divide = new DivideCategoryList(paramObject);
				categoryInfos = divide.getNextLevel(twoLevelID);
				Logger.i(TAG, categoryInfos.size()+"");
				CategoryAdaper adapter = new CategoryAdaper(context, categoryInfos);
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
				CategoryVo vo = categoryInfos.get(position);
				String threeLevelID = (String) view.getTag();
				Logger.i(TAG, threeLevelID);
				if(threeLevelID!=null){//&&vo.isIsleafnode()
					Intent threeLevelIntent = new Intent(CategoryThreeLevelActivity.this, ProductListActivity.class);
			//		threeLevelIntent.pute
					threeLevelIntent.putExtra("cId", threeLevelID);
					startActivity(threeLevelIntent);
			//		finish();
				}else{
					Toast.makeText(getApplicationContext(), "数值没有传递成功", 0).show();
				}
			}
		});
	}


}
