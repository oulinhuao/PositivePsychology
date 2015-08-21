package com.kingdon.positivepsychology;

import com.kingdon.preferences.PreferencesCommon;

import android.content.Context;

/**
 * 全局配置
 * @author Tony
 */
public class GlobalConfig {
	
	private static int mScreenWidth = 0;
	private static int mScreenHeight = 0;

	/**
	 * 图片最大数
	 */
	public static final int MAX_SELECTED_PICTURE_NUM = 5;
	
	public static int getScreenWidth(Context context){
		if(mScreenWidth == 0){
			int[] info = PreferencesCommon.readScreenInfo(context);
			mScreenWidth = info[0];
			mScreenHeight = info[1];
		}
		return mScreenWidth;
	}
	
	
	public static int getScreenHeight(Context context){
		if(mScreenWidth == 0){
			int[] info = PreferencesCommon.readScreenInfo(context);
			mScreenWidth = info[0];
			mScreenHeight = info[1];
		}
		return mScreenHeight;
	}
	public static void setScreenInfo(int w,int h){
		mScreenWidth = w;
		mScreenHeight = h;
	}
	
	
}
