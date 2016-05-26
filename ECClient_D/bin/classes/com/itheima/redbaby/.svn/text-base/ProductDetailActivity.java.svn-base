package com.itheima.redbaby;

import java.util.HashMap;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.redbaby.adapter.productGalleryAdapter;
import com.itheima.redbaby.parser.ProductDetailParser;
import com.itheima.redbaby.parser.SuccessParser;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.vo.ProdcutHistory;
import com.itheima.redbaby.vo.ProductDetail;
import com.itheima.redbaby.vo.RequestVo;

public class ProductDetailActivity extends BaseWapperActivity {

	private static final String TAG = "ProductDetailActivity";
	private ProductDetail productDetail;
	private TextView titleBack;
	private Gallery productGallery;
	private ImageView imgPoint;
	private TextView textProductNameValue;
	private TextView textProductIdValue;
	private TextView textOriginalPriceValue;
	private TextView textProdGradeValue;
	private TextView textPriceValue;
	private EditText prodNumValue;
	private TextView textPutIntoShopcar;
	private ImageView imgServiceImg;
	private TextView textProdIsStock;
	private TextView textProductCommentNum;
	private TextView orderTelTv;
	private ProductDetail currentProduct;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.textProdToCollect:
			if (currentProduct == null)
				return ;
			HashMap<String, String> requestDataMap = new HashMap<String, String>();
			requestDataMap.put("id", currentProduct.getId() + "");
			RequestVo reqVo = new RequestVo(R.string.url_productcollect, context, requestDataMap, new SuccessParser());
			getDataFromServer(reqVo, new DataCallback<Boolean>() {

				@Override
				public void processData(Boolean paramObject, boolean paramBoolean) {
					if (paramObject!= null && paramObject) {
						Toast.makeText(ProductDetailActivity.this, "加入收藏夹成功", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(ProductDetailActivity.this, "加入收藏夹失败", Toast.LENGTH_LONG).show();
					}
				}
			});
			break;
			
		}
	}

	@Override
	protected void findViewById() {
		productGallery = (Gallery) findViewById(R.id.productGallery);
		textProductIdValue = (TextView) findViewById(R.id.textProductIdValue);
		textProductNameValue = (TextView) findViewById(R.id.textProductNameValue);
		textOriginalPriceValue = (TextView) findViewById(R.id.textOriginalPriceValue);
		textPriceValue = (TextView) findViewById(R.id.textPriceValue);
		textProductCommentNum = (TextView) findViewById(R.id.textProductCommentNum);
		prodNumValue = (EditText) findViewById(R.id.prodNumValue);
		textPutIntoShopcar = (TextView) findViewById(R.id.textPutIntoShopcar);
		orderTelTv = (TextView) findViewById(R.id.orderTelTv);
		findViewById(R.id.textProdToCollect).setOnClickListener(this);
	}

	@Override
	protected void loadViewLayout() {
		setContentView(R.layout.product_detail_activity);
		setTitle("商品详细");
	}

	@Override
	protected void onHeadRightButton(View v) {

	}

	@Override
	protected void processLogic() {
		int id  = getIntent().getIntExtra("id", -1);
		if (id == -1) {
			
			Logger.d(TAG, "id is null");
			finish();
			return ;
		}
		
		RequestVo requestVo = new RequestVo();
		requestVo.requestUrl = R.string.product;
		requestVo.context = context;
		HashMap<String, String> requestDataMap = new HashMap<String, String>();
		requestDataMap.put("id", id + "");
		requestVo.requestDataMap = requestDataMap ;
		requestVo.jsonParser = new ProductDetailParser();
		getDataFromServer(requestVo, new DataCallback<ProductDetail>() {
			@Override
			public void processData(ProductDetail paramObject, boolean paramBoolean) {
				if (paramObject != null) {
					currentProduct = paramObject;
					getECManager().addProductToHistory(
					new ProdcutHistory(paramObject.getId(), paramObject.getName(), paramObject.getPic().get(0), paramObject.getMarketprice(), paramObject.getPrice(), paramObject.getComment_count(),
							System.currentTimeMillis()));
					
					productGalleryAdapter adapter = new productGalleryAdapter(context, paramObject, productGallery);
					productGallery.setAdapter(adapter);

					textProductNameValue.setText(paramObject.getName());
					textProductIdValue.setText(paramObject.getId() + "");
					textOriginalPriceValue.setText(paramObject.getMarketprice() + "");
					textPriceValue.setText(paramObject.getPrice() + "");
					textProductCommentNum.setText(paramObject.getComment_count() + "");
				}
			}
		});

	}

	@Override
	protected void setListener() {
		productGallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
		textPutIntoShopcar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HashMap<String, String> requestDataMa = new HashMap<String, String>();
				requestDataMa.put("id", currentProduct.getId() + "");
				requestDataMa.put("count", prodNumValue.getText().toString());
				RequestVo reqVo = new RequestVo(R.string.url_cartdatasession, context, requestDataMa , new SuccessParser());
				getDataFromServer(reqVo , new DataCallback<Boolean>() {

					@Override
					public void processData(Boolean paramObject, boolean paramBoolean) {
 						if (paramObject != null && paramObject) {
 							AlertDialog.Builder builder = new Builder(ProductDetailActivity.this);
 							builder.setTitle("添加进购物车成功");
 							builder.setPositiveButton("继续购物", new DialogInterface.OnClickListener() {
 								@Override
 								public void onClick(DialogInterface dialog, int which) {
 									finish();
 								}
 							});
 							builder.setNegativeButton("进入购物车", new DialogInterface.OnClickListener() {
 								@Override
 								public void onClick(DialogInterface dialog, int which) {
 									Intent intent = new Intent(ProductDetailActivity.this, ShoppingCarActivity.class);
 									startActivity(intent);
 									finish();
 								}
 							});

 							builder.create().show();
 						} else {
 							Toast.makeText(ProductDetailActivity.this, "加入购物车失败", Toast.LENGTH_LONG).show();
 						}
					}
				});
				
				 
			}
		});

		orderTelTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:01088499999"));
				startActivity(intent);
			}
		});

	}

}
