package com.stifler.basecommonmodule.demo.utils;

import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.stifler.basecommonmodule.demo.DemoApplication;

/**
 * ****************************************************************
 * 文件名称	: DisplayMetricsUtils.java
 * 作    者	: hudongsheng
 * 创建时间	: 2014-6-9 下午5:21:50
* 文件描述	: 屏幕显示工具类
 * 版权声明	: Copyright © 2014 江苏钱旺智能系统有限公司
 * 修改历史	: 2014-6-9 1.00 初始版本
 *****************************************************************
 */
public class DisplayMetricsUtils
{
	private static DisplayMetrics dm;
	private static String resolution;
	private static float height,width,density;


	public static void init(){
		dm = DemoApplication.getIntance().getResources().getDisplayMetrics();
		height = dm.heightPixels;
		width = dm.widthPixels;
		density = dm.density;
	}

	/**
	 * 获取屏幕高度
	 */
	public static float getHeight()
	{
		if(height == 0f){
			init();
		}
		return height;
	}

	/**
	 * 获取屏幕宽度
	 */
	public static float getWidth()
	{
		if(width == 0f){
			init();
		}
		return width;
	}

	/**
	 * 获取屏幕的密度
	 * @return
	 */
	public static float getDensity()
	{
		if(density == 0f){
			init();
		}
		return density;
	}

	/** 
	 * dp转换px
	 */
	public static float dp2px(float dpValue)
	{
		return dpValue * density + 0.5f;
	}

	/** 
	 * px转换 dp 
	 */
	public static float px2dp(float pxValue)
	{
		return pxValue / density + 0.5f;
	}

	public static int round( int paramInt) {
		return Math.round(paramInt / getDensity());
	}

	public static String getResolution(){
		if(TextUtils.isEmpty(resolution)){
			resolution = (int)getWidth()+"*"+(int)getHeight();
		}
		return resolution;
	}
}
