package com.chuanonly.jump;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Util
{
    public static final String SPF_SETTING = "setting";
    public static final String SOUND = "sound";
    private static final String LOGIN_TIME = "login";
    public  static final String TOKEN = "token_star";
    private static Toast toast;
    private final static String name1 = "com";
	private final static String name2 = "chuanonly";
	private final static String name3 = "rungame";
	private final static String namedot = ".";
    private static final String[] ALLOWED_SIG = { "c321f556919b7209e99775ed827347f1","21375b3bcf6135526c48830f37f1e594","f89d0b7eaddc23807f69f5544fb567b2"};
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
	public final static String LEVEL = "setting_level_";
    public static int getLevelSharedPref(int set, int level)
    {
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	return sp.getInt(LEVEL+set+"_"+level, 5);
    }
    public static void setLevelSharedPref(int set, int level, int value)
    {
    	if (set < 0 || level < 0|| value< 0) return;
    	int lastValue = getLevelSharedPref(set, level);
    	if (lastValue == 4 && value == 0) return;
    	if (lastValue >=1 && lastValue <=3 && value>= 4) return;
    	if (value < lastValue && lastValue < 4) return; 
    	
    	SharedPreferences sp = APP.getContext().getSharedPreferences(SPF_SETTING, Context.MODE_PRIVATE );
    	Editor editor =  sp.edit();
    	editor.putInt(LEVEL+set+"_"+level, value);
    	editor.commit();
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
	public static void checkPkg() {
		PackageManager pm = APP.getContext().getPackageManager();
		PackageInfo packageinfo;
		String packageName = null;
		try {
			packageinfo = pm.getPackageInfo(APP.getContext()
					.getPackageName(), PackageManager.GET_SIGNATURES);
			packageName = packageinfo.packageName;			  
			if(!packageName.equals(name1+namedot+name2+namedot+name3))
			{
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static boolean checkSignatures() {
        try {
        	PackageManager pm = APP.getContext().getPackageManager();
            PackageInfo pkgInfo = pm.getPackageInfo(APP.getContext()
					.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] sigs = pkgInfo.signatures;
            if (sigs != null && sigs.length > 0) {
            	boolean success = false;
                for (Signature sig : sigs) {
                    String codedSig = getMD5(sig.toByteArray());
                    for (String allowedSig : ALLOWED_SIG) {
                        if (allowedSig.equals(codedSig))
                        	success = true;
                    }
                }
                if (!success) System.exit(0);
            }
        } catch (Exception e) {
        }
        return false;
    }
    public static byte[] MD5(byte[] input)
    {
      MessageDigest md = null;
      try {
        md = MessageDigest.getInstance("MD5");
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      }
      if (md != null) {
        md.update(input);
        return md.digest();
      }
      return null;
    }

    public static String getMD5(byte[] input) {
        return bytesToHexString(MD5(input));
    }
    public static String bytesToHexString(byte[] bytes)
    {
        if (bytes == null)
          return null;
        String table = "0123456789abcdef";
        StringBuilder ret = new StringBuilder(2 * bytes.length);

        for (int i = 0; i < bytes.length; ++i)
        {
          int b = 0xF & bytes[i] >> 4;
          ret.append(table.charAt(b));
          b = 0xF & bytes[i];
          ret.append(table.charAt(b));
        }

        return ret.toString();
      }
}
