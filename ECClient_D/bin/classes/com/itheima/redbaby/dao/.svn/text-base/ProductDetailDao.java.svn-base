package com.itheima.redbaby.dao;

import com.itheima.redbaby.dao.BaseDao.DaoCallBack;
import com.itheima.redbaby.vo.Area;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProductDetailDao extends BaseDao {

	public ProductDetailDao(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public String findProductCount(final String productId) {
		return callBack(TYPE_READ, new DaoCallBack<String>() {

			@Override
			public String invoke(SQLiteDatabase conn) {
				String count = null;
				cursor = conn.rawQuery("select count from person where id=?", new String[] { productId });

				if (cursor.moveToFirst()) {
					count = cursor.getString(0);
				}
				return count;
			}
		});
	}

	public void addProductCount(final String productId, final String count) {
		callBack(TYPE_WRITE, new DaoCallBack<Void>() {

			@Override
			public Void invoke(SQLiteDatabase conn) {
				if (conn.isOpen()) {
					conn.execSQL("insert into productNum (id,count) values (?,?)", new Object[] { productId, count });

				}
				return null;
			}	
		});
	}

	public void deleteProductCount(final String productId) {
		callBack(TYPE_WRITE, new DaoCallBack<Void>() {

			@Override
			public Void invoke(SQLiteDatabase conn) {
				if (conn.isOpen()) {
					conn.execSQL("delete from productNum where id=?", new Object[] { productId });
				}
				return null;
			}
		});
	}

}
