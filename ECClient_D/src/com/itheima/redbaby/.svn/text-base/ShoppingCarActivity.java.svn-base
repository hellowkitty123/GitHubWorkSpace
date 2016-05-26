package com.itheima.redbaby;

import com.itheima.redbaby.adapter.ShoppingCarAdapter;
import com.itheima.redbaby.parser.ShoppingCarParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.Addup;
import com.itheima.redbaby.vo.Cart;
import com.itheima.redbaby.vo.RequestVo;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ShoppingCarActivity extends BaseWapperActivity {
	protected static final String TAG = "ShoppingCarActivity";

	private ListView shopcar_product_list;

	private TextView shopcar_total_buycount_text_1; //数量总计
	private TextView shopcar_total_bonus_text_1; //赠送积分总计
	private TextView shopcar_total_money_text_1; //金额总计(不含运费)
	private String productId;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.shopcar_bottom_toPay_text:
			startActivity(new Intent(this, Payment_Center_Activity.class));
			finish();
			break;
		}
	}

	@Override
	protected void findViewById() {
		shopcar_product_list = (ListView) findViewById(R.id.shopcar_product_list);
		shopcar_total_buycount_text_1= (TextView) findViewById(R.id.shopcar_total_buycount_text_1);
		shopcar_total_bonus_text_1= (TextView) findViewById(R.id.shopcar_total_bonus_text_1);
		shopcar_total_money_text_1= (TextView) findViewById(R.id.shopcar_total_money_text_1);
		findViewById(R.id.shopcar_bottom_toPay_text).setOnClickListener(this);
		
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.shopping_car_activity);
		setTitle("购物车");
		selectedBottomTab(Constant.SHOPCAR);
	}

	@Override
	protected void processLogic() {
		//productId=getIntent().getExtras().toString();
		
		RequestVo requestVo = new RequestVo();
		requestVo.context=context;
		requestVo.jsonParser = new ShoppingCarParser();
		requestVo.requestUrl = R.string.cart;
		getDataFromServer(requestVo, new DataCallback<Cart>() {

			@Override
			public void processData(Cart paramObject, boolean paramBoolean) {
				
				Logger.d(TAG, paramObject.productlist.size()+"");
				ShoppingCarAdapter adapter = new ShoppingCarAdapter(ShoppingCarActivity.this, paramObject);
				shopcar_product_list.setAdapter(adapter);	
				if (paramObject.productlist.size() > 0) {
					Addup addup = paramObject.cart_addup ;
					shopcar_total_buycount_text_1.setText(addup.total_count + "");
					shopcar_total_bonus_text_1.setText(addup.total_point + "");
					shopcar_total_money_text_1.setText(addup.total_price + "");
				} else {
					setContentView(R.layout.shopping_none_car_activity);
				}
				
			}
		});
	}

	@Override
	protected void setListener() {
 
	}

}
