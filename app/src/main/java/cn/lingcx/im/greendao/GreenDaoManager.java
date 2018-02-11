package cn.lingcx.im.greendao;

import cn.lingcx.im.global.AppContext;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 14:26
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class GreenDaoManager {
	private DaoMaster mDaoMaster;
	private DaoSession mDaoSession;
	private static volatile GreenDaoManager mInstance = null;
	private GreenDaoManager(){
		if (mInstance == null) {
			DaoMaster.DevOpenHelper devOpenHelper = new
					DaoMaster.DevOpenHelper(AppContext.getInstance(), "im.db");
			mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
			mDaoSession = mDaoMaster.newSession();
		}
	}
	public static GreenDaoManager getInstance() {
		if (mInstance == null) {
			synchronized (GreenDaoManager.class) {
				if (mInstance == null) {
				mInstance = new GreenDaoManager();
			}
			}
		}
		return mInstance;
	}
	public DaoMaster getMaster() {
		return mDaoMaster;
	}
	public DaoSession getSession() {
		return mDaoSession;
	}
	public DaoSession getNewSession() {
		mDaoSession = mDaoMaster.newSession();
		return mDaoSession;
	}
}
