package com.kingdon.positivepsychology.fragment;

import butterknife.ButterKnife;

import com.kingdon.base.MyBaseFragment;
import com.kingdon.kdmsp.tool.LogHelper;
import com.kingdon.positivepsychology.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 提升，树洞
 * @author Tony
 */
public class PromoteHole extends MyBaseFragment { //implements OnScrollListener{
	
	
	public PromoteHole(){
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(mRootView == null){
			mRootView = inflater.inflate(R.layout.layout_promote_feeling,
					container, false);
		}else{
            ViewGroup parent = (ViewGroup) mRootView.getParent();  
		    if (parent != null) {  
		        parent.removeView(mRootView);  
		    }  
		}
		return mRootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		LogHelper.customLogging("onStart");
		if(mContext == null){
			mContext = this.getActivity();
			getViews();
			init();
			setListeners();
		}
	}

	protected void getViews() {
		ButterKnife.inject(this,mRootView);
		
	}
	
	private void init() {
		
	}
	
	protected void setListeners() {
		
	}

	
	
}