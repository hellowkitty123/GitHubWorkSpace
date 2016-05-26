package com.itheima.redbaby.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {
	public static final String DB_NAME = "IT_CAST";
	public static final int DB_VERSION = 1;
	private final SqlLiteHelper helper;
	private SQLiteDatabase db;

	/**
	 * 
	 * @author Mathew
	 * 
	 */
	private static class SqlLiteHelper extends SQLiteOpenHelper {
		SqlLiteHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE T_CUST(ID INTEGER PRIMARY KEY,NAME TEXT NOT NULL,PWD TEXT NOT NULL) ");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(" DROP TABLE IF EXISTS T_CUST");
		}
	}

	private DBHelper(Context context) {
		helper = new SqlLiteHelper(context);
		db = helper.getWritableDatabase();
	}

	public void insertCust() {
		ContentValues values = new ContentValues();
		values.put("ID", 1);
		values.put("NAME", "mathew");
		values.put("PWD", "666666");
		db.insert("T_CUST", null, values);
		db.execSQL("", new Object[] {});
	}

}
