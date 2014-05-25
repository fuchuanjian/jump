package com.chuanonly.babyrun;

import android.app.Application;
import android.content.Context;

public class APP extends Application
{
	private static Context mContext;
	private static APP mApplication;
	@Override
	public void onCreate()
	{
		super.onCreate();
		mApplication = this;
		mContext = getApplicationContext();
	}
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		System.gc();
        System.runFinalization();
	}
	public static Context getContext()
	{
		return mContext;
	}

	public static APP getInstance()
	{
		return mApplication;
	}
}
