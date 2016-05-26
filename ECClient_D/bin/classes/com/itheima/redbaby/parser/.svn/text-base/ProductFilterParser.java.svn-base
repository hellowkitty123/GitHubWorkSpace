package com.itheima.redbaby.parser;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.FilterCategory;

public class ProductFilterParser extends BaseParser<List<FilterCategory>> {

	@Override
	public List<FilterCategory> parseJSON(String paramString)
			throws JSONException {
		if (super.checkResponse(paramString) != null) {
			JSONObject jsonObject = new JSONObject(paramString);
			String filter = jsonObject.getString("list_filter");
			return JSON.parseArray(filter, FilterCategory.class);
		}else{

		return null;
		}
	}

	

}
