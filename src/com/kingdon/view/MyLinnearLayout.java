package com.kingdon.view;

import com.kingdon.positivepsychology.interfaces.ILayoutClickListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinnearLayout extends LinearLayout {
	/**单击间隔时间*/
	final int d = 800;
	
	private ILayoutClickListener mILayoutClickListener;
	/**
	 * 自定义一个点击事件
	 * @param _mILayoutClickListener
	 * @author Tony
	 */
	public void setOnLayoutClickListener(ILayoutClickListener _mILayoutClickListener){
		mILayoutClickListener = _mILayoutClickListener;
	}

	public MyLinnearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
    public MyLinnearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    
    public MyLinnearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
    	// TODO Auto-generated method stub
    	return true;
    }

    long downTime = 0L;
	boolean isMoved = false;
	boolean touch = false;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	switch(ev.getAction()){
		case MotionEvent.ACTION_DOWN:
			downTime = System.currentTimeMillis();
			isMoved = false;
			break;
		case MotionEvent.ACTION_MOVE:
			isMoved = true;
			break;
		case MotionEvent.ACTION_UP:
			if(!isMoved && System.currentTimeMillis() - downTime < d){
				if(ev.getX() < getWidth() / 3){
					if(mILayoutClickListener != null){
						mILayoutClickListener.onClicked(0);
					}
				}else if(ev.getX() > getWidth() / 3 * 2){
					if(mILayoutClickListener != null){
						mILayoutClickListener.onClicked(2);
					}
				}else{
					if(mILayoutClickListener != null){
						mILayoutClickListener.onClicked(1);
					}
				}
				return false;
			}
			break;
		}
    	return super.dispatchTouchEvent(ev);
    }
    
}
