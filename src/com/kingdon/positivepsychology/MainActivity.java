package com.kingdon.positivepsychology;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.kingdon.kdmsp.tool.LogHelper;
import com.kingdon.positivepsychology.adapter.MyPagerAdapter;
import com.kingdon.positivepsychology.interfaces.ILayoutClickListener;
import com.kingdon.preferences.PreferencesCommon;
import com.kingdon.util.ImageHelper;
import com.kingdon.view.MyLinnearLayout;
import com.kingdon.view.ViewPagerScroller;
import com.kingdon.view.indicator.CirclePageIndicator;
import com.kingdon.view.loopviewpager.LoopViewPager;
import com.kingdon.viewpagereffect.AccordionTransformer;
import com.kingdon.viewpagereffect.CubeTransformer;
import com.kingdon.viewpagereffect.DepthPageTransformer;
import com.kingdon.viewpagereffect.InRightDownTransformer;
import com.kingdon.viewpagereffect.InRightUpTransformer;
import com.kingdon.viewpagereffect.RotateTransformer;
import com.kingdon.viewpagereffect.ZoomOutPageTransformer;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends MyBaseActivity {
	
	@InjectView(R.id.relativeLayout1) RelativeLayout mLayoutTitle;
	
	// 评测
	@InjectView(R.id.layout_pingce) MyLinnearLayout mLayoutPingce;
	@InjectView(R.id.view_pingce) LoopViewPager mViewPingce;
	@InjectView(R.id.view_img_pingce) ImageView mViewImgPingce;
	
	// 学习
	@InjectView(R.id.layout_study) MyLinnearLayout mLayoutStudy;
	@InjectView(R.id.view_study) LoopViewPager mViewStudy;
	@InjectView(R.id.view_study_right) LoopViewPager mViewStudyRight;
	@InjectView(R.id.view_img_study) ImageView mViewImgStudy;
	
	// 提升
	@InjectView(R.id.layout_tisheng) MyLinnearLayout mLayoutTishen;
	@InjectView(R.id.view_tisheng) LoopViewPager mViewTishen;
	@InjectView(R.id.view_tisheng_right) LoopViewPager mViewTishenRight;
	@InjectView(R.id.view_img_tisheng) ImageView mViewImgTishen;
 	
	// 交流
	@InjectView(R.id.layout_comm) MyLinnearLayout mLayoutComm;
	@InjectView(R.id.view_comm) LoopViewPager mViewComm;
	@InjectView(R.id.view_comm_right) LoopViewPager mViewCommRight;
	@InjectView(R.id.view_img_comm) ImageView mViewImgComm;
	
	// 教练
	@InjectView(R.id.layout_jiao) MyLinnearLayout mLayoutJiao;
	@InjectView(R.id.view_jiao) LoopViewPager mViewJiao;
	@InjectView(R.id.view_jiao_right) LoopViewPager mViewJiaoRight;
	@InjectView(R.id.view_img_jiao) ImageView mViewImgJiao;
	
	@InjectView(R.id.view_img_pingce_i) CirclePageIndicator mIndicatorPingce;
	@InjectView(R.id.view_img_study_i) CirclePageIndicator mIndicatorStudy;
	@InjectView(R.id.view_img_tisheng_i) CirclePageIndicator mIndicatorTishen;
	@InjectView(R.id.view_img_comm_i) CirclePageIndicator mIndicatorComm;
	@InjectView(R.id.view_img_jiao_i) CirclePageIndicator mIndicatorJiao;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void getViews() {
		ButterKnife.inject(this);
	}

	@Override
	protected void init() {
		setSystemBar();
		if (GlobalConfig.getScreenWidth(mContext) == 0) {
			// 记录分辨率
			DisplayMetrics mDisplayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
			GlobalConfig.setScreenInfo(mDisplayMetrics.widthPixels,
					mDisplayMetrics.heightPixels);
			PreferencesCommon.saveScreenInfo(mContext, 
					mDisplayMetrics.widthPixels, mDisplayMetrics.heightPixels);
		}
		
//		resizeLayout(mLayoutPingce,0.9795F);//0.9795F
//		resizeLayout(mLayoutStudy,0.7407F);
//		resizeLayout(mLayoutTishen,0.8197F);
//		resizeLayout(mLayoutComm,1F);
//		resizeLayout(mLayoutJiao,0.8691F);
		
		mLayoutTitle.getLayoutParams().height = (int)(
				GlobalConfig.getScreenWidth(mContext) / 
				ImageHelper.getResRatio(mContext, R.drawable.main_title));
		
		resizeLayout(mLayoutPingce,R.drawable.main_pengce_txt);
		resizeLayout(mLayoutPingce,R.drawable.main_pengce_txt);
		resizeLayout(mLayoutStudy,R.drawable.main_study_text);
		resizeLayout(mLayoutTishen,R.drawable.main_tisheng_text);
		resizeLayout(mLayoutComm,R.drawable.main_comm_text);
		resizeLayout(mLayoutJiao,R.drawable.main_jiao_txt);
		
		
		initPingce();
		initStudy();
		initTishen();
		initComm();
		initJiao();
		
		setIndicator(mIndicatorPingce,mViewPingce);
		setIndicator(mIndicatorStudy,mViewStudy);
		setIndicator(mIndicatorTishen,mViewTishen);
		setIndicator(mIndicatorComm,mViewComm);
		setIndicator(mIndicatorJiao,mViewJiao);
		
		mViewPingce.setPageTransformer(true, new CubeTransformer());
		mViewStudy.setPageTransformer(true, new DepthPageTransformer());
		mViewStudyRight.setPageTransformer(true, new RotateTransformer());
		mViewTishen.setPageTransformer(true, new AccordionTransformer());
		mViewTishenRight.setPageTransformer(true, new InRightUpTransformer());
		mViewComm.setPageTransformer(true, new InRightDownTransformer());
		mViewCommRight.setPageTransformer(true, new ZoomOutPageTransformer());
		
		
	}
	
	private void initViewPagerScroll(ViewPager vp){
	    try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true); 
            ViewPagerScroller scroller = new ViewPagerScroller(vp.getContext());
            mScroller.set(vp,scroller);
            scroller.setScrollDuration(200);// 系统默认是250
        }catch(NoSuchFieldException e){
        }catch (IllegalArgumentException e){
        }catch (IllegalAccessException e){
        }
    }
	
	/**
	 * 初始化评测数据
	 * @author Tony
	 */
	private void initPingce(){
		initViewPagers(R.drawable.main_pingce_page1,
				mViewPingce,mViewPingce);
	}
	
	/**
	 * 初始化学习数据
	 * @author Tony
	 */
	private void initStudy(){
		initViewPagers(R.drawable.main_study_left,
				mLayoutStudy,mViewStudy);
		initViewPagers(R.drawable.main_study_right,
				mLayoutStudy,mViewStudyRight);
	}
	
	private void initTishen(){
		initViewPagers(R.drawable.main_tisheng_middle,
				mLayoutTishen,mViewTishen);
		initViewPagers(R.drawable.main_tisheng_right,
				mLayoutTishen,mViewTishenRight);
	}
	
	private void initComm(){
		initViewPagers(R.drawable.main_comm_left,
				mLayoutComm,mViewComm);
		initViewPagers(R.drawable.main_comm_right,
				mLayoutComm,mViewCommRight);
	}
	
	private void initJiao(){
		initViewPagers(R.drawable.main_jiao,
				mLayoutJiao,mViewJiao);
		initViewPagers(R.drawable.main_jiao_right,
				mLayoutJiao,mViewJiaoRight);
	}
	
	
	/**
	 * 
	 * @param imgRes 要显示的图片
	 * @param layout 每行父布局
	 * @param _LoopViewPager 
	 * @author Tony
	 */
	private void initViewPagers(int imgRes,View layout,ViewPager _LoopViewPager){
		_LoopViewPager.setAdapter(getMyPagerAdapter(imgRes));
		_LoopViewPager.setPageMargin(0);
		initViewPagerScroll(_LoopViewPager);
	}
	
	private void setSystemBar(){
		//  布局跟节点下需要加上android:fitsSystemWindows="true"
		// 这样整个界面才不会推到顶部
		SystemBarTintManager mTintManager = new SystemBarTintManager(this);
		mTintManager.setStatusBarTintEnabled(true);
		mTintManager.setNavigationBarTintEnabled(false);
		mTintManager.setStatusBarTintResourceWithPadding(R.color.title);
	}
	
	private MyPagerAdapter getMyPagerAdapter(int imgRes){
		int count = 6;
		ImageView[] imgs = new ImageView[count];
		for(int i = 0;i < count;i++){
			if(imgs[i] == null){
				imgs[i] = new ImageView(mContext);
				imgs[i].setImageResource(imgRes);
				imgs[i].setScaleType(ScaleType.FIT_XY);
				setResize(imgs[i]);
			}
		}
		return new MyPagerAdapter(mContext,imgs);
	}
	
	private void setResize(ImageView iv){
//		iv.setAdjustViewBounds(true);
//		iv.setMaxHeight(1000000);
//		iv.setMaxWidth(1000000);
	}
	
	/**
	 * 配置指示器
	 * @param mIndicator
	 * @param mViewPager
	 * @author Tony
	 */
	private void setIndicator(CirclePageIndicator mIndicator,ViewPager mViewPager){
		mIndicator.setViewPager(mViewPager);
		mIndicator.setSnap(false);
		final float density = mContext.getResources().getDisplayMetrics().density;
		mIndicator.setBackgroundColor(0x00000000);
		mIndicator.setRadius(4 * density);
		mIndicator.setFillColor(0xffffffff);
		mIndicator.setStrokeColor(0xffffffff);
		mIndicator.setStrokeWidth(0);
		mIndicator.setPageColor(0x55ffffff);
	}

	@Override
	protected void setListeners() {
		mLayoutPingce.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewPingce.onTouchEvent(event);
				return true;
			}
		});
		mLayoutStudy.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewStudyRight.onTouchEvent(event);
				mViewStudy.onTouchEvent(event);
				return true;
			}
		});
		mLayoutTishen.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewTishenRight.onTouchEvent(event);
				mViewTishen.onTouchEvent(event);
				return true;
			}
		});
		mLayoutComm.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewCommRight.onTouchEvent(event);
				mViewComm.onTouchEvent(event);
				return true;
			}
		});
		mLayoutJiao.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewJiaoRight.onTouchEvent(event);
				mViewJiao.onTouchEvent(event);
				return true;
			}
		});
		
		mLayoutPingce.setOnLayoutClickListener(new ILayoutClickListener() {
			
			@Override
			public void onClicked(int index) {
				if(index == 2){// 点击右侧
					goPingce();
				}else{// 点击左侧
					
				}
				
			}
		});
		
		
	}
	
//	resizeLayout(mLayoutPingce,0.9795F);//0.9795F
//	resizeLayout(mLayoutStudy,0.7407F);
//	resizeLayout(mLayoutTishen,0.8197F);
//	resizeLayout(mLayoutComm,1F);
//	resizeLayout(mLayoutJiao,0.8691F);
//	private void resizeLayout(LinearLayout layout,float res){
//		layout.getLayoutParams().height = (int)(
//				GlobalConfig.getScreenWidth(mContext) / 
//				res / 3);
//	}
	int i = 0;
	private void resizeLayout(LinearLayout layout,int res){
		i++;
		layout.getLayoutParams().height = (int)(
				GlobalConfig.getScreenWidth(mContext) / 
				ImageHelper.getResRatio(mContext, res) / 3);
	}
	
	
//	@OnTouch(R.id.view_img_pingce)
//	protected boolean touchPingce(){
//		LogHelper.customLogging("goPingce");
//		return false;
//		
//	}
	
	
	/**
	 * 打开评测模块（点击评测文字处）
	 * @author Tony
	 */
	protected void goPingce(){
		LogHelper.customLogging("goPingce");
		
	}
}
