package com.kingdon.positivepsychology;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

public class WebBrowerActivity extends BaseWebActivity {
	
	public static void open(Activity a,String url){
		Intent i = new Intent(a, WebBrowerActivity.class);
		Bundle b = new Bundle();
		b.putString("mUrl", url);
		i.putExtras(b);
		a.startActivity(i);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("mUrl", mUrl);
		super.onSaveInstanceState(outState);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey("mUrl")) {
				mUrl = savedInstanceState.getString("mUrl");
			}
		}
		setContentView(R.layout.layout_webview);
		super.onCreate(savedInstanceState);
	}
    
    @Override
    protected void init() {
    	super.init();
    	//http://192.186.1.22:8020/test/examples/accordion.html
//    	String urlRemote = "http://192.186.1.22:8020/test/examples/accordion.html";
    	
    	
    	Bundle b = getIntent().getExtras();
    	if(b != null){
    		if(b.containsKey("mUrl")){
    			mUrl = b.getString("mUrl");
    		}
    	}
    	if(!TextUtils.isEmpty(mUrl)){
    		loadPage(mUrl);
    	}
    }
    
}
