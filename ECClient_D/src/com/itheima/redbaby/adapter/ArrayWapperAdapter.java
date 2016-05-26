package com.itheima.redbaby.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ArrayWapperAdapter<T> extends BaseAdapter {

	private List<T> mObjects;
 
	private final Object mLock = new Object();

	private boolean mNotifyOnChange = true;

	private Context mContext;

	private LayoutInflater mInflater;

	public ArrayWapperAdapter(Context context) {
		init(context, new ArrayList<T>());
	}

	public ArrayWapperAdapter(Context context, T[] objects) {
		init(context, Arrays.asList(objects));
	}

	public ArrayWapperAdapter(Context context, List<T> objects) {
		init(context, objects);
	}

	private void init(Context context, List<T> objects) {
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mObjects = objects;
	}

	public void add(T object) {
		synchronized (mLock) {
			mObjects.add(object);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void addAll(Collection<? extends T> collection) {
		synchronized (mLock) {
			mObjects.addAll(collection);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void addAll(T... items) {
		synchronized (mLock) {
			Collections.addAll(mObjects, items);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void insert(T object, int index) {
		synchronized (mLock) {
			mObjects.add(index, object);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void remove(T object) {
		synchronized (mLock) {
			mObjects.remove(object);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void clear() {
		synchronized (mLock) {
			mObjects.clear();
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	public void sort(Comparator<? super T> comparator) {
		synchronized (mLock) {
			Collections.sort(mObjects, comparator);
		}
		if (mNotifyOnChange)
			notifyDataSetChanged();
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		mNotifyOnChange = true;
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
		mNotifyOnChange = notifyOnChange;
	}

	public Context getContext() {
		return mContext;
	}

	public int getCount() {
		return mObjects.size();
	}

	public T getItem(int position) {
		return mObjects.get(position);
	}

	public int getPosition(T item) {
		return mObjects.indexOf(item);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return new View(mContext);
	}

	public View inflate(int resource, ViewGroup root) {
		return mInflater.inflate(resource, root);
	}

	public LayoutInflater getInflater() {
		return mInflater;
	}
}
