package com.example.itours;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //滑动菜单
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar    android.support.v7.widget.Toolbar;
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //将Toolbar的实例导入
        setSupportActionBar(toolbar);
        //drawerLayout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        //根据getSupportActionBar()来获得actionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            //让导航按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_view_headline_black_36dp);
        }

        //NavigationView
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        //默认选择拨打电话
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //关闭活动界面
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //FloatingActionButton
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "FAB clicked", Toast.LENGTH_LONG).show();
                //利用Snackbar来作为提示工具
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_LONG).show();
                    }
                }).show();
            }
        });

    }

    //对HomeAsUp按钮的点击事件进行处理

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
