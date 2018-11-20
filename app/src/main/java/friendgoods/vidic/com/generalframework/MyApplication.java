package friendgoods.vidic.com.generalframework;import android.app.Activity;import android.app.Application;import android.content.Intent;import android.os.Environment;import com.google.gson.Gson;import com.lzy.okgo.OkGo;import com.lzy.okgo.cache.CacheEntity;import com.lzy.okgo.cache.CacheMode;import com.lzy.okgo.callback.StringCallback;import com.lzy.okgo.cookie.store.PersistentCookieStore;import com.lzy.okgo.model.HttpHeaders;import com.lzy.okgo.model.HttpParams;import com.tencent.mm.opensdk.openapi.IWXAPI;import com.tencent.mm.opensdk.openapi.WXAPIFactory;import org.json.JSONException;import org.json.JSONObject;import java.io.File;import java.util.ArrayList;import java.util.List;import friendgoods.vidic.com.generalframework.entity.UrlCollect;import friendgoods.vidic.com.generalframework.musicplay.MusicBean;import friendgoods.vidic.com.generalframework.musicplay.MusicService;import friendgoods.vidic.com.generalframework.musicplay.MusicThread;import friendgoods.vidic.com.generalframework.util.SharedPFUtils;import okhttp3.Call;import okhttp3.Response;import static friendgoods.vidic.com.generalframework.entity.UrlCollect.WXAppID;/** * Created by Administrator on 2016/10/9 0009. */public class MyApplication extends Application {    // 获取到主线程的上下文    private static MyApplication mContext = null;    private static List<Activity> cacheActivity = new ArrayList<>();//    public static int USERID;//    public static String NAME;//    public static String USERICON;//    public static Double USERINTEGRAL;//    public static String WX;//OpenId    public static String MUSICPATH=Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"trem";    @Override    public void onCreate() {        super.onCreate();        this.mContext = this;        initImageLoader();        String token = (String)SharedPFUtils.getParam(this, "token", "");        if (token!=null&&token.length()!=0){            HttpHeaders headers = new HttpHeaders();            headers.put("token",token);            OkGo.getInstance().addCommonHeaders(headers);        }    }    private void requestMusic() {        OkGo.post(UrlCollect.getMusic)//                .tag(this)//                .execute(new StringCallback() {                    @Override                    public void onSuccess(String s, Call call, Response response) {                        String old = (String) SharedPFUtils.getParam(MyApplication.this, "music", "");                        boolean havemusic = (boolean) SharedPFUtils.getParam(MyApplication.this, "havemusic", false);                        if (!havemusic||!old.equals(s)){                            new MusicThread(MyApplication.this,s).start();                        }else {                            SharedPFUtils.setParam(MyApplication.this,"music",s);//                            开启服务                            startService(new Intent(MyApplication.this,MusicService.class));                        }                    }                });    }    // 对外暴露上下文    public static MyApplication getApplication() {        return mContext;    }    private void initImageLoader(){        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------////        HttpHeaders headers = new HttpHeaders();//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文//        headers.put("commonHeaderKey2", "commonHeaderValue2");//        HttpParams params = new HttpParams();//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码//        params.put("commonParamsKey2", "这里支持中文参数");        //-----------------------------------------------------------------------------------//        //必须调用初始化        OkGo.init(this);        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数        //好处是全局参数统一,特定请求可以特别定制参数        try {            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了            OkGo.getInstance()                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行                    .debug("OkGo")                    //如果使用默认的 60秒,以下三行也不需要传                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/                    .setCacheMode(CacheMode.NO_CACHE)                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)                    //如果不想让框架管理cookie,以下不需要//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）                    .setCookieStore(new PersistentCookieStore());     //cookie持久化存储，如果cookie不过期，则一直有效                    //可以设置https的证书,以下几种方案根据需要自己设置//                    .setCertificates()                                  //方法一：信任所有证书（选一种即可）//                    .setCertificates(getAssets().open("srca.cer"))      //方法二：也可以自己设置https证书（选一种即可）//                    .setCertificates(getAssets().open("aaaa.bks"), "123456", getAssets().open("srca.cer"))//方法三：传入bks证书,密码,和cer证书,支持双向加密                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行//                .addInterceptor(new Interceptor() {//                    @Override//                    public Response intercept(Chain chain) throws IOException {//                        return chain.proceed(chain.request());//                    }//                })                    //这两行同上,不需要就不要传//                    .addCommonHeaders(headers)                                         //设置全局公共头//                    .addCommonParams(params);                                          //设置全局公共参数        } catch (Exception e) {            e.printStackTrace();        }    }    public static void addCacheActivity(Activity ac){        if(ac != null){            cacheActivity.add(ac);        }    }}