package com.example.itours;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * 新建BaseActivity作为所有活动的父类
 */
public class BaseActivity extends AppCompatActivity {

    //广播接收器
    private ForceOfflineReceive receive;

    @Override
    //当处于onCreate状态时，ActivityCollector类添加活动
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    //栈顶
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        //添加action
        intentFilter.addAction("com.example.administrator.broadcastbestpractice.FORCE_OFFLINE");
        receive = new ForceOfflineReceive();
        registerReceiver(receive, intentFilter);
    }

    //暂停


    @Override
    protected void onPause() {
        super.onPause();
        if(receive != null){
            unregisterReceiver(receive);
            receive = null;
        }
    }

    @Override
    //当处于onDestroy状态时，ActivityCollector类移除活动
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //广播接收器
    class ForceOfflineReceive extends BroadcastReceiver{
        @Override
        public void onReceive(final Context context, Intent intent) {
            //利用AlertDialog.Builder来创建一个对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline, Please try to login again.");
            //设为不可取消，不然点击back后就会继续使用程序了
            builder.setCancelable(false);
            //确定后的动作
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();              //销毁所有活动
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);              //重新启动LoginActivity
                }
            });
            builder.show();
        }
    }
}
