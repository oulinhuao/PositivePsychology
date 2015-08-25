package com.kingdon.positivepsychology.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyPagerAdapter extends PagerAdapter {
	private Context mContext;
	
	/**缓存页面集合*/
	private int[] mViews;
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
	
	public MyPagerAdapter(Context c,
			int[] _mViews) {
		this.mContext = c;
		this.mViews = _mViews;
		this.mAllCount = _mViews == null ? 0 : _mViews.length;
	}
	
	/**
	 * 获取每页的view
	 * @param bi 数据源集合，每页有不多于三个元素
	 * @return
	 * @author Tony
	 * @date 2015年5月5日 上午10:45:49
	 */
	private View getViewPaged(int index){
		ImageView img = new ImageView(mContext);
		img = new ImageView(mContext);
		img.setImageResource(mViews[index]);
		img.setScaleType(ScaleType.FIT_XY);
		return img;
	}
	
	@Override
	public int getCount() {
		return mAllCount;
	}

	@Override
	public Object instantiateItem(ViewGroup arg0, int position) {
		View v = getViewPaged(position);
		arg0.addView(v);
		return v;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
		arg0.removeView((View)arg2);
	}
}
