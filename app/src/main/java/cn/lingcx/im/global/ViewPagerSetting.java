package cn.lingcx.im.global;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.lang.reflect.Field;

/**
 * @author ling_cx
 * @version 1.0
 * @Description 通用viewpager设置
 * @date 2017/9/4.
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class ViewPagerSetting {
	private static EdgeEffectCompat leftEdge;
	private static EdgeEffectCompat rightEdge;
	public static void commonSettingWithCommonTabLayout(final ViewPager mVpContent,final CommonTabLayout mCtlMenu){
		mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				//禁用viewpager拉到边界时的渐变颜色
				try {
					Field leftEdgeField = mVpContent.getClass().getDeclaredField("mLeftEdge");
					Field rightEdgeField = mVpContent.getClass().getDeclaredField("mRightEdge");
					if (leftEdgeField != null && rightEdgeField != null) {
						leftEdgeField.setAccessible(true);
						rightEdgeField.setAccessible(true);
						leftEdge = (EdgeEffectCompat) leftEdgeField.get(mVpContent);
						rightEdge = (EdgeEffectCompat) rightEdgeField.get(mVpContent);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (leftEdge != null && rightEdge != null) {
					leftEdge.finish();
					rightEdge.finish();
					leftEdge.setSize(0, 0);
					rightEdge.setSize(0, 0);
				}
			}

			@Override
			public void onPageSelected(int position) {
				mCtlMenu.setCurrentTab(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		mCtlMenu.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				mVpContent.setCurrentItem(position);
			}
			@Override
			public void onTabReselect(int position) {

			}
		});
	}

	public static void commonSettingWithSlidingTabLayout(final ViewPager mVpContent,final SlidingTabLayout mCtlMenu){
		mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				//禁用viewpager拉到边界时的渐变颜色
				try {
					Field leftEdgeField = mVpContent.getClass().getDeclaredField("mLeftEdge");
					Field rightEdgeField = mVpContent.getClass().getDeclaredField("mRightEdge");
					if (leftEdgeField != null && rightEdgeField != null) {
						leftEdgeField.setAccessible(true);
						rightEdgeField.setAccessible(true);
						leftEdge = (EdgeEffectCompat) leftEdgeField.get(mVpContent);
						rightEdge = (EdgeEffectCompat) rightEdgeField.get(mVpContent);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (leftEdge != null && rightEdge != null) {
					leftEdge.finish();
					rightEdge.finish();
					leftEdge.setSize(0, 0);
					rightEdge.setSize(0, 0);
				}
			}

			@Override
			public void onPageSelected(int position) {
				mCtlMenu.setCurrentTab(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		mCtlMenu.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelect(int position) {
				mVpContent.setCurrentItem(position);
			}
			@Override
			public void onTabReselect(int position) {

			}
		});
	}
}
