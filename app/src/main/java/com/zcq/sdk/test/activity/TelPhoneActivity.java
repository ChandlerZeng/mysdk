package com.zcq.sdk.test.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.zcq.sdk.test.R;
import com.zcq.sdk.test.adapter.TelPhoneAdapter;
import com.zcq.sdk.test.entity.PhoneBean;
import com.zcq.sdk.test.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TelPhoneActivity extends AppCompatActivity {

    private ListView listView;
    private List<PhoneBean> phoneBeanList;
    private TelPhoneAdapter adapter;
    private ContentResolver resolver;
    private TelephonyManager telephonyManager;
    private String telNumber = "10000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tel_phone);
        getPersimmion();
        resolver = getContentResolver();
        listView = findViewById(R.id.listview);
        phoneBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            phoneBeanList.add(new PhoneBean("测试" + i, "10000", i+"", "100" + i));
        }
        adapter = new TelPhoneAdapter(this, phoneBeanList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (ActivityCompat.checkSelfPermission(TelPhoneActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + telNumber);
                    intent.setData(data);
                    startActivity(intent);

                    return;
                }
            }
        });
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
        getDataList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        telephonyManager.listen(listener,PhoneStateListener.LISTEN_NONE);
    }

    private void getCallLog(){
//        CallLog.Calls.INCOMING_TYPE;
    }

    /**
     * 读取数据
     *
     * @return 读取到的数据
     */
    private Uri callUri = CallLog.Calls.CONTENT_URI;
    private String[] columns = {CallLog.Calls.CACHED_NAME// 通话记录的联系人
            , CallLog.Calls.NUMBER// 通话记录的电话号码
            , CallLog.Calls.DATE// 通话记录的日期
            , CallLog.Calls.DURATION// 通话时长
            , CallLog.Calls.TYPE};// 通话类型}
    private List<Map<String, String>> getDataList() {
        // 1.获得ContentResolver
        resolver = getContentResolver();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
        }
        // 2.利用ContentResolver的query方法查询通话记录数据库
        /**
         * @param uri 需要查询的URI，（这个URI是ContentProvider提供的）
         * @param projection 需要查询的字段
         * @param selection sql语句where之后的语句
         * @param selectionArgs ?占位符代表的数据
         * @param sortOrder 排序方式
         */
        Cursor cursor = resolver.query(callUri, // 查询通话记录的URI
                columns
                , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
        );
        // 3.通过Cursor获得数据
        List<Map<String, String>> list = new ArrayList<>();
        phoneBeanList.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateLong));
            String time = new SimpleDateFormat("HH:mm").format(new Date(dateLong));
            int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String dayCurrent = new SimpleDateFormat("dd").format(new Date());
            String dayRecord = new SimpleDateFormat("dd").format(new Date(dateLong));
            String typeString = "";
            switch (type) {
                case CallLog.Calls.INCOMING_TYPE:
                    //"打入"
                    typeString = "打入";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    //"打出"
                    typeString = "打出";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    //"未接"
                    typeString = "未接";
                    break;
                case CallLog.Calls.VOICEMAIL_TYPE:
                    //"未接"
                    typeString = "VOICEMAIL_TYPE";
                    break;
                case CallLog.Calls.ANSWERED_EXTERNALLY_TYPE:
                    //"未接"
                    typeString = "ANSWERED_EXTERNALLY_TYPE";
                    break;
                case CallLog.Calls.REJECTED_TYPE:
                    //"未接"
                    typeString = "拒接";
                    break;
                case CallLog.Calls.BLOCKED_TYPE:
                    //"未接"
                    typeString = "占线";
                    break;
                default:
                    break;
            }
            PhoneBean phoneBean = new PhoneBean(name,number,typeString,duration+"");
            phoneBeanList.add(phoneBean);
        }
        adapter.setData(phoneBeanList);
        return list;
    }

    private boolean isOffhook;
    //定义电话状态监听器
    private PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            String msg = "未知";
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    msg = "电话空闲";
                    if(isOffhook){
//                        startActivity(new Intent(TelPhoneActivity.this,TelPhoneActivity.class));
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    msg = incomingNumber + "来电话了，响铃中……";
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    msg = "电话中……";
                    isOffhook = true;
                    break;
            }
            Toast.makeText(TelPhoneActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    //授权信息
    private void getPersimmion() {
        if (Build.VERSION.SDK_INT >= 23) {
            //1. 检测是否添加权限   PERMISSION_GRANTED  表示已经授权并可以使用
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                //手机为Android6.0的版本,未授权则动态请求授权
                //2. 申请请求授权权限
                //1. Activity
                // 2. 申请的权限名称
                // 3. 申请权限的 请求码
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.READ_CALL_LOG}, 1001);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                //手机为Android6.0的版本,未授权则动态请求授权
                //2. 申请请求授权权限
                //1. Activity
                // 2. 申请的权限名称
                // 3. 申请权限的 请求码
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.READ_PHONE_STATE}, 1002);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //手机为Android6.0的版本,未授权则动态请求授权
                //2. 申请请求授权权限
                //1. Activity
                // 2. 申请的权限名称
                // 3. 申请权限的 请求码
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.CALL_PHONE}, 1003);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED) {
                //手机为Android6.0的版本,未授权则动态请求授权
                //2. 申请请求授权权限
                //1. Activity
                // 2. 申请的权限名称
                // 3. 申请权限的 请求码
//                ActivityCompat.requestPermissions(this, new String[]
//                        {Manifest.permission.PROCESS_OUTGOING_CALLS}, 1004);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1001 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("READ_CALL_LOG success");
        }
        if (requestCode == 1002 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("READ_PHONE_STATE success");
        }
        if (requestCode == 1003 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("CALL_PHONE success");
        }
        if (requestCode == 1004 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            LogUtil.e("PROCESS_OUTGOING_CALLS success");
        }
    }

}
