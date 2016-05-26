package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.Product;
import com.itheima.redbaby.vo.ProductListVo;

public class SearchParser extends BaseParser<List<ProductListVo>> {

	@Override
	public List<ProductListVo> parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String search_keywords = jsonObject.getString("productlist");
			List<ProductListVo> products = JSON.parseArray(search_keywords, ProductListVo.class);
			return products;
		}
		return null;
	}

}
