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

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.activity.bean.MusicListBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import okhttp3.Call;
import okhttp3.Response;

public class MusicService extends Service {

    private List<String> list=new ArrayList<>();
//    private boolean waiting=true;
//    private boolean havemusic=false;
    private static MusicService service;
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
        requestList();
//        下载列表文件
//        播放音乐
        MediaPlayer player=new MediaPlayer();
        try {
            player.setDataSource(UrlCollect.baseIamgeUrl+File.separator+list.get(0));
//            监听
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    currentIndex++;
                    try {
                        mp.setDataSource(MyApplication.MUSICPATH+File.separator+list.get(currentIndex%list.size()));
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
//
            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    try {
                        mp.setDataSource(MusicService.this,Uri.parse(UrlCollect.baseIamgeUrl+File.separator+list.get(currentIndex%list.size())));
                        mp.prepare();
                        mp.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
//
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void requestList() {
        OkGo.post(UrlCollect.getMusic)//
                .tag(this)//
                .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                        Log.e("=================", "onSuccess: "+s);
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
}
