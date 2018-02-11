package cn.lingcx.im.ui.activity;

import android.os.Handler;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import cn.lingcx.im.R;
import cn.lingcx.im.base.BaseActivity;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 11:45
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class SplashActivity extends BaseActivity {

	@BindView(R.id.imgv_splash)
	ImageView mImgvSplash;

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initViews() {
		getToolBar().hide();
		Glide.with(mContext)
				.load(R.drawable.bg_splash)
				.into(mImgvSplash);
	}

	@Override
	protected void initData() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//注册一个监听连接状态的listener
				EMClient.getInstance().addConnectionListener(new ConnectionListener());
				Logger.d("Splash");
			}
		}, 2000);
	}

	@Override
	protected boolean isNeedLoadStatusBar() {
		return false;
	}

	/**
	 * 实现ConnectionListener接口
	 */
	private class ConnectionListener implements EMConnectionListener {
		@Override
		public void onConnected() {
			ActivityUtils.startActivity(HomeActivity.class);
			Logger.d("已登录");
			finish();
		}

		@Override
		public void onDisconnected(int error) {
			if(error == EMError.USER_REMOVED){
				ToastUtils.showLong("帐号已经被移除");
			}else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
				ToastUtils.showLong("帐号在其他设备登录");
			} else {
				if (!NetworkUtils.isConnected()){
					ToastUtils.showShort("无法连接到聊天服务器");
				}
			}

			ActivityUtils.startActivity(LoginActivity.class);
			Logger.d("未登录");
			finish();
		}
	}
}
