package com.huang.mvp.login;

import android.util.Log;

import com.huang.mvp.base.BaseModel;
import com.huang.mvp.bean.UserInfo;

public class LoginModel extends BaseModel<LoginPresenter,LoginContract.Model> {
    public LoginModel(LoginPresenter loginPresenter) {
        super(loginPresenter);
    }

    @Override
    public LoginContract.Model getContract() {
        return new LoginContract.Model() {
            @Override
            public void executeLogin(String name, String password) throws Exception {
                if (name.equalsIgnoreCase("hjq")&&password.equalsIgnoreCase("123")){
                    p.getContract().responseResult(new UserInfo("网易","hjq"));
                }else {
                    p.getContract().responseResult(new UserInfo());
                }
            }
        };
    }
}
