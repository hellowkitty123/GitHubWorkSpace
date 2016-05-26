package com.itheima.redbaby;

import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redbaby.application.ECApplication;
import com.itheima.redbaby.service.IECManager;
import com.itheima.redbaby.util.CommonUtil;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.util.NetUtil;
import com.itheima.redbaby.util.NetUtil.Status;
import com.itheima.redbaby.util.ThreadPoolManager;
import com.itheima.redbaby.vo.RequestVo;

public abstract class BaseWapperActivity extends Activity implements OnClickListener {
	private static final String TAG = "BaseWapperActivity";

	private final static Integer DEFAULT_INDEX = 1;
	
	/** 登录请求码 */
	public static final int NOT_LOGIN = 403;
	
	/** 登录结果码*/
	public static final int LOGIN_SUCCESS = 10000000;
	
	protected int activityCase;
	private ImageView classify;
	private ImageView home;
	private ImageView more;
	protected ProgressDialog progressDialog;
	private ImageView search;
	private ImageView shopCar;
	protected TextView textShopCarNum;
	protected Context context;
	private ThreadPoolManager threadPoolManager;
	// private ECApplication application;
	private LinearLayout layout_content;

	/** ContentView */
	private View inflate;

	private RelativeLayout head_layout; // TitleLayout

	/** 左边的button */
	private Button headLeftBtn;
	/** 右边的button */
	private Button headRightBtn;
	/** 标题 */
	private TextView head_title;
	private ButtomClick buttomClick;
	private ECApplication application;

	private List<BaseTask> record = new Vector<BaseTask>();

	public BaseWapperActivity() {
		threadPoolManager = ThreadPoolManager.getInstance();
	}

	/**
	 * Android生命周期回调方法-创建
	 */
	@Override
	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		application = (ECApplication) getApplication();
		application.addActvity(this);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.setContentView(R.layout.frame);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		buttomClick = new ButtomClick();
		layout_content = (LinearLayout) super.findViewById(R.id.frame_content);
		head_layout = (RelativeLayout) super.findViewById(R.id.head_layout);
		head_title = (TextView) super.findViewById(R.id.head_title);
		headLeftBtn = (Button) super.findViewById(R.id.head_left);
		headRightBtn = (Button) super.findViewById(R.id.head_right);
		headLeftBtn.setOnClickListener(buttomClick);
		headRightBtn.setOnClickListener(buttomClick);
		context = getApplicationContext();
 		initView();
 	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_item, menu);
 		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.menu_home:
			startActivity(new Intent(BaseWapperActivity.this, HomeActivity.class));
			break;
		case R.id.menu_search:
			startActivity(new Intent(BaseWapperActivity.this, SearchActivity.class));
			break;
		case R.id.menu_order:
			startActivity(new Intent(BaseWapperActivity.this, OrderListActivity.class));
			break;
		case R.id.menu_favorites:
			startActivity(new Intent(BaseWapperActivity.this, MyFavoriteActivity.class));
			break;
		case R.id.menu_history:
			startActivity(new Intent(BaseWapperActivity.this, ProductHistoryActivity.class));
			break;
		case R.id.menu_exit:
			application.exit();
			break;
 
		}
		return true;
	}
	
	public IECManager getECManager() {
		return application.getEcManager();
	}

	@Override
	public void setContentView(int layoutResID) {
		inflate = getLayoutInflater().inflate(layoutResID, null);
		setContentView(inflate);
	}

	public void setContentView(View view) {
		layout_content.removeAllViews();
		layout_content.addView(inflate);
	};

	@Override
	public View findViewById(int id) {
		return inflate.findViewById(id);
	}

	/**
	 * 
	 */
	private void initView() {
		if (isLoadBottomTab()) {
			loadBottomTab();
			selectedBottomTab(DEFAULT_INDEX);
		}
		loadViewLayout();
		findViewById();
		setListener();
		processLogic();
	}

	@Override
	public void setTitle(CharSequence title) {
		head_title.setText(title);
	}

	@Override
	public void setTitle(int titleId) {
		head_title.setText(titleId);
	}

	/**
	 * 
	 * @author Mathew
	 * 
	 */
	@SuppressWarnings("unchecked")
	class BaseHandler extends Handler {
 		private Context context;
		private DataCallback callBack;
		private RequestVo reqVo;

		public BaseHandler(Context context, DataCallback callBack, RequestVo reqVo) {
			this.context = context;
			this.callBack = callBack;
			this.reqVo = reqVo;
		}

		public void handleMessage(Message msg) {
			closeProgressDialog();
			if (msg.what == Constant.SUCCESS) {
				if (msg.obj == null) {
					CommonUtil.showInfoDialog(context, getString(R.string.net_error));
				} else {
					callBack.processData(msg.obj, true);
				}
			} else if (msg.what == Constant.NET_FAILED) {
				CommonUtil.showInfoDialog(context, getString(R.string.net_error));
			}
			
			Logger.d(TAG, "recordSize:" + record.size());
		}
	}

	class BaseTask implements Runnable {
		private Context context;
		private RequestVo reqVo;
		private Handler handler;

		public BaseTask(Context context, RequestVo reqVo, Handler handler) {
			this.context = context;
			this.reqVo = reqVo;
			this.handler = handler;
		}

		@Override
		public void run() {
			Object obj = null;
			Message msg = Message.obtain();
			try {
				if (NetUtil.hasNetwork(context)) {
					obj = NetUtil.post(reqVo);
					if (obj instanceof Status) {
						Intent intent = new Intent(BaseWapperActivity.this, LoginActivity.class);
						intent.putExtra("notlogin", "notlogin");
						startActivityForResult(intent, NOT_LOGIN);
					} else {
						msg.what = Constant.SUCCESS;
						msg.obj = obj;
						handler.sendMessage(msg);
						record.remove(this);
					}
				} else {
					msg.what = Constant.NET_FAILED;
					msg.obj = obj;
					handler.sendMessage(msg);
					record.remove(this);
				}
			} catch (Exception e) {
				record.remove(this);
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == NOT_LOGIN) {
			if (resultCode == LOGIN_SUCCESS) {
				int size = record.size();
				if (size > 0) {
					for (int i = 0; i < size; i ++) {
						threadPoolManager.addTask(record.get(0));
					}
				}
			} else {
				finish();
			}
		}
	}

	/**
	 * 
	 */
	private void loadBottomTab() {
		ImageView localImageView1 = (ImageView) super.findViewById(R.id.imgHome);
		this.home = localImageView1;
		ImageView localImageView2 = (ImageView) super.findViewById(R.id.imgClassify);
		this.classify = localImageView2;
		ImageView localImageView3 = (ImageView) super.findViewById(R.id.imgSearch);
		this.search = localImageView3;
		ImageView localImageView4 = (ImageView) super.findViewById(R.id.imgShoppingCar);
		this.shopCar = localImageView4;
		ImageView localImageView5 = (ImageView) super.findViewById(R.id.imgMore);
		this.more = localImageView5;
		textShopCarNum = (TextView) super.findViewById(R.id.textShopCarNum);
		this.home.setOnClickListener(buttomClick);
		this.classify.setOnClickListener(buttomClick);
		this.search.setOnClickListener(buttomClick);
		this.shopCar.setOnClickListener(buttomClick);
		this.more.setOnClickListener(buttomClick);
	}

	/**
	 * tab条图片切换
	 * 
	 * @param paramViewId
	 */
	protected void selectedBottomTab(int paramViewId) {
		this.home.setBackgroundResource(R.drawable.bar_home_normal);
		this.classify.setBackgroundResource(R.drawable.bar_class_normal);
		this.search.setBackgroundResource(R.drawable.bar_search_normal);
		this.shopCar.setBackgroundResource(R.drawable.bar_shopping_normal);
		this.more.setBackgroundResource(R.drawable.bar_more_normal);
		switch (paramViewId) {
		case Constant.HOME:
			Constant.defaultIndex = Constant.HOME;
			this.home.setBackgroundResource(R.drawable.bar_home_selected);
			break;
		case Constant.CLASSIFY:
			this.classify.setBackgroundResource(R.drawable.bar_class_selected);
			Constant.defaultIndex = Constant.CLASSIFY;
			break;
		case Constant.SEARCH:
			this.search.setBackgroundResource(R.drawable.bar_search_selected);
			Constant.defaultIndex = Constant.SEARCH;
			break;
		case Constant.SHOPCAR:
			this.shopCar.setBackgroundResource(R.drawable.bar_shopping_selected);
			Constant.defaultIndex = Constant.SHOPCAR;
			break;
		case Constant.MORE:
			this.more.setBackgroundResource(R.drawable.bar_more_selected);
			Constant.defaultIndex = Constant.MORE;
			break;
		}
	}

	/**
	 * 是否加载底部tab
	 * 
	 * @return
	 */
	protected Boolean isLoadBottomTab() {
		return true;
	}

	public abstract interface DataCallback<T> {
		public abstract void processData(T paramObject, boolean paramBoolean);
	}

	/**
	 * 从服务器上获取数据，并回调处理
	 * 
	 * @param reqVo
	 * @param callBack
	 */
	protected void getDataFromServer(RequestVo reqVo, DataCallback callBack) {
		showProgressDialog();
		BaseHandler handler = new BaseHandler(this, callBack, reqVo);
		BaseTask taskThread = new BaseTask(this, reqVo, handler);
		record.add(taskThread);
		this.threadPoolManager.addTask(taskThread);
	}

	/**
	 * 显示提示框
	 */
	protected void showProgressDialog() {
		if ((!isFinishing()) && (this.progressDialog == null)) {
			this.progressDialog = new ProgressDialog(this);
		}
		this.progressDialog.setTitle(getString(R.string.loadTitle));
		this.progressDialog.setMessage(getString(R.string.LoadContent));
		this.progressDialog.show();
	}

	/**
	 * 关闭提示框
	 */
	protected void closeProgressDialog() {
		if (this.progressDialog != null)
			this.progressDialog.dismiss();
	}

	/**
	 * 
	 */
	protected abstract void findViewById();

	/**
	 * 
	 */
	protected abstract void loadViewLayout();

	/**
	 * 向后台请求数据
	 */
	protected abstract void processLogic();

	/**
	 * 
	 */
	protected abstract void setListener();

	private class ButtomClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imgHome:
				startActivity(new Intent(BaseWapperActivity.this, HomeActivity.class));
				break;
			case R.id.imgClassify:
				startActivity(new Intent(BaseWapperActivity.this, CategoryActivity.class));
				break;
			case R.id.imgSearch:
				startActivity(new Intent(BaseWapperActivity.this, SearchActivity.class));
				break;
			case R.id.imgShoppingCar:
				startActivity(new Intent(BaseWapperActivity.this, ShoppingCarActivity.class));
				break;
			case R.id.imgMore:
				startActivity(new Intent(BaseWapperActivity.this, MoreActivity.class));
				break;
			case R.id.head_left:
				onHeadLeftButton(v);
				break;
			case R.id.head_right:
				onHeadRightButton(v);
				break;
			}
		}
	}

	protected void onHeadLeftButton(View v) {
		finish();
	}

	protected void onHeadRightButton(View v) {

	}

	protected void setHeadLeftText(CharSequence text) {
		headLeftBtn.setText(text);
	}

	protected void setHeadLeftText(int resid) {
		headLeftBtn.setText(resid);
	}

	protected void setHeadLeftBackgroundResource(int resid) {
		headLeftBtn.setBackgroundResource(resid);
	}

	protected void setHeadLeftVisibility(int visibility) {
		headLeftBtn.setVisibility(visibility);
	}

	protected void setHeadRightText(CharSequence text) {
		headRightBtn.setText(text);
	}

	protected void setHeadRightText(int resid) {
		headRightBtn.setText(resid);
	}

	protected void setHeadRightBackgroundResource(int resid) {
		headRightBtn.setBackgroundResource(resid);
	}

	protected void setHeadRightVisibility(int visibility) {
		headRightBtn.setVisibility(visibility);
	}

	protected void setHeadBackgroundResource(int resid) {
		head_layout.setBackgroundResource(resid);
	}

	protected void BackgroundDrawable(Drawable d) {
		head_layout.setBackgroundDrawable(d);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		application.removeActvity(this);
		record.clear();
		record = null;
		classify = null;
		home = null;
		more = null;
		search = null;
		shopCar = null;
		textShopCarNum = null;
		context = null;
		threadPoolManager = null;
		layout_content = null;
		inflate = null;
		head_layout = null;
		headLeftBtn = null;
		headRightBtn = null;
		head_title = null;
		buttomClick = null;
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
		application = null;
	}
}
