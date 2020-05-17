package com.huang.mvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMvpActivity<P extends BasePresenter,CONTRACT> extends AppCompatActivity {

    protected P p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //弱引用
        p = getPresenter();
        p.bindView(this);

        initData();
        initView();

    }

    protected void initData(){

    }

    protected  void initView() {

    }

    public abstract int getLayoutId() ;

    //让P层做什么需求
    public abstract CONTRACT getContract();

    //从子类中获取具体的契约
    public abstract P getPresenter();

    //如果Presenter层出现了异常，需要告知View层
    public void error(Exception e){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.unBindView();
    }
}
