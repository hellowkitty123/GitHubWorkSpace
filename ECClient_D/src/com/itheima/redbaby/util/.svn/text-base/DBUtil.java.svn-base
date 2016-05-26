package com.itheima.redbaby.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库释放资源
 * 
 * @author liu
 * 
 */
public class DBUtil {
	public static void Release(Cursor cursor) {
		Release(null, cursor);
	}

	public static void Release(SQLiteDatabase conn) {
		Release(conn, null);
	}

	public static void Release(SQLiteDatabase conn, Cursor cursor) {
		if (cursor != null) {
			try {
				cursor.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			cursor = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
