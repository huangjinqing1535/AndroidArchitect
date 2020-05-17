package com.huang.mvp.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huang.mvp.R;
import com.huang.mvp.base.BaseMvpActivity;
import com.huang.mvp.bean.UserInfo;

public class LoginActivity extends BaseMvpActivity<LoginPresenter, LoginContract.View> implements View.OnClickListener {

    private EditText userNameEt;
    private EditText passwordEt;
    private Button loginBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        userNameEt = findViewById(R.id.username_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        String userName = userNameEt.getText().toString();
        String password = passwordEt.getText().toString();
        if (TextUtils.isEmpty(userName)||TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"参数为空",Toast.LENGTH_SHORT).show();
            return;
        }
        p.getContract().requestLogin(userName,password);

    }

    @Override
    public LoginContract.View getContract() {
        return new LoginContract.View<UserInfo>() {
            @Override
            public void handlerResult(UserInfo userInfo) {
                if (userInfo!=null){
                    Toast.makeText(LoginActivity.this,"userinfo="+userInfo.toString(),Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"登录失败！",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void showToast(String msg){

    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter();
    }
}
