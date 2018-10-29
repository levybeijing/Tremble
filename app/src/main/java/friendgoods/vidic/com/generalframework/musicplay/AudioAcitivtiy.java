package friendgoods.vidic.com.generalframework.musicplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class AudioAcitivtiy extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        开始音乐
        musicStart();
    }

    private void musicStart() {

    }

    @Override
    protected void onPause() {
//        暂停
        musicPause();
        super.onPause();
    }

    private void musicPause() {

    }

    @Override
    protected void onDestroy() {
//        销毁
        musicDestroy();
        super.onDestroy();
    }

    private void musicDestroy() {

    }
}
