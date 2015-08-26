package com.kingdon.positivepsychology;

import com.kingdon.kdmsp.tool.LogHelper;
import com.kingdon.util.BuildVersionUtil;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

/**
 * 浏览网页界面超类
 * @author Tony
 */
public class BaseWebActivity extends MyBaseActivity {
	protected WebView mWebView;
	protected WebSettings mWebViewSet = null;
	protected RelativeLayout mLayoutWebView;
	protected boolean mIsLoadError = false;
	protected boolean mIsTransparent = false;
	protected boolean mIsBlockNetworkImage = false;
	/**是否清楚Ram缓存*/
	protected boolean mIsClearCache = false;
	/**
	 * 设置背景是否透明
	 * @param is
	 * @author Tony
	 * @date 2015年1月20日 上午11:19:00
	 */
	protected void setTransparent(boolean is){
		this.mIsTransparent = is;
	}
	
	/**
	 * 是否控制网络图片加载
	 * @param is
	 * @author Tony
	 * @date 2015年1月20日 下午5:28:07
	 */
	protected void setBlockNetworkImage(boolean is){
		mIsBlockNetworkImage = is;
	}
	
	/**
	 * 是否清除缓存
	 * (主要是，全景图那里老是会放大，做个清除缓存的处理貌似好了,起码放大的几率减小了很多)
	 * @param is
	 * @author Tony
	 * @date 2015年2月28日 下午3:00:50
	 */
	protected void setIsClearCache(boolean is){
		mIsClearCache = is;
	}
	
	
	/** 用来记录连接，方便刷新 */
	protected String mUrl = "";
	

	@Override
	/**
	 * 子类要调用这个方法
	 */
	protected void getViews() {
		mLayoutWebView = (RelativeLayout)this.findViewById(R.id.view_layout);
		mWebView = (WebView)this.findViewById(R.id.view_webview);
	}
	
	/**
	 * 加载页面(子类按需要重写（例如:通知详情）)
	 * @param url 页面地址
	 */
	@SuppressLint("NewApi")
	protected void loadPage(String url){
		mIsLoadError = false;
		if(mWebViewSet == null){
			if(mIsTransparent){
				mWebView.setBackgroundColor(0);
				if(BuildVersionUtil.isHoneycombOrLater()){
		    		mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		    	}
			}
//			mWebView.setInitialScale(25);//为25%，最小缩放等级 
			mWebViewSet = mWebView.getSettings();
			mWebViewSet.setJavaScriptEnabled(true); 
			mWebViewSet.setUseWideViewPort(true); 
			mWebViewSet.setLoadWithOverviewMode(true); 
//			if(BuildVersionUtil.isHoneycombOrLater()){
//				mWebViewSet.setBuiltInZoomControls(true); // 设置显示缩放按钮
//			}
			mWebViewSet.setBuiltInZoomControls(false); // 设置显示缩放按钮
			mWebViewSet.setSupportZoom(false); //支持缩放
//			if(BuildVersionUtil.isHoneycombOrLater()){
//				mWebViewSet.setLayoutAlgorithm(LayoutAlgorithm.TEXT_AUTOSIZING);
//			}
			
//			
			int   screenDensity   = getResources().getDisplayMetrics(). densityDpi ;
			WebSettings.ZoomDensity   zoomDensity   = WebSettings.ZoomDensity. MEDIUM ;
			switch (screenDensity){
			case   DisplayMetrics.DENSITY_LOW :
			    zoomDensity = WebSettings.ZoomDensity.CLOSE;
			      break ;
			case   DisplayMetrics.DENSITY_MEDIUM :
			    zoomDensity = WebSettings.ZoomDensity.MEDIUM;
			      break ;
			case   DisplayMetrics.DENSITY_HIGH :
			case   DisplayMetrics.DENSITY_XHIGH :
			    zoomDensity = WebSettings.ZoomDensity.FAR;
			      break ;
			}
			mWebViewSet.setDefaultZoom(zoomDensity) ;
			
		     
			mWebView.setWebViewClient(new WebViewClient() {//不另外打开浏览器
				@Override
				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					super.onPageStarted(view, url, favicon);
					if(mIsBlockNetworkImage){
						mWebViewSet.setBlockNetworkImage(true);
					}
				}
				@Override
				public void onPageFinished(WebView view, String url) {
					super.onPageFinished(view, url);
					loadFinish(view, url);
				}
				@Override 
	            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) { 
					LogHelper.errorLogging("errorCode = " + errorCode);
					loadError(view,errorCode,description,failingUrl);
	            } 
			});
			
			mWebView.setWebChromeClient(new WebChromeClient(){
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					super.onProgressChanged(view, newProgress);
				}
			});
		}
		if(mIsClearCache){
			mWebView.clearCache(false);
//			mWebView.clearCache(true);// 只清除RAM cache 也不是很理想，这里清除所有
		}
		if(!TextUtils.isEmpty(url)){
			if(!url.startsWith("http") && !url.startsWith("file")){
				url = "http://"+url;
			}
			mWebView.loadUrl((mUrl = url));
			LogHelper.customLogging("mUrl = " + mUrl);
		}
		// 测试
//		mWebView.loadUrl("http://kingdonsoft.com/KDGUA/Mobile4KDGUA/ProjectInfo/ProjectDetail.aspx?ProjectId=95");
//		mWebView.loadUrl("http://www.kingdonsoft.com/KDGUA/Module/Stat/ProjectCateChart.aspx");
//		mWebView.loadUrl("www.hcharts.cn/demo/index.php?p=14");
	}
	
	
	
	protected void loadError(WebView view, int errorCode, String description, String failingUrl){
		if(errorCode==WebViewClient.ERROR_HOST_LOOKUP){
			// TODO 找不到页面
		}else if(errorCode==WebViewClient.ERROR_UNSUPPORTED_SCHEME){
			// TODO 不支持协议
		}
		mIsLoadError = true;
		view.setVisibility(View.INVISIBLE);
	}
	
	protected void loadFinish(WebView view, String url){
		if(mIsBlockNetworkImage){
			mWebViewSet.setBlockNetworkImage(false);
		}
		if(mIsLoadError){
			// 如果是加载失败了，显示加载失败的界面，隐藏掉WebView
			if(view.getVisibility() == View.VISIBLE){
				view.setVisibility(View.INVISIBLE);
			}
		}else{
			// 如果是加载成功了，显示WebView，隐藏掉加载失败的界面
			if(view.getVisibility() != View.VISIBLE){
				view.setVisibility(View.VISIBLE);
			}	

		}
	}
	
	@Override
	protected void onDestroy() {
		if(mWebView != null){
			mLayoutWebView.removeAllViews();
			try {
				mWebView.setVisibility(View.GONE);
				mWebView.stopLoading();
				mWebView.removeAllViews();
				mWebView.destroy();
				mWebView = null;
			} catch (Exception e) {
				LogHelper.customLogging("销毁mWebView出错");
			}
		}
		super.onDestroy();
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListeners() {
		// TODO Auto-generated method stub
		
	}

}
