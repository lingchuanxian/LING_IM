package cn.lingcx.im.widget;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 重写ToolBar相关设置
 * @author ling_cx
 * @date 2017/6/4.
 */

public class ToolBarSet {
	private Toolbar mToolbar;
	private AppCompatActivity mActivity;
	private ActionBar mActionBar;
	private TextView mTvCenterTitle;
	private CircleImageView mCircleImageView;

	public ToolBarSet(Toolbar toolbar,CircleImageView  circleImageView,TextView tvCenterTitle, AppCompatActivity activity) {
		if(toolbar==null){
			return;
		}
		mToolbar = toolbar;
		mTvCenterTitle = tvCenterTitle;
		mCircleImageView = circleImageView;
		mActivity = activity;
		activity.setSupportActionBar(mToolbar);
		mActionBar = activity.getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mActivity.finish();
			}
		});
	}

	public ToolBarSet setLeftImage(int resId){
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mCircleImageView.setImageResource(resId);
		mCircleImageView.setVisibility(View.VISIBLE);
		return this;
	}

	public ToolBarSet setLeftImageOnClickListener(View.OnClickListener listener){
		mCircleImageView.setOnClickListener(listener);
		return this;
	}

	public ToolBarSet setTitle(String title){
		mActionBar.setTitle(title);
		return this;
	}

	public ToolBarSet setSubtitle(String subTitle){
		mActionBar.setSubtitle(subTitle);
		return this;
	}

	public ToolBarSet setCenterTitle(String centerTitle){
		mActionBar.setTitle("");
		mTvCenterTitle.setText(centerTitle);
		mTvCenterTitle.setVisibility(View.VISIBLE);
		return this;
	}

	public ToolBarSet setTitle(int resId){
		mActionBar.setTitle(resId);
		return this;
	}

	public ToolBarSet setSubtitle(int resId){
		mActionBar.setSubtitle(resId);
		return this;
	}

	public ToolBarSet setNavigationOnClickListener(View.OnClickListener listener){
		mToolbar.setNavigationOnClickListener(listener);
		return this;
	}

	public ToolBarSet setNavigationIcon(Drawable drawable){
		mToolbar.setNavigationIcon(drawable);
		return this;
	}

	public ToolBarSet setNavigationIcon(int resId){
		mToolbar.setNavigationIcon(resId);
		return this;
	}

	public ToolBarSet setDisplayHomeAsUpEnabled(boolean show){
		mActionBar.setDisplayHomeAsUpEnabled(show);
		return this;
	}

	public ToolBarSet setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener){
		mToolbar.setOnMenuItemClickListener(listener);
		return this;
	}

	public ToolBarSet inflateMenu(int resId){
		mToolbar.inflateMenu(resId);
		return this;
	}

	public void hide(){
		mActionBar.hide();
	}

}
