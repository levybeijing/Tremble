package friendgoods.vidic.com.generalframework._idle;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import friendgoods.vidic.com.generalframework.R;
import friendgoods.vidic.com.generalframework.mine.listener.OnItemClickListenerPosition;
import friendgoods.vidic.com.generalframework.mine.adapter.AdapterBluelist;
import friendgoods.vidic.com.generalframework.mine.bluelink.ClsUtils;

public class BluetoothListActivity extends AppCompatActivity {
    private static  final int PERMISSIONS_ACCESS_COARSE_LOCATION=1000;
    private Switch sw;
    private String mac;
//    private TextView list;
    private BluetoothAdapter mBluetoothAdapter;

    private List<BluetoothDevice> list=new ArrayList<>();
    private AdapterBluelist adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetoothlist);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        initView();
        mac = getMac();

    }
    private void initView() {
        if (Build.VERSION.SDK_INT >= 23){
            requestPermission();
        }
        findViewById(R.id.iv_back_bluelist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sw = findViewById(R.id.switch_bluelist);
//        list = findViewById(R.id.tv_bluelist);
        TextView islink = findViewById(R.id.tv_islink_bluelist);
        TextView name = findViewById(R.id.tv_namelink_bluelist);

        sw.setChecked(mBluetoothAdapter.isEnabled());
        if (!mBluetoothAdapter.isEnabled()) {
            //若没打开则打开蓝牙
            mBluetoothAdapter.enable();
            sw.setChecked(mBluetoothAdapter.isEnabled());
        }
//        设置开关
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                mBluetoothAdapter.enable();
            }else{
                mBluetoothAdapter.disable();
            }
            }
        });

        RecyclerView rv = findViewById(R.id.rv_bluelist);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterBluelist(this);
        rv.setAdapter(adapter);

//        UUID[] uuids=new UUID[];

//注册设备被发现时的广播
        IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filter);
//注册一个搜索结束时的广播
        IntentFilter filter2=new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,filter2);

        mBluetoothAdapter.startDiscovery();
//蓝牙列表监听
        adapter.setOnItemClickListenerPosition(new OnItemClickListenerPosition() {
            @Override
            public void onItemClick(final int i) {
//                弹窗确认是否配对
                Toast.makeText(BluetoothListActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(BluetoothListActivity.this);
                //    设置Title的图标
                //    设置Title的内容
                //    设置Content来显示一个信息
                if (list.get(i).getName()==null){
                    builder.setMessage("连接"+list.get(i).getAddress()+"?");
                }else{
                    builder.setMessage("连接"+list.get(i).getName()+"?");
                }
                //    设置一个PositiveButton
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try {
                            ClsUtils.createBond(list.get(i).getClass(),list.get(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
//                        取消
                    }
                });
                //    显示出该对话框
                builder.show();
            }
        });
    }

    private String pin="0000";
    //定义广播接收
    private BroadcastReceiver mReceiver=new BroadcastReceiver(){

        private BluetoothDevice device;

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(BluetoothDevice.ACTION_FOUND))
            {
//                所有的device 包含已经配对的
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                list.add(device);
                adapter.setData(list);
//                list.append("\n"+device.getBondState()+"\n"+device.getType()+"\n"+device.getUuids()+"\n"+device.getBluetoothClass()+"\n");
//在此处不停地刷新数据 刷新adapter 设置点击时间弹窗 确定 执行createbond方法
//                监听PAIRING_REQUEST 动作 进行配对
//                if(device.getBondState()==BluetoothDevice.BOND_BONDED)
//                {    //显示已配对设备
////                    list.append("\n"+device.getName()+"==>"+device.getAddress()+"\n");
//                }else if(device.getBondState()!=BluetoothDevice.BOND_BONDED)
//                {
////                    list.append("\n"+device.getName()+"==>"+device.getAddress()+"\n");
//                }

            }else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)){
                Toast.makeText(context, "搜索完成", Toast.LENGTH_SHORT).show();
            }else if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")){
                try {
                    //1.确认配对
                    ClsUtils.setPairingConfirmation(device.getClass(), device, true);
                    //2.终止有序广播
//                    Log.i("order...", "isOrderedBroadcast:"+isOrderedBroadcast()+",isInitialStickyBroadcast:"+isInitialStickyBroadcast());
                    abortBroadcast();//如果没有将广播终止，则会出现一个一闪而过的配对框。
                    //3.调用setPin方法进行配对...
                    boolean ret = ClsUtils.setPin(device.getClass(), device, pin);
                    //假如配对成功则保存mac地址  通过mac地址确定设备  唯一  通过协议建立连接
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){ //表示未授权时
            //进行授权
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSIONS_ACCESS_COARSE_LOCATION);
        }else{
            //调用打电话的方法
//            makeCall();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==PERMISSIONS_ACCESS_COARSE_LOCATION){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){

            }else{
                //被拒绝的逻辑
                return;
            }

        }
    }

    /**
     * 获取mac地址
     * @return
     */
    public String getMac() {
        String mac_s= "";
        StringBuilder buf = new StringBuilder();
        try {
            byte[] mac;
            NetworkInterface ne= NetworkInterface.getByInetAddress(InetAddress.getByName(getIpAddress(this)));
            mac = ne.getHardwareAddress();
            for (byte b : mac) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            mac_s = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mac_s;
    }

    public static String getIpAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 3/4g网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //  wifi网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {
                // 有限网络
                return getLocalIp();
            }
        }
        return null;
    }
    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    // 获取有限网IP
    private static String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";

    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}

