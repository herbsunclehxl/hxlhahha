package com.example.wanandoridtraing.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ButtonNavigationView extends ConstraintLayout implements CompoundButton.OnCheckedChangeListener {

    public ButtonNavigationView(Context context) {
        super(context);
    }

    public ButtonNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ButtonNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //当布局都找完的时候  就是确保所有的布局都被加载出来之后 在调用此方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View button;
        for(int i = 0; i < getChildCount(); i ++){
            button = getChildAt(i);
            if(button instanceof RadioButton){
                ((RadioButton)getChildAt(i)).setOnCheckedChangeListener(this);
            }
        }
    }


    //点击按钮监听变化的监听  实现CompoundButton.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
        if (ischeck) {  //我们只关注 由未选中变为选中的情况，由选中变为未选中的情况我们不关注
            unCheckOtherButton(compoundButton);
            if (mChangedListener!=null){
                mChangedListener.onCheckedChanged(compoundButton,false);
            }
        }


    }


    public  void unCheckOtherButton(CompoundButton checkButton){
         View button;
        for (int i = 0; i <getChildCount(); i++) {
            button = getChildAt(i);
            if (button instanceof RadioButton&&button!=checkButton){
                ((RadioButton) button).setChecked(false);
            }
        }
    }


    private OnTabCheckedChangedListener mChangedListener;


    public void setOnTabChangedListener(OnTabCheckedChangedListener mChangedListener) {
        this.mChangedListener = mChangedListener;
    }

    public interface  OnTabCheckedChangedListener{
        void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
    }
}
