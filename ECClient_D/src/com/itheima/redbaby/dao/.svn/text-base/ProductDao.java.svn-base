package com.itheima.redbaby.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itheima.redbaby.vo.ProdcutHistory;

/**
 * 商品浏览记录
 * 
 * @author liu
 * 
 */
public class ProductDao extends BaseDao {
	private static final String TABLE = "productHistory";

	public ProductDao(Context context) {
		super(context);
	}

	public List<ProdcutHistory> getAll() {
		return callBack(TYPE_READ, new DaoCallBack<List<ProdcutHistory>>() {

			@Override
			public List<ProdcutHistory> invoke(SQLiteDatabase conn) {
				cursor = conn.query(TABLE, null, null, null, null, null, " time desc");
				ProdcutHistory history;
				List<ProdcutHistory> historys = new ArrayList<ProdcutHistory>();
				while (cursor.moveToNext()) {
					history = new ProdcutHistory();
					fillProdcutHistory(cursor, history);
					historys.add(history);
				}
				return historys;
			}
		});
	}

	public void delete(final int id) {
		callBack(TYPE_WRITE, new DaoCallBack<Void>() {

			@Override
			public Void invoke(SQLiteDatabase conn) {
				conn.delete(TABLE, "id = ?", new String[] { Integer.toString(id) });
				return null;
			}
		});
	}

	public Boolean findById(final int id) {
		return callBack(TYPE_READ, new DaoCallBack<Boolean>() {

			@Override
			public Boolean invoke(SQLiteDatabase conn) {
				cursor = conn.query(TABLE, null, " id = ?", new String[] { Integer.toString(id) }, null, null, null);
				return cursor.moveToFirst();
			}
		});
	}

	public Long add(final ProdcutHistory history) {
		return callBack(TYPE_WRITE, new DaoCallBack<Long>() {

			@Override
			public Long invoke(SQLiteDatabase conn) {
				return conn.insert(TABLE, null, fillContentValues(history));
			}
		});
	}

	public Integer update(final ProdcutHistory history) {
		return callBack(TYPE_WRITE, new DaoCallBack<Integer>() {

			@Override
			public Integer invoke(SQLiteDatabase conn) {
				return conn.update(TABLE, fillContentValues(history), "id = ?", new String[] { history.getId() + "" });
			}
		});
	}

	public Integer deleteAll() {
		return callBack(TYPE_WRITE, new DaoCallBack<Integer>() {

			@Override
			public Integer invoke(SQLiteDatabase conn) {
				return conn.delete(TABLE, null, null);
			}
		});
	}

	private void fillProdcutHistory(Cursor cursor, ProdcutHistory history) {
		history.setId(cursor.getInt(cursor.getColumnIndex("id")));
		history.setName(cursor.getString(cursor.getColumnIndex("name")));
		history.setPic(cursor.getString(cursor.getColumnIndex("pic")));
		history.setMarketprice(cursor.getDouble(cursor.getColumnIndex("marketprice")));
		history.setPrice(cursor.getDouble(cursor.getColumnIndex("price")));
		history.setComment_count(cursor.getInt(cursor.getColumnIndex("comment_count")));
		history.setTime(cursor.getInt(cursor.getColumnIndex("time")));
	}

	private ContentValues fillContentValues(ProdcutHistory history) {
		ContentValues values = new ContentValues();
		values.put("id", history.getId());
		values.put("name", history.getName());
		values.put("pic", history.getPic());
		values.put("marketprice", history.getMarketprice());
		values.put("price", history.getPrice());
		values.put("comment_count", history.getComment_count());
		values.put("time", history.getTime());
		return values;
	}

}
