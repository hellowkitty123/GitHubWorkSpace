package com.itheima.redbaby;

import java.util.HashMap;
import java.util.List;

import android.media.ToneGenerator;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.adapter.MyFavoriteAdapter;
import com.itheima.redbaby.adapter.ProductAdapter;
import com.itheima.redbaby.parser.FavoriteParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.PageVo;
import com.itheima.redbaby.vo.Product;
import com.itheima.redbaby.vo.ProductListVo;
import com.itheima.redbaby.vo.RequestVo;

public class MyFavoriteActivity extends BaseWapperActivity {
	private static final String TAG = "MyFavoriteActivity";
	private ListView myfavorite_product_list;
	private PageVo pageVo;
	private View pageView;
	TextView previousPage;
	TextView nextPage;
	TextView textPage;
	MyFavoriteAdapter adapter;
	private List<Product> data;
	
	@Override
	protected void onHeadRightButton(View v) {
		data.clear();
		// 分页处理
		if (pageView != null) {
			myfavorite_product_list.removeFooterView(pageView);
		}
		adapter.notifyDataSetChanged();
		setHeadRightVisibility(View.INVISIBLE);
		setContentView(R.layout.empty);
		TextView tv = (TextView) this.findViewById(R.id.empty_text);
		tv.setText("您的收藏夹还没有商品哦！亲");
	};
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_page_previous:
			Logger.d(TAG, "显示前一页------1");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum<=1){
				previousPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum -= 1;
			Logger.d(TAG, "显示前一页------2");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		case R.id.my_page_next:
			Logger.d(TAG, "显示下一页-------1");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			if(pageVo.wantedPageNum>=pageVo.totalPageNum){
				nextPage.setClickable(false);
				return;
			}
			processLogic();
			pageVo.wantedPageNum += 1;
			Logger.d(TAG, "显示下一页-------2");
			Logger.d(TAG,pageVo.wantedPageNum+"");
			textPage.setText(pageVo.wantedPageNum+"/"+pageVo.totalPageNum);
			break;
		}
	}

	@Override
	protected void findViewById() {
		myfavorite_product_list = (ListView) this
				.findViewById(R.id.myfavorite_product_list);

	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_favorite_activity);
		setHeadLeftVisibility(View.VISIBLE);
		setHeadRightVisibility(View.VISIBLE);
		setHeadRightText("清空");
		setTitle(R.string.my_favorite_title);
		setHeadBackgroundResource(R.drawable.head_bg);
		selectedBottomTab(Constant.HOME);
		int totalFavoriteCount = getIntent().getIntExtra("totalFavoriteCount",
				0);
		Logger.d(TAG, totalFavoriteCount + "------------");
		pageVo = new PageVo(0, totalFavoriteCount, 1);
	}

	@Override
	protected void processLogic() {
		RequestVo favoriteRequeset = new RequestVo();
		favoriteRequeset.context = this;
		favoriteRequeset.jsonParser = new FavoriteParser();
		favoriteRequeset.requestUrl = R.string.favorites;
		favoriteRequeset.requestDataMap = new HashMap<String, String>();
		favoriteRequeset.requestDataMap.put("page", pageVo.wantedPageNum + "");
		favoriteRequeset.requestDataMap.put("pageNum", pageVo.pageLenth + "");
		getDataFromServer(favoriteRequeset, new DataCallback<List<Product>>() {
			@Override
			public void processData(List<Product> paramObject,
					boolean paramBoolean) {
				data = paramObject;
				// 分页处理
				if (pageView != null) {
					myfavorite_product_list.removeFooterView(pageView);
				}
				if (paramObject.size() >= pageVo.pageLenth) {
					showPageBar();
				}
				adapter  = new MyFavoriteAdapter(MyFavoriteActivity.this,myfavorite_product_list,data);
				myfavorite_product_list.setAdapter(adapter);
			}
		});
	}

	private void showPageBar() {
		pageView = View.inflate(MyFavoriteActivity.this, R.layout.page, null);
		previousPage = (TextView) pageView.findViewById(R.id.my_page_previous);
		nextPage = (TextView) pageView.findViewById(R.id.my_page_next);
		textPage = (TextView) pageView.findViewById(R.id.my_page_text);
		previousPage.setOnClickListener(this);
		nextPage.setOnClickListener(this);
		textPage.setText(pageVo.wantedPageNum + "/" + pageVo.totalPageNum);
		myfavorite_product_list.addFooterView(pageView);
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

}
