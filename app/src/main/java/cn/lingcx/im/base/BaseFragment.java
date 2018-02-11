package cn.lingcx.im.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import butterknife.ButterKnife;
import cn.lingcx.im.R;
import cn.lingcx.im.widget.ToolBarSet;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * @author ling_cx
 * @version 1.0
 * @Description 基类Fragment
 * @date 2017/5/4.
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public abstract class BaseFragment extends Fragment {
	protected final String TAG = this.getClass().getSimpleName();
	public Context mContext;
	public View view;
	private Toolbar mToolbar;
	private TextView mTvCenterTitle;
	private ToolBarSet mToolBarSet;
	public MaterialDialog progress;

	public Gson gson = new Gson();

	/**
	 * 左侧图片
	 */
	private CircleImageView mCircleImageView;

	protected boolean isVisible;
	private boolean isPrepared;
	private boolean isFirst = true;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.base_layout, container, false);
		mCircleImageView = view.findViewById(R.id.civ_head);
		mTvCenterTitle = view.findViewById(R.id.tv_center_title);
		initDefaultView(attachLayoutRes(), view);
		init();
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		isPrepared = true;
		lazyLoad();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void initDefaultView(int layoutResId, View view) {
		FrameLayout container = view.findViewById(R.id.container);
		mToolbar = view.findViewById(R.id.toolbar);
		View childView = LayoutInflater.from(getActivity()).inflate(layoutResId, null);
		container.addView(childView, 0);
		ButterKnife.bind(this, container);
	}

	private void init() {
		mContext = getActivity();
		/*加载条*/
		progress = new MaterialDialog.Builder(mContext)
				.content(getResources().getString(R.string.loading))
				.progress(true, 0)
				.cancelable(false)
				.build();
		initViews();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			isVisible = true;
			lazyLoad();
		} else {
			isVisible = false;
		}
	}

	protected void lazyLoad() {
		if (!isPrepared || !isVisible || !isFirst) {
			return;
		}
		initData();
		isFirst = false;
	}

	/**
	 * 绑定布局文件
	 *
	 * @return 布局文件ID
	 */
	@LayoutRes
	protected abstract int attachLayoutRes();

	/**
	 * 初始化视图控件
	 */
	protected abstract void initViews();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 获取Toolbar对象
	 *
	 * @return
	 */
	public ToolBarSet getToolBar() {
		if (mToolBarSet == null) {
			mToolBarSet = new ToolBarSet(mToolbar,mCircleImageView,mTvCenterTitle, (AppCompatActivity) getActivity());
		}
		return mToolBarSet;
	}
}
