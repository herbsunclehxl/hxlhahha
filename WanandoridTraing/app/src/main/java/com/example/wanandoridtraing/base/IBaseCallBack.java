package com.example.wanandoridtraing.base;

import io.reactivex.disposables.Disposable;


public interface IBaseCallBack<T> {

    void onSuccess(T data);
    void onFail(String msg);
}
