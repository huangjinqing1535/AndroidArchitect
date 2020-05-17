package com.huang.mvp.login;

import com.huang.mvp.base.BasePresenter;
import com.huang.mvp.bean.UserInfo;

public class LoginPresenter extends BasePresenter<LoginModel,LoginActivity,LoginContract.Presenter> {
    @Override
    public LoginContract.Presenter getContract() {
        return new LoginContract.Presenter<UserInfo>() {
            @Override
            public void requestLogin(String name, String pwd) {
                try {
                    m.getContract().executeLogin(name,pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void responseResult(UserInfo userInfo) {
                getView().getContract().handlerResult(userInfo);
            }
        };
    }

    @Override
    public LoginModel getModel() {
        return new LoginModel(this);
    }

}
