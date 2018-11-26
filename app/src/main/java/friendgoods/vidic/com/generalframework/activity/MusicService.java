package friendgoods.vidic.com.generalframework.activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.activity.bean.MusicListBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class MusicService extends Service {

    private List<String> list=new ArrayList<>();
    private boolean havemusic=false;
    private static MusicService service;
    private MediaPlayer player;

    public static MusicService getInstance(){
        if (service==null){
            service=new MusicService();
        }
        return service;
    }
    private int currentIndex=0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        获取列表
        File  ff=new File(MyApplication.MUSICPATH);
        if (!ff.exists()){
            ff.mkdirs();
        }
        player = new MediaPlayer();
        player.setLooping(true);
        havemusic= (boolean) SharedPFUtils.getParam(MusicService.this,"havemusic",false);
        if (!havemusic){
            requestList();
        }else{
            try {
                String[] strings =ff.list();
                player.setDataSource(MyApplication.MUSICPATH+File.separator+strings[new Random().nextInt(3)]);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        }
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    currentIndex++;
                    Log.e("============", "havemusic: "+currentIndex);
                    if (list.size()==0) {
                        return;
                    }
                    mp.stop();
                    mp.release();
                    mp = null;
                    mp = new MediaPlayer();
                    try {
                        if (havemusic){
                            File file=new File(MyApplication.MUSICPATH);
                            String[] list = file.list();
                            Log.e("============", "havemusic: "+list.length);
                            mp.setDataSource(MyApplication.MUSICPATH+File.separator+list[currentIndex%list.length]);
                            Log.e("============", "havemusic: "+MyApplication.MUSICPATH+File.separator+list[currentIndex%list.length]);
                            mp.prepare();
                            mp.start();
                        }else{
                            mp.setDataSource(UrlCollect.baseIamgeUrl+File.separator+list.get(currentIndex%list.size()));
                            Log.e("============", "nomusic: "+Uri.parse(UrlCollect.baseIamgeUrl+File.separator+list.get(currentIndex%list.size())));

                            mp.prepare();
                            mp.start();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
//
            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    if (list.size()==0){
                        return false;
                    }
                    mp.stop();
                    mp.release();
                    mp = null;
                    mp = new MediaPlayer();
                    try {
                        mp.setDataSource(MusicService.this
                                ,Uri.parse(UrlCollect.baseIamgeUrl+File.separator+list.get(currentIndex%list.size())));
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        return Service.START_NOT_STICKY;
    }
//请求列表 并下载 储存标识
    private void requestList() {
        OkGo.post(UrlCollect.getMusic)//
                .tag(this)//
                .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                        Log.e("=================", "onSuccess: "+s);
                        if (!(boolean) SharedPFUtils.getParam(MusicService.this,"havemusic",false)){
                            SharedPFUtils.setParam(MusicService.this,"music",s);
                        }
                        MusicListBean listBean = new Gson().fromJson(s, MusicListBean.class);
                        List<MusicListBean.DataBean> data = listBean.getData();
                        if (data==null){
                            return;
                        }
    //                        获取列表  和下载
                        for (int i = 0; i < data.size(); i++) {
                            list.add(data.get(i).getUrl());
                            download(data.get(i).getUrl());
                        }
                        try {
                            player.setDataSource(MusicService.this,Uri.parse(UrlCollect.baseIamgeUrl+File.separator+list.get(currentIndex%list.size())));
                            player.prepare();
                            player.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void download(final String name){
        OkGo.<File>get(UrlCollect.baseIamgeUrl+File.separator+name)//
                .tag(this)//
                .execute(
                    new FileCallback() {
                        @Override
                        public void onSuccess(File file, Call call, Response response) {
                            try {
                                File f=new File(MyApplication.MUSICPATH+File.separator+name);
                                file.renameTo(f);
                                havemusic=true;
                                SharedPFUtils.setParam(MusicService.this,"havemusic",true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.stop();
        }
        stopSelf();
    }
}
