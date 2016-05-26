package com.itheima.redbaby.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.HelpVo;

public class HelpParser extends BaseParser<Map<String,Object>> {

	@Override
	public Map<String, Object> parseJSON(String paramString)
			throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String help = jsonObject.getString("help");
			List<HelpVo> helpList = JSON.parseArray(help,HelpVo.class);
			String version = jsonObject.getString("version");
			Map<String,Object> helpMap = new HashMap<String, Object>();
			helpMap.put("version", version);
			helpMap.put("help",helpList);
			return helpMap;
		}
		
		
		return null;
	}
	

}
