package com.example.wanandoridtraing.base;

import android.content.Context;

public interface IBaseView<T extends IBasePresenter> {


    void setPresenter(T presenter);

    Context getContextObject();
}
