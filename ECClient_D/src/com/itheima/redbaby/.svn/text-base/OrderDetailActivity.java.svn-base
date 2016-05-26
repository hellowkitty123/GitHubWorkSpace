package com.itheima.redbaby;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.parser.OrderDetailParser;
import com.itheima.redbaby.util.Constant;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.Address;
import com.itheima.redbaby.vo.CartProduct;
import com.itheima.redbaby.vo.CheckoutAddup;
import com.itheima.redbaby.vo.Delivery;
import com.itheima.redbaby.vo.InvoiceInfo;
import com.itheima.redbaby.vo.OrderInfo;
import com.itheima.redbaby.vo.Payment;
import com.itheima.redbaby.vo.RequestVo;

public class OrderDetailActivity extends BaseWapperActivity {

	private static final String TAG = "OrderDetailActivity";
	private String orderId;
	
	private TextView textAdress1;
	private TextView textAdress2;
	private TextView textAdress3;
	private TextView textAdress4;
	private TextView textDetail1;
	private TextView textDetail2;
	private TextView textDetail3;
	private TextView textDetail4;
	private TextView textDetail5;
	private TextView textDetail6;
	private TextView textDetail7;
	private TextView textDetail8;
	private TextView textDetail9;
	
	private ListView listdetail;//商品清单
	private TextView textPrice2;//商品金额总计：
	private TextView textPrice3;//优惠金额：
	private TextView textPrice4;//运费金额：
	private TextView textPrice5;//已支付金额：
	private TextView textPrice6;//获得积分：
	private TextView textPrice7;//应收款金额：
	
	private List<CartProduct> productlistInfo;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void findViewById() {
		// 收件人信息
		textAdress1 = (TextView) this.findViewById(R.id.textAdress1);//编号
		textAdress2 = (TextView) this.findViewById(R.id.textAdress2);//收件人
		textAdress3 = (TextView) this.findViewById(R.id.textAdress3);//收件人地址
		textAdress4 = (TextView) this.findViewById(R.id.textAdress4);//详细地址
		//订单详情
		textDetail1 = (TextView) this.findViewById(R.id.textDetail1);//订单状态
		textDetail2 = (TextView) this.findViewById(R.id.textDetail2);//送货方式
		textDetail3 = (TextView) this.findViewById(R.id.textDetail3);//支付方式
		textDetail4 = (TextView) this.findViewById(R.id.textDetail4);//订单生成时间
		textDetail5 = (TextView) this.findViewById(R.id.textDetail5);//发货时间
		textDetail6 = (TextView) this.findViewById(R.id.textDetail6);//是否开发票
		textDetail7 = (TextView) this.findViewById(R.id.textDetail7);//发票抬头
		textDetail8 = (TextView) this.findViewById(R.id.textDetail8);//送货要求
		textDetail9 = (TextView) this.findViewById(R.id.textDetail9);//备注
		//商品清单
		listdetail = (ListView) this.findViewById(R.id.listdetail);
		textPrice2 = (TextView) this.findViewById(R.id.textPrice2);
		textPrice3 = (TextView) this.findViewById(R.id.textPrice3);
		textPrice4 = (TextView) this.findViewById(R.id.textPrice4);
		textPrice5 = (TextView) this.findViewById(R.id.textPrice5);
		textPrice6 = (TextView) this.findViewById(R.id.textPrice6);
		textPrice7 = (TextView) this.findViewById(R.id.textPrice7);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.my_order_detail_activity);
		setHeadLeftVisibility(View.VISIBLE);
		setHeadBackgroundResource(R.drawable.head_bg);
		selectedBottomTab(Constant.HOME);
		orderId = getIntent().getStringExtra("orderId");
		Logger.d(TAG, orderId); 
		processLogic();
	}

	@Override
	protected void processLogic() {
		RequestVo vo = new RequestVo();
		// 设置请求参数为：订单号码
		vo.context = this;
		vo.requestDataMap = new HashMap<String, String>();
		vo.requestDataMap.put("orderId", orderId);
		vo.jsonParser = new OrderDetailParser();
		vo.requestUrl = R.string.orderdetail;
		Logger.d(TAG, "-----vo.requestUrl----------");
		getDataFromServer(vo, new DataCallback<Map<String,Object>>() {
			@Override
			public void processData(Map<String,Object>  paramObject,
					boolean paramBoolean) {
				if (paramObject != null) {
					Address address_info = (Address) paramObject.get("addressInfo");
					OrderInfo orderInfo = (OrderInfo) paramObject.get("orderInfo");
					Payment paymentInfo = (Payment) paramObject.get("paymentInfo");
					Delivery deliveryInfo = (Delivery) paramObject.get("deliveryInfo");
					InvoiceInfo invoiceInfo = (InvoiceInfo) paramObject.get("invoiceInfo");
				    productlistInfo = (List<CartProduct>) paramObject.get("productlistInfo");
					List<String> checkout = (List<String>)paramObject.get("checkout");
					CheckoutAddup checkoutAdd = (CheckoutAddup) paramObject.get("checkoutAdd");
					
					textAdress1.setText(address_info.getId()+"");
					textAdress2.setText(address_info.getName()+"");
					textAdress3.setText(address_info.getAddress_area()+"");
					textAdress4.setText(address_info.getAddress_detail()+"");
					
					textDetail1.setText(orderInfo.getStatus()+"");
					textDetail2.setText(deliveryInfo.getType()+"");
					textDetail3.setText(paymentInfo.getType()+"");
					textDetail4.setText(orderInfo.getTime()+"");
					textDetail5.setText(deliveryInfo.getType()+"");
					textDetail7.setText(invoiceInfo.getTitle()+"");
					
					textPrice2.setText(checkoutAdd.getTotal_price()+"");
					textPrice3.setText(checkoutAdd.getProm_cut()+"");
					textPrice4.setText(checkoutAdd.getFreight()+"");
					textPrice6.setText(checkoutAdd.getTotal_point()+"");
					textPrice7.setText(checkoutAdd.getTotal_price()-checkoutAdd.getProm_cut()+"");
					
					
				}
			}
		});
	}

	@Override
	protected void setListener() {
		// TODO Auto-generated method stub

	}

}
