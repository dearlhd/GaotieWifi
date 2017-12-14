package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Account;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;

import rx.Subscriber;

public class LoginActivity extends Activity {
    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Subscriber<Integer> mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEtAccount = (EditText) findViewById(R.id.et_account);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String username = mEtAccount.getText().toString();
        String password = mEtPassword.getText().toString();
        mSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(LoginActivity.this, "网络错误，请稍后重试", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(Integer i) {
//                if (auth.authorization == null) {
//                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
//                } else {
//                    // 将登录获得的认证信息存入本地文件
//                    requestWritePermission();
//                    SQLiteHelper helper = new SQLiteHelper();
//                    try {
//                        helper.setAuth(auth);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(LoginActivity.this, "您的手机存储无效，请联系应用提供者", Toast.LENGTH_SHORT).show();
//                    }
//
//                    auth = helper.getAuth();
//
//
                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
//                }
            }
        };

        // 测试用
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        CRHWifiApi.getInstance().login(mSubscriber, account);
    }
}
