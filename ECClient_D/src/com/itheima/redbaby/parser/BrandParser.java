package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.BrandCategory;



public class BrandParser extends BaseParser<List<BrandCategory>> {

	@Override
	public List<BrandCategory> parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("brand");
			return JSON.parseArray(productlist, BrandCategory.class);
		}else{
		return null;
		}
	}

}
