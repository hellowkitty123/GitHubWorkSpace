package com.itheima.redbaby.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.Address;
import com.itheima.redbaby.vo.CartProduct;
import com.itheima.redbaby.vo.CheckoutAddup;
import com.itheima.redbaby.vo.Delivery;
import com.itheima.redbaby.vo.InvoiceInfo;
import com.itheima.redbaby.vo.OrderInfo;
import com.itheima.redbaby.vo.Payment;

public class OrderDetailParser extends BaseParser<Map<String,Object>> {

	private static final String TAG = "OrderDetailParser";

	@Override
	public Map<String,Object> parseJSON(String paramString) throws JSONException {
		Logger.d(TAG, "解析orderdetail订单详细信息");
		Map<String,Object> map = new HashMap<String, Object>();
				
		JSONObject json = new JSONObject(paramString);
		String order = json.getString("order_info");
		OrderInfo orderInfo = JSON.parseObject(order,OrderInfo.class);
		map.put("orderInfo", orderInfo);
		Logger.d(TAG, "解析orderInfo 成功");
		
		String address = json.getString("address_info");
		Address addressInfo = JSON.parseObject(address, Address.class);
		map.put("addressInfo", addressInfo);
		Logger.d(TAG, "解析addreInfo 成功");
		
		String payment = json.getString("payment_info");
		Payment paymentInfo = JSON.parseObject(payment, Payment.class);
		map.put("paymentInfo", paymentInfo);
		Logger.d(TAG, "解析paymentInfo 成功");
		
		String delivery = json.getString("delivery_info");
		Delivery deliveryInfo = JSON.parseObject(delivery, Delivery.class);
		map.put("deliveryInfo", deliveryInfo);
		Logger.d(TAG, "解析deliveryInfo 成功");
		
		String invoice = json.getString("invoice_info");
		InvoiceInfo invoiceInfo = JSON.parseObject(invoice, InvoiceInfo.class);
		map.put("invoiceInfo", invoiceInfo);
		Logger.d(TAG, "解析invoiceInfo 成功");
		
		String productlist = json.getString("productlist");
		List<CartProduct> productlistInfo = JSON.parseArray(productlist, CartProduct.class);
		map.put("productlistInfo", productlistInfo);
		Logger.d(TAG, "解析productlistInfo 成功");
		
		String checkout_prom = json.getString("checkout_prom");
		List<String> checkoutList = JSON.parseArray(checkout_prom, String.class);
		map.put("checkout", checkoutList);
		Logger.d(TAG, "解析checkout 成功");
		
		String checkout_addup = json.getString("checkout _addup");
		CheckoutAddup checkoutAdd = JSON.parseObject(checkout_addup,CheckoutAddup.class);
		map.put("checkoutAdd", checkoutAdd);
		Logger.d(TAG, "解析checkoutAdd 成功");
		
		return map;
	}
}
