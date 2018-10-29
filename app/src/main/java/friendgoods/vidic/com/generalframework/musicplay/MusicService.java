package friendgoods.vidic.com.generalframework.musicplay;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

import friendgoods.vidic.com.generalframework.util.SharedPFUtils;

public class MusicService extends Service {
//    实现单例模式
    private static MusicService service;

//    public static MusicService getInstance(){
//        service=new MusicService();
//        return service;
//    }
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
        mp = new MediaPlayer();
        String music1 = (String) SharedPFUtils.getParam(this, "music0", null);
        try {
            mp.setDataSource(music1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
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
        return super.onStartCommand(intent, flags, startId);
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