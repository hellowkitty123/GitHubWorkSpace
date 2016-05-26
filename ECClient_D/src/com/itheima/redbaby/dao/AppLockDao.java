package com.itheima.redbaby.dao;

import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 *实例代码
 * 程序锁 dao
 * @author liu
 *
 */
public class AppLockDao extends BaseDao {

	public static String TABLE = "applock";
	public static String PACKENAME = "packename";

	public AppLockDao(Context context) {
		super(context);
	}
	
	/**
	 * 添加
	 * 
	 * @param black
	 */
	public long add(final String packagename) {

		if (packagename == null)
			throw new NullPointerException("packagename is null");
		return callBack(TYPE_WRITE, new DaoCallBack<Long>() {

			@Override
			public Long invoke(SQLiteDatabase conn) {
				ContentValues values = new ContentValues();
				values.put(PACKENAME, packagename);
				long insert = conn.insert(TABLE, null, values);
				return insert;
			}
		});
	}

	/**
	 * 查找
	 * 
	 * @param packagename
	 * @return
	 */
	public boolean find(final String packagename) {
		return callBack(TYPE_READ, new DaoCallBack<Boolean>() {

			@Override
			public Boolean invoke(SQLiteDatabase conn) {
				ContentValues values = new ContentValues();
				values.put(PACKENAME, packagename);
				Cursor c = conn.query(TABLE, null, PACKENAME + " = ?", new String[] { packagename }, null, null, null);
				if (c.moveToFirst()) {
					return true;
				} else
					return false;
			}
		});
	}

	/**
	 * 根据包名删除
	 * 
	 * @param packagename
	 */
	public void delete(final String packagename) {
		callBack(TYPE_WRITE, new DaoCallBack<Object>() {
			@Override
			public Object invoke(SQLiteDatabase conn) {
				ContentValues values = new ContentValues();
				values.put(PACKENAME, packagename);
				conn.delete(TABLE, PACKENAME + " = ?", new String[] { packagename });
				return null;
			}
		});
	}
	
	public Set<String> findAll() {
		return callBack(TYPE_READ, new DaoCallBack<Set<String>>() {

			@Override
			public Set<String> invoke(SQLiteDatabase conn) {
				Cursor c = conn.query(TABLE, null, null, null, null, null, null);
				Set<String> values = new HashSet<String>();
				while (c.moveToNext()) {
					values.add(c.getString(0));
				}
 				return values;
			}
		});
		
	}
}
