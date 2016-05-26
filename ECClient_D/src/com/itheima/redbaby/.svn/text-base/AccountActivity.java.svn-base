package com.itheima.redbaby;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redbaby.parser.SuccessParser;
import com.itheima.redbaby.parser.UserinfoParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.RequestVo;
import com.itheima.redbaby.vo.User;

public class AccountActivity extends BaseWapperActivity {
	private static final String TAG = "AccountActivity";
	private TextView my_name_text; // 用户名
	private TextView my_bonus_text; // 积分
	private TextView my_level_text; // 等级
	private LinearLayout ll_account_myorder; // 我的订单
	private LinearLayout ll_account_address_manage; // 地址管理
	private LinearLayout ll_account_conservation; // 收藏夹
	private SharedPreferences sp;

	private User info;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_account_myorder:
			Logger.d(TAG, "跳转我的订单activity");
			Intent orderIntent = new Intent(this, OrderListActivity.class);
			orderIntent.putExtra("totoalOrderCount", info.getOrdercount());
			startActivity(orderIntent);
			break;
		case R.id.ll_account_address_manage:
			Logger.d(TAG, "跳转地址管理activity");
			Intent addressManagerIntent = new Intent(this, AddressManageActivity.class);
			startActivity(addressManagerIntent);
			break;
		case R.id.ll_account_conservation:
			Logger.d(TAG, "跳转收藏夹activity");
			Intent myFavoriteIntent = new Intent(this, MyFavoriteActivity.class);
			myFavoriteIntent.putExtra("totalFavoriteCount", info.getFavoritescount());
			startActivity(myFavoriteIntent);
			break;
		}
	}

	@Override
	protected void findViewById() {
		my_name_text = (TextView) this.findViewById(R.id.my_name_text);
		my_bonus_text = (TextView) this.findViewById(R.id.my_bonus_text);
		my_level_text = (TextView) this.findViewById(R.id.my_level_text);
		ll_account_myorder = (LinearLayout) this.findViewById(R.id.ll_account_myorder);
		ll_account_address_manage = (LinearLayout) this.findViewById(R.id.ll_account_address_manage);
		ll_account_conservation = (LinearLayout) this.findViewById(R.id.ll_account_conservation);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_account_activity);
		setHeadRightVisibility(View.VISIBLE);
		setHeadRightText("退出");
		selectedBottomTab(Constant.MORE);
		setTitle(R.string.my_account_title);
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);

	}

	@Override
	protected void processLogic() {
		RequestVo vo = new RequestVo();
		vo.context = context;
		vo.requestUrl = R.string.userinfo;
		vo.jsonParser = new UserinfoParser();
		super.getDataFromServer(vo, new DataCallback<User>() {
			@Override
			public void processData(User paramObject, boolean paramBoolean) {
				if (paramObject != null) {
					info = paramObject;
					Logger.d(TAG, Thread.currentThread().getName());
					my_bonus_text.setText(info.getBonus() + "");
					my_level_text.setText(info.getLevel() + "");
					String username = sp.getString("userName", "");
					Logger.d(TAG, "userName:"+username);
					my_name_text.setText(username);
				}
			}
		});
	}

	@Override
	protected void setListener() {
		ll_account_myorder.setOnClickListener(this);
		ll_account_address_manage.setOnClickListener(this);
		ll_account_conservation.setOnClickListener(this);
	}

	@Override
	protected void onHeadRightButton(View v) {
		 
		RequestVo reqVo = new RequestVo(R.string.url_logout, context, null, new SuccessParser());
		super.getDataFromServer(reqVo, new DataCallback<Boolean>() {
			@Override
			public void processData(Boolean paramObject, boolean paramBoolean) {
				startActivity(new Intent(AccountActivity.this, HomeActivity.class));
			}
		});
	}
 
}
