package com.huang.mvp.login;

import com.huang.mvp.bean.BaseEntity;

//将Model层、View层、Presenter层协商共同业务，封装成接口
//契约、合同
public interface LoginContract {

    interface Model{
        void executeLogin(String name,String password) throws Exception;
    }

    interface View<T extends BaseEntity>{
        //真实返回的是javabean
        void handlerResult(T t);
    }

    interface Presenter<T extends BaseEntity>{
        //登录请求，接收到View的命令，可以自己做，也可以让Model层去执行
        void requestLogin(String name,String pwd);

        void responseResult(T t);

    }
}
