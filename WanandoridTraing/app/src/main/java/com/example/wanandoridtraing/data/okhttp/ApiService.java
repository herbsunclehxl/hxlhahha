package com.example.wanandoridtraing.data.okhttp;


import com.example.wanandoridtraing.AppConstant;
import com.example.wanandoridtraing.data.entity.HttpResult;
import com.example.wanandoridtraing.data.entity.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {

    @POST(AppConstant.WEB_SITE_REGISTER)
    @FormUrlEncoded
    Observable<HttpResult<User>> register(@FieldMap Map<String, String> params);

    @POST(AppConstant.WEB_SITE_LOGIN)
    @FormUrlEncoded
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);



}
