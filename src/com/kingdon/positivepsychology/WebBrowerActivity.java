package com.kingdon.positivepsychology;

import android.os.Bundle;

public class WebBrowerActivity extends BaseWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setContentView(R.layout.layout_webview);
        super.onCreate(savedInstanceState);
    }
    
    @Override
    protected void init() {
    	super.init();
    	//http://192.186.1.22:8020/test/examples/accordion.html
    	String urlRemote = "http://192.186.1.22:8020/test/examples/accordion.html";
    	String urlLocal = "file:///android_asset/hello-mui/examples/tab-webview-main.html";
    	
    	loadPage(urlLocal);
    }
    
}
