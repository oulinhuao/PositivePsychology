package com.kingdon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinnearLayout extends LinearLayout {

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

}
