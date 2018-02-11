package cn.lingcx.im.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;
import cn.lingcx.im.R;
import cn.lingcx.im.widget.ToolBarSet;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 8:54
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public abstract class BaseActivity extends AppCompatActivity {
	/**
	 * 上下文对象
	 */
	public Context mContext;
	/**
	 * Gson对象
	 */
	public Gson gson;
	/**
	 * 重写toolbar对象
	 */
	private ToolBarSet mToolBarSet;
	/**
	 * baselayout toolbar对象
	 */
	private Toolbar mToolbar;
	/**
	 * 加载进度条
	 */
	private MaterialDialog progress;
	/**
	 * toolbar居中标题
	 */
	private TextView mTvCenterTitle;
	/**
	 * 左侧图片
	 */
	private CircleImageView mCircleImageView;
	/**
	 *沉浸式状态栏管理器
	 */
	private SystemBarTintManager tintManager;
	/**
	 * 沉浸式状态栏颜色
	 */
	private final static int SystemBarColor = R.color.statusBarColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(attachLayoutRes());
		initStateBar();
		ButterKnife.bind(this);
		init();
	}

	/**
	 * 初始化沉浸式
	 */
	private void initStateBar() {
		if (isNeedLoadStatusBar()) {
			loadStateBar();
		}
	}

	private void loadStateBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		tintManager = new SystemBarTintManager(this);
		// 激活状态栏设置
		tintManager.setStatusBarTintEnabled(true);
		// 激活导航栏设置
		tintManager.setNavigationBarTintEnabled(true);
		// 设置一个状态栏颜色
		tintManager.setStatusBarTintResource(SystemBarColor);
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * 子类是否需要实现沉浸式,默认需要
	 *
	 * @return
	 */
	protected boolean isNeedLoadStatusBar() {
		return true;
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		View view = getLayoutInflater().inflate(R.layout.base_layout, null);
		mCircleImageView = view.findViewById(R.id.civ_head);
		mTvCenterTitle = view.findViewById(R.id.tv_center_title);
		super.setContentView(view);
		initDefaultView(layoutResID);
	}

	private void initDefaultView(int layoutResId) {
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		View childView = LayoutInflater.from(this).inflate(layoutResId, null);
		container.addView(childView, 0);
	}

	private void init() {
		mContext = this;
		gson = new GsonBuilder().serializeNulls().create();
		getProgress();
		initViews();
		initData();
	}

	public MaterialDialog getProgress(){
		return getProgress("加载中...");
	}

	public MaterialDialog getProgress(String message){
		progress = new MaterialDialog.Builder(mContext)
				.content(message)
				.progress(true, 0)
				.cancelable(false)
				.build();
		return progress;
	}

	/**
	 * 获取Toolbar对象
	 *
	 * @return
	 */
	public ToolBarSet getToolBar() {
		if (mToolBarSet == null) {
			mToolBarSet = new ToolBarSet(mToolbar,mCircleImageView, mTvCenterTitle, this);
		}
		return mToolBarSet;
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

}
