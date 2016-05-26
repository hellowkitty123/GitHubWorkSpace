package com.itheima.redbaby.util;

import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil {
	public static void Release(InputStream in) {
		Release(in, null);
	}

	public static void Release(OutputStream out) {
		Release(null, out);
 	}

	public static void Release(InputStream in, OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			out = null;
		}
		if (in != null) {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			in = null;
		}
	}
}
