package friendgoods.vidic.com.generalframework.musicplay;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import friendgoods.vidic.com.generalframework.entity.UrlCollect;
import friendgoods.vidic.com.generalframework.util.SharedPFUtils;
import okhttp3.Call;
import okhttp3.Response;

public class DownloadService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        开始下载
        ArrayList<MusicBean.DataBean> music = intent.getParcelableArrayListExtra("music");
//        for (int i = 0; i < music.size(); i++) {
//            Log.e("==============", "onStartCommand: "+i);
            final MusicBean.DataBean dataBean = music.get(0);
//            final int finalI = i;
            OkGo.post(UrlCollect.baseIamgeUrl+dataBean.getUrl())//
                    .tag(this)//
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            InputStream inputStream = response.body().byteStream();
                            String path=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+dataBean.getUrl();
                            Log.e("==============", "onStartCommand: "+path);
                            SharedPFUtils.setParam(DownloadService.this,"music0",path);
                            try {
                                FileOutputStream fos=new FileOutputStream(new File(path));
                                int size;
                                byte[] buffer=new byte[1024];
                                while((size=inputStream.read(buffer))!=-1){
                                    fos.write(buffer,0,size);
                                }
                                fos.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "下载完成", Toast.LENGTH_SHORT).show();
        SharedPFUtils.setParam(this, "haveMusic", true);
//        Intent intent = new Intent("android.intent.action.ServiceStart");
//        sendBroadcast(intent);
//        Intent intent=new Intent(this,MusicService.class);
//        intent.putParcelableArrayListExtra()
//        startService(new Intent(this,MusicService.class));
    }
}
