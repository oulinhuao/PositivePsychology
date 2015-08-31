package com.kingdon.positivepsychology;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.kingdon.base.MyBaseActivity;
import com.kingdon.base.MyBaseFragment;
import com.kingdon.positivepsychology.fragment.Promote3Event;
import com.kingdon.positivepsychology.fragment.PromoteFeeling;
import com.kingdon.positivepsychology.fragment.PromoteHole;
import com.kingdon.util.ImageHelper;
import com.kingdon.view.PagerSlidingTabStrip;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 提升
 * @author Tony
 */
public class PromoteActivity extends MyBaseActivity {

	@InjectView(R.id.relativeLayout1) RelativeLayout mLayoutTitle;
	@InjectView(R.id.tabs) PagerSlidingTabStrip tabs;
	@InjectView(R.id.pager) ViewPager pager;

	private MyPagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_promote);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void getViews() {
		ButterKnife.inject(this);
	}

	@Override
	protected void init() {
		setSystemBar(R.color.title);
		mLayoutTitle.getLayoutParams().height = (int)(
				GlobalConfig.getScreenWidth(mContext) / 
				ImageHelper.getResRatio(mContext, R.drawable.main_title));
		
		
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
				.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		// 分割线颜色
		tabs.setDividerColorResource(R.color.white);
		// 默认文字颜色
		tabs.setTextColorResource(R.color.promote_tab_text_default);
		// 选中文字颜色
		tabs.setSelectedTextColorResource(R.color.promote_tab_text_focused);
		// 选中tab背景颜色
		tabs.setSelectedBGColorResource(R.color.promote_tab_focused);
		// 选中下划线颜色
		tabs.setIndicatorColorResource(R.color.promote_tab_line_focused);
		
		tabs.setIndicatorHeight(2);
		tabs.setUnderlineHeight(tabs.getIndicatorHeight());// 设置成一样的
		tabs.setTextSize(getResources().getDimensionPixelSize(R.dimen.size_20));
		tabs.setViewPager(pager);
		tabs.setShouldExpand(true);
	}
	
	@Override
	protected void setListeners() {
		
	}
	
	
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { 
				"心情日历", "三件好事", 
				"树洞"};

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			MyBaseFragment mbf = null;
			switch(position){
			case 0:
				mbf = new PromoteFeeling();
				break;
			case 1:
				mbf = new Promote3Event();
				break;
			case 2:
				mbf = new PromoteHole();
				break;
				
			}
			return mbf;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			return super.instantiateItem(container, position);
		}

		
	}
}
