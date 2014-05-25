package com.chuanonly.babyrun;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Util
{
    public static final String SPF_SETTING = "setting";
    public static final String SOUND = "sound";
    private static final String LOGIN_TIME = "login";
    private static Toast toast;
    public static void showToast(String str) {
       showToast(str, 0);
    }
    public static void showToast(String str, int n) {
      int time = Math.min(1, Math.max(n, 0));
      try {
          if (toast == null) {
              toast = Toast.makeText(APP.getContext(), str, Toast.LENGTH_SHORT);
          }
          toast.setText(str);
          toast.setDuration(n);
          toast.show();
      } catch (Exception e) {
          // TODO: handle exception
      }
    }
    
    public static void release() {
        toast = null;
    }
    
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            }
            NetworkInfo[] networkInfos = connectivity.getAllNetworkInfo();
            if (networkInfos == null) {
                return false;
            }
            for (NetworkInfo networkInfo : networkInfos) {
                if (networkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            return false;
        }
    }
    
    public static void setSoundSettingON(boolean isSoundON)
    {
    	boolean soundBool = isSoundSettingOn();
    	if (soundBool == isSoundON ) return;
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putBoolean(SOUND, isSoundON);
    	editor.commit();
    }
    public static boolean isSoundSettingOn()
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	return sp.getBoolean(SOUND, true);
    }
    
    public static void setStringToSharedPref(String key, String value)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putString(key, value);
    	editor.commit();
    }
    
    
    public static void setIntToSharedPref(String key, int value)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putInt(key, value);
    	editor.commit();
    }
    public static void setLongToSharedPref(String key, long value)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putLong(key, value);
    	editor.commit();
    }
    
    public static String getStringFromSharedPref(String key, String defValue)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	return sp.getString(key, defValue);
    }
    public static int getIntFromSharedPref(String key, int defValue)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	return sp.getInt(key, defValue);
    }
	public static int getLoginCnt() {
		SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	int cnt = sp.getInt(LOGIN_TIME, 0);
    	setLoginCnt(cnt + 1);
		return cnt;
	}
	public static void setLoginCnt(int cnt) 
	{
	   	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putInt(LOGIN_TIME, cnt);
    	editor.commit();
	}
    
}
