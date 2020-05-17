package com.huang.mvp.base;

import java.lang.ref.WeakReference;

//Presenter基类
public abstract class BasePresenter<M extends BaseModel, V extends BaseMvpActivity, CONTRACT> {
    protected M m;
    //绑定View层弱引用
    private WeakReference<V> vWeakReference;

    public BasePresenter() {
        this.m = getModel();
    }

    public void bindView(V v) {
        vWeakReference = new WeakReference<>(v);
    }

    public void unBindView() {
        if (vWeakReference != null) {
            vWeakReference.clear();
            vWeakReference = null;
            System.gc();
        }
    }

    public V getView() {
        if (vWeakReference != null) {
            return vWeakReference.get();
        }
        return null;
    }

    public abstract CONTRACT getContract();

    public abstract M getModel();

}
