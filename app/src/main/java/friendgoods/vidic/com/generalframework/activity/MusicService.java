package friendgoods.vidic.com.generalframework.activity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import friendgoods.vidic.com.generalframework.activity.bean.MusicListBean;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import okhttp3.Call;
import okhttp3.Response;

public class MusicService extends Service {

    private List<String> list=new ArrayList<>();

    private static MusicService service;
    public static MusicService getInstance(){
        if (service==null){
            service=new MusicService();
        }
        return service;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        获取列表
        requestList();
//        下载列表文件

//        播放音乐

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
                        for (int i = 0; i < data.size(); i++) {
                            list.add(UrlCollect.baseIamgeUrl+File.separator+data.get(i).getUrl());
                        }
//                        列表
                    }
                });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
