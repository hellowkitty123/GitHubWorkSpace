package com.itheima.redbaby;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.adapter.HomeAdapter;
import com.itheima.redbaby.adapter.HomeBannerAdapter;
import com.itheima.redbaby.parser.HomeBannerParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.HomeBanner;
import com.itheima.redbaby.vo.HomeCategory;
import com.itheima.redbaby.vo.RequestVo;

/**
 * 主页面
 * 
 * 搜索<br>
 * 点击搜索按钮激活 SearchActivity 数据存在 "key_words" 通过Intent.getStringExtra("key_words") 获取
 * @author liu
 *
 */
public class HomeActivity extends BaseWapperActivity implements OnItemClickListener, OnItemSelectedListener {

	private static final String TAG = "HomeActivity";
	private ListView mCategoryListView;
	private Gallery mGallery;
	private List<ImageView> mSlideViews;
	private TextView searchEdit;
	private boolean isPlay;
	
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			if  (!isPlay)
				return ;
			mGallery.setSelection(mGallery.getSelectedItemPosition() + 1);
 			handler.postDelayed(this, 4000);
 			Logger.d(TAG, "下一张");
		}
	};
	
	private Handler handler = new Handler();

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_searchok: //搜索确定按钮
			String words = searchEdit.getText().toString();
			if (TextUtils.isEmpty(words)) {
				Toast.makeText(this, "请输入关键词", Toast.LENGTH_LONG).show();
				return ;
			}
			Intent Intent = new Intent(this, SearchProductListActivity.class);
			Intent.putExtra("keyWord", words);
			startActivity(Intent);
			break;
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
		isPlay = true;
		runnable.run();
	}
	@Override
	protected void onPause() {
		super.onPause();
		isPlay = false;
	}
	
	@Override
	protected void findViewById() {
		mCategoryListView = (ListView) findViewById(R.id.custonInfoListView);
		mGallery = (Gallery) findViewById(R.id.gallery);
		mSlideViews = new ArrayList<ImageView>();
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint0));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint1));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint2));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint3));
		mSlideViews.add((ImageView) findViewById(R.id.imgPoint4));
		searchEdit = (TextView) findViewById(R.id.editSearchInfo);
		findViewById(R.id.home_searchok).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.home_activity);
		setHeadLeftVisibility(View.INVISIBLE);
		setHeadBackgroundResource(R.drawable.head_bg_home);
		selectedBottomTab(Constant.HOME);
	}

	@Override
	protected void processLogic() {
		List<HomeCategory> categroy = new ArrayList<HomeCategory>();
		categroy.add(new HomeCategory(R.drawable.home_classify_01, "限时抢购"));
		categroy.add(new HomeCategory(R.drawable.home_classify_02, "促销快报"));
		categroy.add(new HomeCategory(R.drawable.home_classify_03, "新品上架"));
		categroy.add(new HomeCategory(R.drawable.home_classify_04, "热卖单品"));
		categroy.add(new HomeCategory(R.drawable.home_classify_05, "推荐品牌"));
		mCategoryListView.setAdapter(new HomeAdapter(this, categroy));
		RequestVo reqVo = new RequestVo(R.string.url_home, this, null, new HomeBannerParser());
		getDataFromServer(reqVo, new DataCallback<List<HomeBanner>>() {
			@Override
			public void processData(List<HomeBanner> paramObject, boolean paramBoolean) {
				HomeBannerAdapter adapter = new HomeBannerAdapter(HomeActivity.this, paramObject);
				mGallery.setAdapter(adapter);

			}
		});
	}

	@Override
	protected void setListener() {
		mGallery.setOnItemSelectedListener(this);
		mCategoryListView.setOnItemClickListener(this);
	}

	/**
	 * 首页栏点击
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0://限时抢购
			startActivity(new Intent(this,LimitbuyActivity.class));
			break;
		case 1://促销快报
			startActivity(new Intent(this, BulletinActivity.class));
			break;
		case 2://新品上架
			startActivity(new Intent(this,NewproductActivity.class));
			break;
		case 3://热卖单品
			startActivity(new Intent(this,HotproductActivity.class));
			break;
		case 4://推荐品牌
			startActivity(new Intent(this,BrandActivity.class));
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
 		int size = mSlideViews.size();
		for (int i = 0; i < size; i++) {
			int j = position % size;
			ImageView imageView = mSlideViews.get(i);
			if (j == i)
				imageView.setBackgroundResource(R.drawable.slide_adv_selected);
			else
				imageView.setBackgroundResource(R.drawable.slide_adv_normal);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}