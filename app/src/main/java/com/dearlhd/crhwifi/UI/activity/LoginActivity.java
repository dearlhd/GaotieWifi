package com.dearlhd.crhwifi.UI.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dearlhd.crhwifi.R;
import com.dearlhd.crhwifi.SDK.bean.Account;
import com.dearlhd.crhwifi.SDK.network.CRHWifiApi;
import com.dearlhd.crhwifi.SDK.response.LoginResponse;
import com.dearlhd.crhwifi.SDK.util.SQLiteHelper;

import rx.Subscriber;

public class LoginActivity extends Activity {
    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Subscriber<LoginResponse> mSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        SQLiteHelper helper = new SQLiteHelper();
        if (helper.getUid() != 0) {
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
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
        final String username = mEtAccount.getText().toString();
        String password = mEtPassword.getText().toString();
        mSubscriber = new Subscriber<LoginResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(LoginActivity.this, "网络错误，请稍后重试", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(LoginResponse response) {
                if (response.result != 1) {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    SQLiteHelper helper = new SQLiteHelper();
                    helper.setUser(username, response.uid);
                }

                Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
