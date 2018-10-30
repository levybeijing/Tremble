package friendgoods.vidic.com.generalframework.musicplay;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import friendgoods.vidic.com.generalframework.MyApplication;
import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MusicThread extends Thread {
    private String str;
    private Context context;
    public MusicThread(Context context_,String str_){
        str=str_;
        context=context_;
    }
    @Override
    public void run() {
        super.run();
        MusicBean musicBean = new Gson().fromJson(str, MusicBean.class);
        List<MusicBean.DataBean> data = musicBean.getData();
        File file=new File(MyApplication.MUSICPATH);
        if (!file.exists())
            file.mkdir();
//        for (int i = 0; i < data.size(); i++) {
        final MusicBean.DataBean dataBean = data.get(0);
        Log.e("==============", "music "+UrlCollect.baseIamgeUrl+dataBean.getUrl());
        OkGo.get(UrlCollect.baseIamgeUrl+dataBean.getUrl())//
                    .tag(this)//
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            Log.e("==============", "runmusic ");
                            try {
                                InputStream inputStream = response.body().byteStream();
                                FileOutputStream fos=new FileOutputStream(new File(MyApplication.MUSICPATH+File.separator+dataBean.getUrl()));
                                int size;
                                byte[] buffer=new byte[2048];
                                while((size=inputStream.read(buffer))!=-1){
                                    fos.write(buffer,0,size);
                                    fos.flush();
                                }
                                fos.close();
                                //        标记加上启动服务
                                Log.e("==============", "fos.close() ");
                                SharedPFUtils.setParam(context,"havemusic",true);
                                context.startService(new Intent(context,MusicService.class));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
//        }

    }
}
