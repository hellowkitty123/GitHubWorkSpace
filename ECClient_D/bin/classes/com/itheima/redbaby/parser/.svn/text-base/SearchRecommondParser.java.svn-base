package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

public class SearchRecommondParser extends BaseParser<String[]> {

	@Override
	public String[] parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String search_keywords = jsonObject.getString("search_keywords");
			String[] search= JSON.parseObject(search_keywords, String[].class);
			return search;
		}
		return null;
		
	}

}
