package com.lenny.framedemo.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;



import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

/**
 */
public abstract class OSUtil {
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final String IMEI = "imei";
    public static final String IMSI = "imsi";
    public static final String MACADDRESS = "mac_address";

    public static String getProcessName(Context context) {
        try {
            int pid = android.os.Process.myPid();
            ActivityManager mActivityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public static String getSDTotalMsg(Context context) {
        long totalSize = getSDTotalSize(context);
        return Formatter.formatFileSize(context, totalSize);
    }

    /**
     * 获得SD卡总大小
     *
     * @return
     */
    public static long getSDTotalSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long totalSize = blockSize * totalBlocks;
        return totalSize;
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static String getSDAvailableMsg(Context context) {
        long availableSize = getSDAvailableSize(context);
        return Formatter.formatFileSize(context, availableSize);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */
    public static long getSDAvailableSize(Context context) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        long totalSize = blockSize * availableBlocks;
        return totalSize;
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public static String getRomTotalMsg(Context context) {
        long totalSize = getRomTotalSize(context);
        return Formatter.formatFileSize(context, totalSize);
    }

    /**
     * 获得机身内存总大小
     *
     * @return
     */
    public static long getRomTotalSize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long totalSize = blockSize * totalBlocks;
        return totalSize;
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public static String getRomAvailableMsg(Context context) {
        long availableSize = getRomAvailableSize(context);
        return Formatter.formatFileSize(context, availableSize);
    }

    /**
     * 获得机身可用内存
     *
     * @return
     */
    public static long getRomAvailableSize(Context context) {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        long availableSize = blockSize * availableBlocks;
        return availableSize;
    }

    public static int getHeapSize(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (hasHoneycomb() && isLargeHeap(context)) {
            return getLargeMemoryClass(am);
        }
        return am.getMemoryClass();
    }

    /**
     * 获取当前手机内存信息
     *
     * @param context : Context实例
     * @return 内存
     */
    public static ActivityManager.MemoryInfo getMemoryInfo(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean isHigher4_4() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isHigher5_0() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static boolean isLargeHeap(Context context) {
        return (context.getApplicationInfo().flags & ApplicationInfo.FLAG_LARGE_HEAP) != 0;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static int getLargeMemoryClass(ActivityManager am) {
        return am.getLargeMemoryClass();
    }


    public static ComponentName getTopTask(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> appTask = activityManager.getRunningTasks(1);
        if (appTask == null || appTask.isEmpty()) return null;
        return appTask.get(0).topActivity;
    }


//    public static String getMid(Context context) {
//        String imei = getImei(context);
//        String AndroidID = getAndroidId(context);
//        String serialNo = getSerialNum();
//        return MD5.encrypt(imei + AndroidID + serialNo);
//    }

    private static String generateImei() {
        StringBuilder imei = new StringBuilder();

        // 添加当前秒数 毫秒数 5位
        long time = System.currentTimeMillis();
        String currentTime = Long.toString(time);
        imei.append(currentTime.substring(currentTime.length() - 5));

        // 手机型号 6位
        StringBuilder model = new StringBuilder();
        model.append(Build.MODEL.replaceAll(" ", ""));
        while (model.length() < 6) {
            model.append('0');
        }
        imei.append(model.substring(0, 6));

        // 随机数 4位
        Random random = new Random(time);
        long tmp = 0;
        while (tmp < 0x1000) {
            tmp = random.nextLong();
        }

        imei.append(Long.toHexString(tmp).substring(0, 4));

        return imei.toString();

    }

    /**
     * 获取imei，如果系统不能获取，则将动态产生一个唯一标识并保存
     *
     * @param context Context实例
     * @return imsi字串
     */
    public static String getImei(Context context) {
        String imei = null;
        try {
            SharedPreferences sp = context.getSharedPreferences(IMEI,
                    Context.MODE_PRIVATE);
            imei = sp.getString(IMEI, null);
            if (imei == null || imei.length() == 0) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Activity.TELEPHONY_SERVICE);
                imei = tm.getDeviceId(); // 获取imei的方法修改
                if (imei == null || imei.length() == 0) {
                    imei = generateImei();
                }
                imei = imei.replaceAll(" ", "").trim();
                // imei 小于15位补全
                while (imei.length() < 15) {
                    imei = "0" + imei;
                }
                Editor editor = sp.edit();
                editor.putString(IMEI, imei);
                editor.apply();
            }
            return imei.trim();
        } catch (Exception e) {
        }
        return imei;
    }

    /**
     * 获取imsi，如果系统不能获取，则将动态产生一个唯一标识并保存
     *
     * @param context ： Context实例
     * @return imsi字串
     */
    static public String getImsi(Context context) {
        String imsi = null;
        try {
            SharedPreferences sp = context.getSharedPreferences(IMEI,
                    Context.MODE_PRIVATE);
            imsi = sp.getString(IMSI, null);
            if (imsi == null || imsi.length() == 0) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                imsi = tm.getSubscriberId();
                if (imsi == null || imsi.length() == 0) {
                    imsi = generateImei();
                }
                imsi = imsi.replaceAll(" ", "").trim();
                // imei 小于15位补全
                while (imsi.length() < 15) {
                    imsi = "0" + imsi;
                }
                Editor editor = sp.edit();
                editor.putString(IMSI, imsi);
                editor.apply();
            }
        } catch (Exception e) {
        }
        return imsi;
    }

    /**
     * 获取wifi 模块mac地址
     *
     * @param context ： Context实例
     * @return wifi模块mac地址
     */
    public static String getLocalMacAddress(Context context) {
        try {
            WifiManager wifi = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiaddr = info.getMacAddress();
            if (wifiaddr == null || "".equals(wifiaddr)) {
                SharedPreferences sp = context.getSharedPreferences(MACADDRESS,
                        Context.MODE_PRIVATE);
                wifiaddr = sp.getString(MACADDRESS, "");
            } else {
                SharedPreferences sp = context.getSharedPreferences(MACADDRESS,
                        Context.MODE_PRIVATE);
                Editor editor = sp.edit();
                editor.putString(MACADDRESS, wifiaddr);
                editor.apply();
            }
            return wifiaddr;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取原始的imei，如果没有返回空字符串，
     *
     * @param context : Context实例
     * @return 手机原生imei，获取失败则返回null
     */
    static public String getOriginalImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (imei != null)
            imei = imei.trim();
        return imei;
    }

    /**
     * 获取原始的imsi，如果没有返回空字符串，
     *
     * @param context : Context实例
     * @return 原生imsi，获取失败则返回null
     */
    static public String getOriginalImsi(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = tm.getSubscriberId();
        if (imsi != null)
            imsi = imsi.trim();
        return imsi;
    }

    public static String getSerialNum() {
        String serialNum = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            serialNum = (String) (get.invoke(c, "ro.serialno", "unknown"));
        } catch (Exception ignored) {
            //ignore
        }
        return serialNum;
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isXiaoMiMobile() {
        return "xiaomi".equalsIgnoreCase(Build.BRAND);
    }

    public static boolean isSamsungMobile() {
        return "samsung".equalsIgnoreCase(Build.BRAND);
    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static String getMemoryInfoString(Context context) {
        try {
            ActivityManager.MemoryInfo memoryInfo = getMemoryInfo(context);
            if (null == memoryInfo) return null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                return String.valueOf(memoryInfo.totalMem / 1048576L) + "MB";
            } else {
                return String.valueOf(memoryInfo.availMem / 1048576L) + "MB";
            }
        } catch (Exception e) {
        }
        return null;
    }

}
