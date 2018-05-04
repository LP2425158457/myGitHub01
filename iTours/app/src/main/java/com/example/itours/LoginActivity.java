package com.example.itours;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //获取填写的账户和密码的信息
        accountEdit = (EditText)findViewById(R.id.account_text);
        passwordEdit = (EditText)findViewById(R.id.password_text);
        login = (Button)findViewById(R.id.login);
        //为登陆按钮注册一个点击事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();      //账户
                String pwd = passwordEdit.getText().toString();         //密码
                //账号为admin，密码为123456
                if(account.equals("admin") && pwd.equals("123456")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    //将loginActivity关闭
                    finish();
                }else{
                    //密码输入错误就进行提示
                    Toast.makeText(LoginActivity.this, "message input is erro", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
