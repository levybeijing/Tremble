package friendgoods.vidic.com.generalframework.musicplay;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;

public class MusicService extends Service {
//    实现单例模式
    private static MusicService service;

    public static MusicService getInstance(){
        service=new MusicService();
        return service;
    }
    public class MyBinder extends Binder {
        public MusicService getService(){
            return MusicService.this;
        }
    }
    //通过binder实现了 调用者（client）与 service之间的通信
    public MyBinder binder = new MyBinder();

//    private final Random generator = new Random();

    private MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("==============", "onStartCommand");
        mp = new MediaPlayer();
//        File file=new File(MyApplication.MUSICPATH);
//        String[] list = file.list();
        try {
            mp.setDataSource(this, Uri.parse("http://doutui.oss-cn-beijing.aliyuncs.com/1537522790.mp3"));
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                // 循环播放
                try {
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
        // 播放音乐时发生错误的事件处理
        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            public boolean onError(MediaPlayer mp, int what, int extra) {
                // 释放资源
                try {
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        if(!mp.isPlaying()){
//            // 开始播放
//            mp.start();
//            // 允许循环播放
//            mp.setLooping(true);
//        }
        return binder;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        //松绑Service，会触发onDestroy()
//        if(mp.isPlaying()){
//            mp.stop();
//        }
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
        super.onDestroy();
    }

}
