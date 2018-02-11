package cn.lingcx.im.ui.activity;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lingcx.im.R;
import cn.lingcx.im.base.BaseActivity;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 10:58
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class RegistActivity extends BaseActivity {

	@BindView(R.id.edt_regist_account)
	TextInputEditText mEdtRegistAccount;
	@BindView(R.id.edt_regist_pwd)
	TextInputEditText mEdtRegistPwd;
	@BindView(R.id.edt_regist_repwd)
	TextInputEditText mEdtRegistRepwd;

	@Override
	protected int attachLayoutRes() {
		return R.layout.activity_regist;
	}

	@Override
	protected void initViews() {
		getToolBar().setTitle("用户注册");
	}

	@OnClick({R.id.btn_regist})
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btn_regist:
				if(StringUtils.isEmpty(mEdtRegistAccount.getText().toString())){
					ToastUtils.showShort("请输入帐号");
					return;
				}
				if(StringUtils.isEmpty(mEdtRegistPwd.getText().toString())){
					ToastUtils.showShort("请输入密码");
					return;
				}
				if(StringUtils.isEmpty(mEdtRegistRepwd.getText().toString())){
					ToastUtils.showShort("请再次输入密码");
					return;
				}
				if(!(mEdtRegistPwd.getText().toString().equals(mEdtRegistRepwd.getText().toString()))){
					ToastUtils.showShort("密码输入不一致，请重新输入");
					return;
				}
				regist();
				break;
			default:
				break;
		}
	}

	/**
	 * 注册
	 */
	private void regist() {
		//注册失败会抛出
		try{
			EMClient.getInstance().createAccount(mEdtRegistAccount.getText().toString(), mEdtRegistPwd.getText().toString());
		}catch (HyphenateException exception){
			ToastUtils.showLong(exception.getMessage());
		}
	}

	@Override
	protected void initData() {

	}

}
