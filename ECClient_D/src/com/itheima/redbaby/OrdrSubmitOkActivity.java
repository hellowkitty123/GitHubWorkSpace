package com.itheima.redbaby;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.itheima.redbaby.parser.OrderForSubmitParser;
import com.itheima.redbaby.vo.OrderForSubmit;
import com.itheima.redbaby.vo.RequestVo;

public class OrdrSubmitOkActivity extends BaseWapperActivity {
	private TextView orderid_value_text;
	private TextView paymoney_value_text;
	private TextView paytype_value_text;
	private TextView continue_shoping_text;
	private TextView to_ordr_detail_text;
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continue_shoping_text:
			finish();
			break;
		case R.id.to_ordr_detail_text:
			finish();
			Intent intent = new Intent(this,AccountActivity.class);
			startActivity(intent);
			break;
		}

	}

	@Override
	protected void findViewById() {
		orderid_value_text = (TextView) this.findViewById(R.id.orderid_value_text);
		paymoney_value_text = (TextView) this.findViewById(R.id.paymoney_value_text);
		paytype_value_text = (TextView) this.findViewById(R.id.paytype_value_text);
		continue_shoping_text = (TextView) this.findViewById(R.id.continue_shoping_text);
		to_ordr_detail_text = (TextView) this.findViewById(R.id.to_ordr_detail_text);
		continue_shoping_text.setOnClickListener(this);
		to_ordr_detail_text.setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.ordr_submit_ok_activity);
		setTitle(R.string.uphandsuccess);
		setHeadLeftVisibility(View.INVISIBLE);
	}

	@Override
	protected void processLogic() {
		RequestVo vo = new RequestVo();
		vo.context = this;
		vo.requestUrl = R.string.ordersumbit;
		vo.jsonParser = new OrderForSubmitParser();
		getDataFromServer(vo, new DataCallback<OrderForSubmit>() {
			@Override
			public void processData(OrderForSubmit paramObject,
					boolean paramBoolean) {
				orderid_value_text.setText(paramObject.orderid);
				paymoney_value_text.setText(paramObject.price);
//				支付方式
				String type = paramObject.getPaymenttype().trim();
				if("1".equals(type)){
					paytype_value_text.setText("货到付款");
				}else if("2".equals(type)){
					paytype_value_text.setText("货到POS机");
				}else if("3".equals(type)){
					paytype_value_text.setText("支付宝");
				}
			}
		});
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

}
