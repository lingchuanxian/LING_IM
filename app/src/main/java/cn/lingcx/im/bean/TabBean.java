package cn.lingcx.im.bean;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author ling_cx
 * @version 1.0
 * @Description 首页底部菜单属性
 * @date 2017/6/29.
 * @Copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class TabBean implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabBean(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
