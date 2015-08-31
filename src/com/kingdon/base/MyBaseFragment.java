package com.kingdon.base;

import butterknife.ButterKnife;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;


public class MyBaseFragment extends Fragment {

	protected Context mContext;
	protected View mRootView;
	protected boolean mIsFinish = false;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIsFinish = false;
	}
	
	@Override
	public void onResume() {
		mIsFinish = false;
		super.onResume();
	}
	
	@Override
	public void onDestroy() {
		mIsFinish = true;
		ButterKnife.reset(this);
		super.onDestroy();
	}
	
	@Override
	public void onStop() {
		mIsFinish = true;
		super.onStop();
	}
	
	/**
	 * 子线程要使用这个方法，避免界面结束了线程才跑完，这个时候再去刷新界面可能会有问题
	 *     <P>要注意，这里只能做UI操作，不要做业务、数据处理
	 * @param msg
	 * @author Tony
	 * @date 2015年1月15日 下午1:41:00
	 */
	protected void executeByMessage(Message msg){
		if(mIsFinish){
			return;
		}
	}
	
}
