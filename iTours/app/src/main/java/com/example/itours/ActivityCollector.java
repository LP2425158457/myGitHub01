package com.example.itours;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ActivityCollector用于管理所有的活动
 */
public class ActivityCollector {
    //定义一个活动activity的集合
    public static List<Activity> activities = new ArrayList<>();
    //添加
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    //移除
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    //关闭所有的活动
    public static void finishAll(){
        for (Activity activity:activities) {
            //判断是否在运行
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
