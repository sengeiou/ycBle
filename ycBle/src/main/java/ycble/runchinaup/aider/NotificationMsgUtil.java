package ycble.runchinaup.aider;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.NotificationManagerCompat;

import com.google.gson.Gson;

import java.util.List;
import java.util.Set;

import ycble.runchinaup.log.ycBleLog;


/**
 * Created by nopointer on 2018/7/26.
 * 通知栏消息监听工具
 */

public final class NotificationMsgUtil {

    /**
     * 判断消息栏通知权限是否授权
     *
     * @param context
     * @return
     */
    public static boolean isEnabled(Context context) {
        Set<String> packageNames = NotificationManagerCompat.getEnabledListenerPackages(context);
        ycBleLog.e("获取了通知栏监听权限的应用包名:" + new Gson().toJson(packageNames));
        return packageNames.contains(context.getPackageName());
    }


    /**
     * 前往设置通知栏权限
     *
     * @param context
     */
    public static void goToSettingNotificationAccess(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 前往设置辅助功能
     *
     * @param context
     */
    public static void goToSettingAccessibility(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //开启监听通知栏的服务
    public static void reStartNotifyListenService(Context context) {
        ycBleLog.e("reBindService==>NPNotificationService");
        ComponentName thisComponent = new ComponentName(context, NPNotificationService.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 关闭监听通知栏的服务
     * 如果调用这个方法 会导致通知栏监听里面没有这个app的选项
     *
     * @param context
     */
    private static void closeService(Context context) {
        ComponentName thisComponent = new ComponentName(context, NPNotificationService.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(thisComponent, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param context
     * @param clazz   是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceExisted(Context context, Class clazz) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if (!(serviceList.size() > 0)) {
            return false;
        }

        String className = clazz.getName();
        String packageName = context.getPackageName();
        ycBleLog.e("className====>" + "" + context.getPackageName() + "///" + className);

        for (int i = 0; i < serviceList.size(); i++) {
            ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
            ComponentName serviceName = serviceInfo.service;
            if (serviceName.getPackageName().equalsIgnoreCase(packageName) && serviceName.getClassName().equals(className)) {
                ycBleLog.e("开启了通知监听====>" + "" + context.getPackageName() + "///" + className);
//                ycBleLog.e(" serviceInfo.getPackageName===>" + serviceName.getPackageName());
                return true;
            }
        }
        return false;
    }


}
