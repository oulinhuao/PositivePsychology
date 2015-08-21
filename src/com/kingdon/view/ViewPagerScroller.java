package com.kingdon.view;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * ViewPage的滑动速度控制器
 * @author Tony
 */
public class ViewPagerScroller extends Scroller {
	public static final int SCROLL_SPEED_FAST = 500;
	public static final int SCROLL_SPEED_SLOW = 2000;
	
	private int mScrollDuration = SCROLL_SPEED_SLOW;// 滑动速度
	
	
    public ViewPagerScroller(Context context) {
        super(context);
    }
    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }
    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }
    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }
    
    /**
     * 设定ViewPage的滑动速度
     * @param d
     * @author Tony
     * @date 2014年6月23日 上午10:20:55
     */
    public void setScrollDuration(int d){
		this.mScrollDuration = d;
	}
}