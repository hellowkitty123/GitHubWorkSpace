package com.itheima.redbaby.parser;

import org.json.JSONException;

import android.text.TextUtils;

/**
 * 请求是否成功，成功为true 
 * @author liu
 *
 */
public class SuccessParser extends BaseParser<Boolean> {

	@Override
	public Boolean parseJSON(String paramString) throws JSONException {
		if (!TextUtils.isEmpty(checkResponse(paramString))) {
			return true;
		}
		return false;
	}
}
