package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.HomeBanner;

/**
 * 首页baner 解析
 * @author liu
 *
 */
public class HomeBannerParser extends BaseParser<List<HomeBanner>>{

	@Override
	public List<HomeBanner> parseJSON(String paramString) throws JSONException {
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			JSONObject j = new JSONObject(paramString);
 			return JSON.parseArray(j.getString("home_banner"), HomeBanner.class);
		}
		return null;
	}

}
