package cn.lingcx.im.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @author ling_cx
 * @version 1.0
 * @Description ViewPage适配器
 * @date 2017/8/4.
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class PagerAdapter extends FragmentPagerAdapter {
	private ArrayList<Fragment> mFragments;
	private String[] mTitles;
	public PagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments,String[] titles) {
		super(fm);
		mFragments = fragments;
		mTitles = titles;
	}
	@Override
	public int getCount() {
		return mFragments.size();
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles[position];
	}
	@Override
	public Fragment getItem(int position) {
		return mFragments.get(position);
	}
}
