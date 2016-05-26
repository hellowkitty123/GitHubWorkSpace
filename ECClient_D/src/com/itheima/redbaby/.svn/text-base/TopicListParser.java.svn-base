package com.itheima.redbaby;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.parser.BaseParser;
import com.itheima.redbaby.vo.TopicListVo;

public class TopicListParser extends BaseParser<List<TopicListVo>> {

	@Override
	public List<TopicListVo> parseJSON(String paramString)
			throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String productlist = jsonObject.getString("productlist");
			return JSON.parseArray(productlist, TopicListVo.class);
		}else{
		return null;
		}
		
	}

}
