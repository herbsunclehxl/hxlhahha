package com.example.wanandoridtraing.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

import com.cunoraz.gifview.library.GifView;
import com.example.wanandoridtraing.R;

public class LoadingPage2 extends ConstraintLayout {

    public static final int MODE_1 = 1; // 透明背景
    public static final int MODE_2 = 2;// 背景不透明


    private LinearLayout mLoadingLayout;

    private Group mErrorGroup;


    private GifView mGifView;

    private TextView mTVMsg;

    private Button mReload;



    private int mMode;



    public LoadingPage2(Context context) {
        super(context);
    }

    public LoadingPage2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPage2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView(){

        mLoadingLayout = findViewById(R.id.loading_layout);
        mErrorGroup = findViewById(R.id.loading_group_error);
        mGifView = findViewById(R.id.loading_gif_view);
        mTVMsg = findViewById(R.id.loading_tv_msg);
        mReload = findViewById(R.id.loading_btn_reload);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    public void show(int mode ){
        if(mode == MODE_1){
            setBackgroundColor(Color.parseColor("#00000000"));

        }else{
            setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }
        mErrorGroup.setVisibility(View.GONE);
        mLoadingLayout.setVisibility(View.VISIBLE);

        mGifView.play();

    }

}
