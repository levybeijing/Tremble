package friendgoods.vidic.com.generalframework.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import friendgoods.vidic.com.generalframework.LoginReceiver;
import friendgoods.vidic.com.generalframework.activity.LoginCodeActivity;
import friendgoods.vidic.com.generalframework.activity.MusicService;
import friendgoods.vidic.com.generalframework.activity.PKModelActivity;
import friendgoods.vidic.com.generalframework.mine.activity.SettingsActivity;

/**
 * Created by wh on 2017/7/10.
 */

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 记录处于前台的Activity
     */
    private static BaseActivity mForegroundActivity = null;
    /**
     * 记录所有活动的Activity
     */
    private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    private LoginReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActivity(this);
        // 1. 实例化BroadcastReceiver子类 &  IntentFilter
        receiver = new LoginReceiver();
        IntentFilter intentFilter = new IntentFilter();
        // 2. 设置接收广播的类型
        intentFilter.addAction("action.LOGIN.OTHER");
        registerReceiver(receiver, intentFilter);

    }

    /**
     * onCreate()时添加
     * @param activity
     */
    public static void addActivity(BaseActivity activity){
        //判断集合中是否已经添加，添加过的则不再添加
        if (!mActivities.contains(activity)){
            mActivities.add(activity);
        }
    }
    @Override
    protected void onResume() {
        mForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        mForegroundActivity = null;
        super.onPause();
    }
    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public static void finishOther(BaseActivity except) {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != except)
                activity.finish();
        }
    }

    /**
     * 是否有启动的Activity
     */
    public static boolean hasActivity() {
        return mActivities.size() > 0;
    }

    /**
     * 获取当前处于前台的activity
     */
    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 获取当前处于栈顶的activity，无论其是否处于前台
     */
    public static BaseActivity getCurrentActivity() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<>(mActivities);
        }
        if (copy.size() > 0) {
            return copy.get(copy.size() - 1);
        }
        return null;
    }
    /**
     * 退出应用
     */
    public void exitApp() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public void stopMusic(){
        startService(new Intent(BaseActivity.this,MusicService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!hasActivity()){
            stopMusic();
        }
        unregisterReceiver(receiver);
    }
}