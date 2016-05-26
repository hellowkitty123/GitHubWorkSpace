package com.itheima.redbaby;

import java.util.HashMap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.itheima.redbaby.parser.LoginParser;
import com.itheima.redbaby.util.CommonUtil;
import com.itheima.redbaby.vo.RequestVo;
import com.itheima.redbaby.vo.UserInfo;

public class RegisterActivity extends BaseWapperActivity {
	private EditText login_name_edit;
	private EditText login_pwd_edit;
	private EditText login_pwd2_edit;
	private TextView register_text;  
	private DataCallback<UserInfo> callback;
	private SharedPreferences sp;
	 
	
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.register_text:
			String name =  login_name_edit.getText().toString().trim();
			String pwd = login_pwd_edit.getText().toString().trim();
			String pwd2 = login_pwd2_edit.getText().toString().trim();
			if(name==null||"".equals(name)){
				CommonUtil.showInfoDialog(this, "用户名不能为空");
				return ;
			}
			if(pwd==null||"".equals(pwd)){
				CommonUtil.showInfoDialog(this, "密码不能为空");
				return;
			}
			if(pwd2==null||"".equals(pwd2)){
				CommonUtil.showInfoDialog(this, "密码不能为空");
				return;
			}
			if(!(CommonUtil.isValidMobiNumber(name)||CommonUtil.isValidEmail(name))){
				CommonUtil.showInfoDialog(this, "用户名必须是邮箱或者手机号");
				return;
			}
			if(!pwd.equals(pwd2)){
				CommonUtil.showInfoDialog(this, "两次密码不同");
				return;
			}
			
			RequestVo vo = new RequestVo();
			HashMap map = new HashMap();
			map.put("username", name);
			map.put("password",pwd);
			vo.requestDataMap=map;
			vo.requestUrl= R.string.register;
			vo.jsonParser = new LoginParser();
			vo.context=context;
			getDataFromServer(vo, callback);
			Editor ed =  sp.edit();
			ed.putString("userName", name);
			ed.putString("userPwd", pwd);
			ed.commit();
			showProgressDialog();
			break;
		
		} 
	}

	@Override
	protected void findViewById() {
		login_name_edit = (EditText) findViewById(R.id.login_name_edit);
		login_pwd_edit = (EditText) findViewById(R.id.login_pwd_edit);
		login_pwd2_edit = (EditText) findViewById(R.id.login_pwd2_edit);
		register_text = (TextView) findViewById(R.id.register_text);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.register_activity);
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		setTitle("注册");
	}

	@Override
	protected void processLogic() {
		sp = getSharedPreferences("userinfo", MODE_PRIVATE);
		DataCallback<UserInfo> callback = new DataCallback<UserInfo>() {

			@Override
			public void processData(UserInfo paramObject, boolean paramBoolean) {
				String userId =  paramObject.userId;
				String usersession = paramObject.usersession;
				Editor ed =  sp.edit();
				ed.putString("userId", userId);
				ed.putString("usersession", usersession);
				ed.commit();
				Intent intent = new Intent (RegisterActivity.this,AccountActivity.class);
				startActivity(intent);
				closeProgressDialog();
				
			}
		}; 
		
		this.callback=callback;
	}

	@Override
	protected void setListener() {
		register_text.setOnClickListener(this);

	}

}
