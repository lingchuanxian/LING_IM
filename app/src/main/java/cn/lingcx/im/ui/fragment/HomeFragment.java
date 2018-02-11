package cn.lingcx.im.ui.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import cn.lingcx.im.R;
import cn.lingcx.im.base.BaseFragment;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 16:14
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class HomeFragment extends BaseFragment {
	@Override
	protected int attachLayoutRes() {
		return R.layout.fragment_index;
	}

	@Override
	protected void initViews() {
		getToolBar().setCenterTitle("消息")
				.setLeftImage(R.drawable.ic_head)
		.setLeftImageOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtils.showShort("点击头像");
			}
		})
		.inflateMenu(R.menu.menu_message);
	}

	public static Fragment getInstance() {
		HomeFragment mFragment = new HomeFragment();
		return mFragment;
	}

	@Override
	protected void initData() {

	}


}
