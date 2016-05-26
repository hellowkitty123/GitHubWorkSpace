package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.Limitbuy;
import com.itheima.redbaby.vo.ProductListVo;



public class LimitbuyParser extends BaseParser<List<Limitbuy>> {

	@Override
	public List<Limitbuy> parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("productlist");
			return JSON.parseArray(productlist, Limitbuy.class);
		}else{
		return null;
		}
	}

}
