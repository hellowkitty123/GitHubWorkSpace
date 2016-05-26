package com.itheima.redbaby.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.AbsListView;

import com.itheima.redbaby.engine.SyncImageLoader;

public class ImageAsyncLoaderAdpter<T> extends ArrayWapperAdapter<T> {

	protected AbsListView mListView;

	private SyncImageLoader syncImageLoader;

	public ImageAsyncLoaderAdpter(Context context, AbsListView absListView) {
		super(context);
		init(absListView);
 	}

	public ImageAsyncLoaderAdpter(Context context, AbsListView absListView, T[] objects) {
		super(context, objects);
		init(absListView);
	}

	public ImageAsyncLoaderAdpter(Context context, AbsListView absListView, List<T> objects) {
		super(context, objects);
		init(absListView);
	}

	private void init(AbsListView absListView) {
		this.mListView = absListView;
		syncImageLoader = new SyncImageLoader();
		mListView.setOnScrollListener(onScrollListener);
	}

	public void loadImage(Integer t, String imageUrl) {
		syncImageLoader.loadImage(t, imageUrl, imageLoadListener);
	}

	public void onImageLoadFinish(Integer position, Drawable drawable) {

	}

	public void onImageLoadError(Integer position) {

	}

	SyncImageLoader.OnImageLoadListener imageLoadListener = new SyncImageLoader.OnImageLoadListener() {

		@Override
		public void onImageLoad(Integer position, Drawable drawable) {
			onImageLoadFinish(position, drawable);
		}

		@Override
		public void onError(Integer t) {
			onImageLoadError(t);
		}

	};

	public void loadImage() {
		int start = mListView.getFirstVisiblePosition();
		int end = mListView.getLastVisiblePosition();
		if (end >= getCount()) {
			end = getCount() - 1;
		}
		syncImageLoader.setLoadLimit(start, end);
		syncImageLoader.unlock();
	}

	AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
				// Logger.d(TAG, "SCROLL_STATE_FLING");
				syncImageLoader.lock();
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
				// Logger.d(TAG, "SCROLL_STATE_IDLE");
				loadImage();
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				syncImageLoader.lock();
				break;
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

		}
	};
}
