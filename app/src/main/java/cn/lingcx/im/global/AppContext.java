package cn.lingcx.im.global;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.lingcx.im.greendao.GreenDaoManager;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-9 15:35
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class AppContext extends Application {
	private static AppContext instance;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		//日志工具类初始化
		Logger.addLogAdapter(new AndroidLogAdapter());
		//工具集合初始化
		Utils.init(this);
		//初始化greendao
		GreenDaoManager.getInstance();
		//环信初始化
		EMOptions options = new EMOptions();
		// 默认添加好友时，是不需要验证的，改成需要验证
		options.setAcceptInvitationAlways(false);
		// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
		options.setAutoTransferMessageAttachments(true);
		// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
		options.setAutoDownloadThumbnail(true);
		//初始化
		EMClient.getInstance().init(this, options);
		//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
		EMClient.getInstance().setDebugMode(true);
	}

	public static AppContext getInstance(){
		return instance;
	}

}
