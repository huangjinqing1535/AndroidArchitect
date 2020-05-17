package com.huang.mvvm;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.huang.mvvm.databinding.ActivityMainBinding;

public class LoginViewModel {


    private static final String TAG = LoginViewModel.class.getSimpleName();
    public UserInfo userInfo;

    public LoginViewModel(ActivityMainBinding activityMainBinding) {
        userInfo = new UserInfo();
        activityMainBinding.setLoginViewModel(this);
    }

    public TextWatcher nameInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userInfo.name.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public TextWatcher pwdInputListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            userInfo.password.set(String.valueOf(s));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    public View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    if ("huangjinqing".equals(userInfo.name.get()) && "123".equals(userInfo.password.get())) {
                        Log.i(TAG, "登录成功");
                    } else {
                        Log.i(TAG, "登录失败");
                    }
                }
            }).start();
        }
    };

}
