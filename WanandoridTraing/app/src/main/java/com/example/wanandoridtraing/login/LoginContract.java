package com.example.wanandoridtraing.login;

import com.example.wanandoridtraing.base.IBaseCallBack;
import com.example.wanandoridtraing.base.IBasePresenter;
import com.example.wanandoridtraing.base.IBaseView;
import com.example.wanandoridtraing.data.entity.User;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

import io.reactivex.disposables.Disposable;
public interface LoginContract {



    public interface ILoginView extends IBaseView<ILoginPresenter> {

        void onSuccess(User user);

        void onFail(String msg);
    }


    public interface ILoginPresenter extends IBasePresenter<ILoginView> {

        void register(String username, String password, String repassword);
        void login(String username, String password);

    }


    public interface ILoginSource{

        void register(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

        void login(LifecycleProvider provider, Map<String, String> params, IBaseCallBack<User> callBack);

    }

}
