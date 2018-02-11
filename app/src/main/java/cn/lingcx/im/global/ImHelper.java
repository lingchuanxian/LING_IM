package cn.lingcx.im.global;

/**
 * @author ling_cx
 * @version 1.0
 * @Description
 * @date 2018-2-11 16:17
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class ImHelper {
	private static ImHelper instance;

	public static ImHelper getInstance(){
		if(instance == null){
			instance = new ImHelper();
		}
		return instance;
	}
}
