package com.chuanonly.babyrun;

import android.app.Application;

public class MyApplication extends Application
{
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		System.gc();
        System.runFinalization();
	}
}
