package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.vo.ProductDetail;

public class ProductDetailParser extends BaseParser<ProductDetail> {
	@Override
	public ProductDetail parseJSON(String paramString) throws JSONException {
		if(super.checkResponse(paramString)!=null){
			JSONObject jsonObject = new JSONObject(paramString);
			String product = jsonObject.getString("product");
			ProductDetail productDetail = JSON.parseObject(product,ProductDetail.class);
			return productDetail;
		}
		return null;
	}

}
