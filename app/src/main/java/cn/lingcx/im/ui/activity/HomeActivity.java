package cn.lingcx.im.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lingcx.im.R;
import cn.lingcx.im.base.BaseActivity;
import cn.lingcx.im.bean.TabBean;
import cn.lingcx.im.global.ViewPagerSetting;
import cn.lingcx.im.ui.adapter.PagerAdapter;
import cn.lingcx.im.ui.fragment.HomeFragment;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 11:34
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class HomeActivity extends BaseActivity {

	@BindView(R.id.vpContent)
	ViewPager mVpContent;
	@BindView(R.id.ctlMenu)
	CommonTabLayout mCtlMenu;
	/**
	 * fragment集合
	 */
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	/**
	 * 底部菜单文字数组
	 */
	private String[] mTitles = {"消息", "联系人", "我的"};
	/**
	 * 底部菜单未选中图标
	 */
	private int[] mIconUnselectIds = {
			R.drawable.tab_message_unselected,R.drawable.tab_contract_unselected,R.drawable.tab_user_unselected};
	/**
	 * 底部菜单选中图标
	 */
	private int[] mIconSelectIds = {
			R.drawable.tab_message_selected,R.drawable.tab_contract_selected,R.drawable.tab_user_selected};
	/**
	 * 底部菜单实体集合
	 */
	private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_home;
	}

	@Override
	protected void initViews() {
		getToolBar().hide();
		mFragments.add(HomeFragment.getInstance());
		mFragments.add(HomeFragment.getInstance());
		mFragments.add(HomeFragment.getInstance());

		for (int i = 0; i < mTitles.length; i++) {
			mTabEntities.add(new TabBean(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
		}
		mVpContent.setAdapter(new PagerAdapter(getSupportFragmentManager(),mFragments,mTitles));
		mVpContent.setOffscreenPageLimit(2);
		mCtlMenu.setTabData(mTabEntities);
		ViewPagerSetting.commonSettingWithCommonTabLayout(mVpContent,mCtlMenu);

		//tab选中事件
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

	@Override
	protected void initData() {

	}

	private void logout() {
		EMClient.getInstance().logout(true, new EMCallBack() {

			@Override
			public void onSuccess() {
				Logger.d("注销成功");
				ActivityUtils.startActivity(LoginActivity.class);
				finish();
			}

			@Override
			public void onProgress(int progress, String status) {

			}

			@Override
			public void onError(int code, String message) {

			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
