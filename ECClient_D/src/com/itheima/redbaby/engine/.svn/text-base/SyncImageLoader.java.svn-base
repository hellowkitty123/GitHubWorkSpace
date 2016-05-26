package com.itheima.redbaby.engine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;

import com.itheima.redbaby.application.ECApplication;
import com.itheima.redbaby.util.Logger;
import com.itheima.redbaby.util.MD5;
import com.itheima.redbaby.util.StreamUtil;
import com.itheima.redbaby.util.ThreadPoolManager;

public class SyncImageLoader {
	private static final String TAG = null;

	private Object lock = new Object();

	private boolean mAllowLoad = true;

	private boolean firstLoad = true;

	private int mStartLoadLimit = 0;

	private int mStopLoadLimit = 0;

	private static final int BUFFER_SIZE = 8129;

	final Handler handler = new Handler();
	
	private ThreadPoolManager threadPool = ThreadPoolManager.getInstance();

	private Map<String, SoftReference<Drawable>> imageCache = new ConcurrentHashMap<String, SoftReference<Drawable>>();

	public interface OnImageLoadListener {
		public void onImageLoad(Integer t, Drawable drawable);

		public void onError(Integer t);
	}

	public void setLoadLimit(int startLoadLimit, int stopLoadLimit) {
		if (startLoadLimit > stopLoadLimit) {
			return;
		}
		mStartLoadLimit = startLoadLimit;
		mStopLoadLimit = stopLoadLimit;
	}

	public void restore() {
		mAllowLoad = true;
		firstLoad = true;
	}

	public void lock() {
		mAllowLoad = false;
		firstLoad = false;
	}

	public void unlock() {
		mAllowLoad = true;
		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public void loadImage(Integer position, String imageUrl, OnImageLoadListener listener) {
		final OnImageLoadListener mListener = listener;
		final String mImageUrl = imageUrl;
		final Integer mt = position;
		threadPool.addTask(new Runnable() {

			@Override
			public void run() {
				if (!mAllowLoad) {
					// Logger.d(TAG, "prepare to load");
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							Logger.e(TAG, e);
						}
					}
				}
				if (mAllowLoad && firstLoad) {
					loadImage(mImageUrl, mt, mListener);
				} else if (mAllowLoad && mt <= mStopLoadLimit && mt >= mStartLoadLimit) {
					loadImage(mImageUrl, mt, mListener);
				}
			}

		});
	}

	private void loadImage(final String mImageUrl, final Integer mt, final OnImageLoadListener mListener) {

		if (imageCache.containsKey(mImageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(mImageUrl);
			final Drawable d = softReference.get();
			if (d != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (mAllowLoad) {
							mListener.onImageLoad(mt, d);
						}
					}
				});
				return;
			}
		}
		try {
			final Drawable d = loadImageFromUrl(mImageUrl);
			if (d != null) {
				imageCache.put(mImageUrl, new SoftReference<Drawable>(d));
			}
			handler.post(new Runnable() {
				@Override
				public void run() {
					if (mAllowLoad) {
						mListener.onImageLoad(mt, d);
					}
				}
			});
		} catch (IOException e) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					mListener.onError(mt);
				}
			});
			Logger.e(TAG, e);
		}
	}

	public static Drawable loadImageFromUrl(String url) throws IOException {
		InputStream in = null;
		OutputStream out = null;
		try {
			File f = new File(ECApplication.getCacheDirPath(), MD5.digest(url));
			if (f.exists()) {
				Logger.d(TAG, "缓存　" + f.getAbsolutePath());
				return Drawable.createFromPath(f.getAbsolutePath());
			}
			Logger.d(TAG, "网络　" + url);
			URL m = new URL(url);
			in = new BufferedInputStream((InputStream) m.getContent(), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(f), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
		} finally {
			StreamUtil.Release(in, out);
		}
		return loadImageFromUrl(url);
	}
}
