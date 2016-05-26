package com.itheima.redbaby;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;

public class MoreActivity extends BaseWapperActivity {

	private View mBrowseRl;
	private RelativeLayout helpRelLay;
	private RelativeLayout aboutRelLay;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.recent_browse_rl:
			startActivity(new Intent(this, ProductHistoryActivity.class));
			break;
		case R.id.helpRelLay:
			startActivity(new Intent(this, HelpActivity.class));
			break;
		case R.id.aboutRelLay:
			startActivity(new Intent(this, AboutActivity.class));
			break;
		case R.id.my_account_rl:
			startActivity(new Intent(this, AccountActivity.class));
			break;
		case R.id.login:
			startActivity(new Intent(this, LoginActivity.class));
			break;

		}
	}

	@Override
	protected void findViewById() {
		mBrowseRl = findViewById(R.id.recent_browse_rl);
		helpRelLay = (RelativeLayout) findViewById(R.id.helpRelLay);
		findViewById(R.id.login).setOnClickListener(this);
		findViewById(R.id.aboutRelLay).setOnClickListener(this);
		findViewById(R.id.my_account_rl).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.more_activity);
		selectedBottomTab(Constant.MORE);
		setHeadLeftVisibility(View.INVISIBLE);
		setTitle(R.string.more);
	}

	@Override
	protected void processLogic() {

	}

	@Override
	protected void setListener() {
		mBrowseRl.setOnClickListener(this);
		helpRelLay.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null)
			Logger.d("More", "测试获取到数据" + data.getParcelableExtra("address"));
	}

}
