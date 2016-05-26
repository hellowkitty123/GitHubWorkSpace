package com.itheima.redbaby.parser;

import org.json.JSONException;
import org.json.JSONObject;


import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.Addup;
import com.itheima.redbaby.vo.Cart;
import com.itheima.redbaby.vo.CartProduct;

public class ShoppingCarParser extends BaseParser<Cart> {

	@Override
	public Cart parseJSON(String paramString) throws JSONException {
		if(checkResponse(paramString)!=null){
			Cart cart= new Cart();
			
		
			JSONObject jsonObject = new JSONObject(paramString);

			String cartstr = jsonObject.getString("cart");
			cart =JSON.parseObject(cartstr, Cart.class);
			
			return cart;
		}
		return null;
	}
	
}
