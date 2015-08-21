package com.kingdon.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

import com.kingdon.kdmsp.tool.CommonHelper;
import com.kingdon.kdmsp.tool.SdCardAndFileHelper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;

public class CommonUtils {

	public static UUID getDeviceUuId(Context context) {
		UUID uuid = null;
		final String androidId = Secure.getString(context.getContentResolver(),
				Secure.ANDROID_ID);
		try {
			if (!"9774d56d682e549c".equals(androidId)) {
				uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
			} else {
				final String deviceId = ((TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE))
						.getDeviceId();
				uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId
						.getBytes("utf8")) : UUID.randomUUID();
			}
		} catch (UnsupportedEncodingException e) {
			uuid = UUID.fromString("0000");
		}
		return uuid;
	}

	/**
	 * 获取版本名
	 * 
	 * @return 当前应用的版本号
	 */
	public static String getVersionName(Context context) {
		String version = "";
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			version = info.versionName;
		} catch (Exception e) {
			e.printStackTrace();
			version = "1.0";
		}
		return version;
	}

	public static int getVersionCode(Context context) {
		int versionCode = 1;
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			versionCode = info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	public static String getPackageName(Context context) {
		String s = "";
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			// 当前版本的包名
			s = info.packageName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			s = "";
		}
		return s;
	}

	public static String getAPKMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase(Locale.getDefault());
		if (end.equals("apk")) {
			type = "application/vnd.android.package-archive";
		} else {
			type += "/*";
		}
		return type;
	}

	/**
	 * 设置全屏
	 * 
	 * @param is
	 *            true 全屏 false 取消全屏
	 * @param activity
	 * @author Tony
	 * @date 2014年10月23日 下午4:33:40
	 */
	public static void setFullScreen(boolean is, Activity activity) {
		if (is) {
			WindowManager.LayoutParams attrs = activity.getWindow()
					.getAttributes();
			attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
			activity.getWindow().setAttributes(attrs);
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		} else {
			WindowManager.LayoutParams attrs = activity.getWindow()
					.getAttributes();
			attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
			activity.getWindow().setAttributes(attrs);
			activity.getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}
	
	/**
	 * 检查输入框是否为空
	 * @param editText
	 * @return
	 * @author Tony
	 * @date 2015年3月24日 下午2:52:07
	 */
	public static boolean checkIsNull(EditText editText){
		if(editText == null){
			return false;
		}
		return TextUtils.isEmpty(editText.getText());
	}
	
	/**
	 * 检查密码长度，须在6-20
	 * @param editText 
	 * @return true 符合规范
	 * @author Tony
	 * @date 2015年4月14日 下午3:31:04
	 */
	public static boolean checkPswdLen(EditText editText){
		if(editText == null){
			return false;
		}
		int len = editText.getText().toString().trim().length();
		return len >= 6 && len <= 20;
	}

	
	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

}
