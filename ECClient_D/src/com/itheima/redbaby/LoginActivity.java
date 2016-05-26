package com.itheima.redbaby;

import java.util.HashMap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.parser.LoginParser;
import com.itheima.redbaby.util.CommonUtil;
import com.itheima.redbaby.vo.RequestVo;
import com.itheima.redbaby.vo.UserInfo;

public class LoginActivity extends BaseWapperActivity {
	private EditText login_name_edit;
	private EditText login_pwd_edit;
	private TextView login_text;
	private TextView register_text;
	private CheckBox remember_pwd_checkbox;
	private SharedPreferences sp;
	private String userName;
	private String userPwd;
	private boolean autoLogin;
	private boolean isnotLogin;

	protected void findViewById() {
		login_name_edit = (EditText) findViewById(R.id.login_name_edit);
		login_pwd_edit = (EditText) findViewById(R.id.login_pwd_edit);
		login_text = (TextView) findViewById(R.id.login_text);
		register_text = (TextView) findViewById(R.id.register_text);
		remember_pwd_checkbox = (CheckBox) findViewById(R.id.remember_pwd_checkbox);
	}

	protected void loadViewLayout() {
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		setContentView(R.layout.login_activity);
		setTitle("登录");
	}

	protected void processLogic() {
		if (getIntent().getStringExtra("notlogin") != null) {
			isnotLogin = true;
		}
		this.autoLogin = sp.getBoolean("autoLogin", true);
		if (this.autoLogin) {

		}

	}

	protected void setListener() {
		login_text.setOnClickListener(this);
		register_text.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_text:
			String userName = login_name_edit.getText().toString().trim();
			String userPwd = login_pwd_edit.getText().toString().trim();

			if (userName == null || "".equals(userName)) {
				CommonUtil.showInfoDialog(this, "用户名不能为空");
				return;
			}
			if (userPwd == null || "".equals(userPwd)) {
				CommonUtil.showInfoDialog(this, "密码不能为空");
				return;
			}
			this.userName = userName;
			this.userPwd = userPwd;
			showProgressDialog();
			RequestVo vo = new RequestVo();
			vo.requestUrl = R.string.login;
			vo.jsonParser = new LoginParser();
			vo.context = this;
			HashMap map = new HashMap();
			map.put("username", userName);
			map.put("password", userPwd);
			vo.requestDataMap = map;
			super.getDataFromServer(vo, new DataCallback<UserInfo>() {

				@Override
				public void processData(UserInfo paramObject, boolean paramBoolean) {
					if (paramObject != null) {
						if (paramObject.getUserId() == null) {
							Toast.makeText(LoginActivity.this, "用户名或是 密码错误", Toast.LENGTH_LONG).show();
							return;
						}
						Editor ed = sp.edit();

						ed.putString("userName", LoginActivity.this.userName);
						ed.putString("userPwd", LoginActivity.this.userPwd);
						if (remember_pwd_checkbox.isChecked()) {
							ed.putBoolean("autoLogin", true);
						} else {
							ed.putBoolean("autoLogin", false);
						}
						ed.commit();

						if (isnotLogin) {
							setResult(LOGIN_SUCCESS);
						} else {
							Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
							startActivity(intent);
						}
						finish();
					} else {
						Toast.makeText(LoginActivity.this, "用户名或是 密码错误", Toast.LENGTH_LONG).show();
					}
				}
			});

			break;
		case R.id.register_text:
			Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(register);

			break;

		}
	}
}
