package cn.lingcx.im.ui.activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lingcx.im.R;
import cn.lingcx.im.base.BaseActivity;
import cn.lingcx.im.greendao.GreenDaoManager;
import cn.lingcx.im.greendao.LoginUser;
import cn.lingcx.im.greendao.LoginUserDao;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 9:19
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class LoginActivity extends BaseActivity {
	@BindView(R.id.edt_login_account)
	TextInputEditText mEdtLoginAccount;
	@BindView(R.id.edt_login_pwd)
	TextInputEditText mEdtLoginPwd;
	/**
	 * 登录用户信息的dao
	 */
	private LoginUserDao mLoginUSerDao;

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_login;
	}

	@Override
	protected void initViews() {
		getToolBar().hide();
	}

	@Override
	protected void initData() {
		//实例化dao
		mLoginUSerDao =  GreenDaoManager.getInstance().getNewSession().getLoginUserDao();
		//获取最后一次登录的用户信息并设置显示
		LoginUser recentUser = mLoginUSerDao.loadAll().get(0);
		if(recentUser != null){
			mEdtLoginAccount.setText(recentUser.getAccount());
			mEdtLoginPwd.setText(recentUser.getPassword());
		}
	}

	@OnClick({R.id.tv_unknow_pwd, R.id.tv_regist,R.id.btn_login})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tv_unknow_pwd:
				ToastUtils.showShort("忘记密码");
				break;
			case R.id.tv_regist:
				ActivityUtils.startActivity(RegistActivity.class);
				break;
			case R.id.btn_login:
				if(StringUtils.isEmpty(mEdtLoginAccount.getText().toString())){
					ToastUtils.showShort("请输入帐号");
					return;
				}
				if(StringUtils.isEmpty(mEdtLoginPwd.getText().toString())){
					ToastUtils.showShort("请输入密码");
					return;
				}
				login();
				break;
			default:
				break;
		}
	}

	/**
	 * 登录操作
	 */
	private void login() {
		final MaterialDialog progress = getProgress("正在登录中...");
		progress.show();
		EMClient.getInstance().login(mEdtLoginAccount.getText().toString(),mEdtLoginPwd.getText().toString(),new EMCallBack() {
			@Override
			public void onSuccess() {
				EMClient.getInstance().groupManager().loadAllGroups();
				EMClient.getInstance().chatManager().loadAllConversations();
				Logger.d("登录聊天服务器成功！");
				//保存用户信息到数据库中
				saveUserInfo(new LoginUser(null,mEdtLoginAccount.getText().toString(),mEdtLoginPwd.getText().toString()));
				ActivityUtils.startActivity(HomeActivity.class);
				progress.dismiss();
				finish();
			}

			@Override
			public void onProgress(int progressNum, String status) {
				progress.show();
			}

			@Override
			public void onError(int code, String message) {
				progress.dismiss();
				ToastUtils.showLong(message);
				Logger.d("登录聊天服务器失败！");
			}
		});
	}

	/**
	 * 存储用户登录信息
	 */
	private void saveUserInfo(LoginUser mLoginUser) {
		List<LoginUser> list = mLoginUSerDao.queryRaw("where account = ?",mLoginUser.getAccount());
		//判断是否存在帐号信息
		if(list.size() > 0){
			Logger.d("已存在帐号");
			mLoginUser.setId(list.get(0).getId());
			mLoginUSerDao.update(mLoginUser);
		}else{
			Logger.d("不存在帐号");
			mLoginUSerDao.insert(mLoginUser);
		}
	}

	@Override
	protected boolean isNeedLoadStatusBar() {
		return false;
	}

}
