package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.BulletinVo;

public class BulletinParser extends BaseParser<List<BulletinVo>> {

	@Override
	public List<BulletinVo> parseJSON(String paramString) throws JSONException {
		if (super.checkResponse(paramString) != null) {
			JSONObject jsonObject = new JSONObject(paramString);
			String topic = jsonObject.getString("topic");
			return JSON.parseArray(topic, BulletinVo.class);
		}else{

		return null;
		}
	}

}
