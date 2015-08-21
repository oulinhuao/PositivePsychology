package com.kingdon.positivepsychology.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
	private Context mContext;
	private int mSize = 0;
	
	/**缓存页面集合*/
	private View[] mViews;
	/** 总页数 */
	private int mAllCount;
	/***
	 * 获取总页数
	 * @return
	 * @author Tony
	 */
	public int getPageCount(){
		return mAllCount;
	}
	
	private LayoutInflater mLayoutInflater;
	
	public MyPagerAdapter(Context c,
			View[] _mViews) {
		this.mContext = c;
		this.mViews = _mViews;
		this.mSize = _mViews != null ? _mViews.length : 0;
		this.mAllCount = mSize;
		
		this.mLayoutInflater = LayoutInflater.from(c);
		
	}
	
	/**
	 * 获取每页的view
	 * @param bi 数据源集合，每页有不多于三个元素
	 * @return
	 * @author Tony
	 * @date 2015年5月5日 上午10:45:49
	 */
	private View getViewPaged(final int index){
		return mViews[index];
	}
	
	@Override
	public int getCount() {
		return mAllCount;
	}

	@Override
	public Object instantiateItem(ViewGroup arg0, int position) {
		View v = getViewPaged(position);
		arg0.removeView(v);
		arg0.addView(v);
		return v;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
	}
}
