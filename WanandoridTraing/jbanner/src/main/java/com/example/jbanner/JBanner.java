package com.example.jbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

public class JBanner extends ConstraintLayout {

    private ViewPager mViewPager;
    private TextView mtitle;
    private BannerIndicator mbannerindicator;
    private int mId=100;
    private static final float MASK_HEIGHT_FACTOR = 2.5f ;
    private IJBannerAdapter mAdapter;
    // 用户显示banner 图片的Banner 对象list，也是可以使 图片url 集合
    private List<?> mDatas;
    // 显示title 的集合，可以为空
    private List<String> mTitles;
    // 自动轮播间隔时间，默认为一秒
    private int mInterval = 1000;
    // title 字体大小，单位px
    private int mTitleSize;
    private int mTitleColor;
    private int mIndicatorRadius;
    private int mIndicatorSelectColor;
    private int mIndicatorUnSelectColor;
    private int mMaxTitleSize;
    // 是否开启自动轮播
    private boolean mIsLoop;
    private boolean mIsManualScroll;

    public JBanner(Context context) {
        super(context);
    }

    public JBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr){
        mMaxTitleSize= JBannerUtils.dp2px(getContext(), 20);
        mTitleColor= Color.WHITE;
//        TypedArray typedArray=getContext().obtainStyledAttributes(attrs,R.styleable)

    }


    public  void initview(){
        mViewPager=new ViewPager(getContext());
        mViewPager.setId(mId++);
        ConstraintSet constraintSet=new ConstraintSet();
        constraintSet.clone(this);


        constraintSet.connect(mViewPager.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
        constraintSet.connect(mViewPager.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        constraintSet.connect(mViewPager.getId(),ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP);
        constraintSet.connect(mViewPager.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mViewPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mViewPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        addView(mViewPager);


        ImageView imageView = new ImageView(getContext());
        imageView.setId(mId++);
        constraintSet.connect(imageView.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT);
        constraintSet.connect(imageView.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.connect(imageView.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        constraintSet.constrainWidth(imageView.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(imageView.getId(),JBannerUtils.dp2px(getContext(),10));
        addView(imageView);


        mbannerindicator=new BannerIndicator(getContext());
        mbannerindicator.setId(mId++);
        constraintSet.connect(mbannerindicator.getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT);
        constraintSet.connect(mbannerindicator.getId(),ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM);
        constraintSet.constrainWidth(mViewPager.getId(),ConstraintSet.WRAP_CONTENT);
        constraintSet.constrainHeight(mViewPager.getId(),ConstraintSet.WRAP_CONTENT);
        addView(mbannerindicator);

        mtitle=new TextView(getContext());
        mtitle.setId(mId++);
        constraintSet.connect(mtitle.getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,JBannerUtils.dp2px(getContext(),32));
        constraintSet.connect(mtitle.getId(),ConstraintSet.BOTTOM,imageView.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(mtitle.getId(),ConstraintSet.TOP,imageView.getId(),ConstraintSet.TOP);
        constraintSet.connect(mtitle.getId(),ConstraintSet.RIGHT,mbannerindicator.getId(),ConstraintSet.LEFT);
        constraintSet.constrainWidth(mViewPager.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(mViewPager.getId(),ConstraintSet.WRAP_CONTENT);


    }
}
