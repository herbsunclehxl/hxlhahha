package com.example.wanandoridtraing.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandoridtraing.GlideApp;
import com.example.wanandoridtraing.R;
import com.example.wanandoridtraing.base.BaseFragment;
import com.example.wanandoridtraing.home.banner.Banner;
import com.example.wanandoridtraing.home.banner.BannerIndicator;

import java.lang.reflect.Field;
import java.util.List;

public class HomeFragment extends BaseFragment {


    private ViewPager mViewPager;
    private List<Banner> mBanners;
    private TextView mBannerTitle;
    private BannerIndicator mBannerIndicator;
    private boolean isManualScroll;
    private float factory;



    @Override
    public boolean isNeedAnimation() {
        return false;
    }

    @Override
    protected boolean isNeedToAddBackStack() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        mViewPager = inflate.findViewById(R.id.home_top_banner);
        mBannerTitle = inflate.findViewById(R.id.home_banner_title);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                factory=positionOffset;
            }

            @Override
            public void onPageSelected(int position) {
                mBannerIndicator.setCurrentIndex(position % mBanners.size());
                mBannerTitle.setText(mBanners.get(position % mBanners.size()).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE ){
                    factory = 0;

                    if(isManualScroll){
                        isManualScroll = false;
                    }
                }
            }
        });

        mBannerIndicator = inflate.findViewById(R.id.home_banner_indicator);
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        setViewPagerScroller();
        return inflate;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBanners = Banner.getBanners();

        mBannerIndicator.setIndicatorCount(mBanners.size());
        mViewPager.setAdapter(new BannerAdapter());

        int i = Integer.MAX_VALUE / 2;
        int j = i  % mBanners.size();
        if( j != 0){
            i = (mBanners.size() - j) + i;
        }

        mViewPager.setCurrentItem(i);

        mViewPager.postDelayed(loop, 3000);



        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mViewPager.getHandler().removeCallbacks(loop);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mViewPager.postDelayed(loop, 3000);
                }else if(event.getAction() == MotionEvent.ACTION_MOVE){
                    isManualScroll = true;
                }


                return false;
            }

        });

    }

    private Runnable loop = new Runnable() {
        @Override
        public void run() {
            int current  = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(++current, true);

            mViewPager.postDelayed(this, 3000);
        }
    };

    public class BannerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mBanners == null ? 0 : Integer.MAX_VALUE;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v  =  LayoutInflater.from(getContext()).inflate(R.layout.layout_banner_item, container, false);

            ImageView imageView = v.findViewById(R.id.home_banner_img);

            GlideApp.with(container).load(mBanners.get(position % mBanners.size()).getImgUrl()).into(imageView);
            v.setTag(position % mBanners.size());
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }






    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private  float MIN_SCALE = 0.75f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when
                // moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
                        * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);

            }
        }

    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private  float MIN_SCALE = 0.85f;

        private  float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to
                // shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);

                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    private void setViewPagerScroller() {

        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");

            scrollerField.setAccessible(true);

            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");


            interpolator.setAccessible(true);

            Scroller scroller = new Scroller(getContext(), (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    int newDuration;
                    if(isManualScroll){
                        newDuration = duration;
                    }else{
                        newDuration = (int) ((duration * 4 ) * (1- factory));
                    }
                    Log.d("Test", "duration = " + duration + " 修改后的 " + newDuration);
                    super.startScroll(startX, startY, dx, dy, newDuration);    // 这里是关键，将duration变长或变短
                }
            };
            scrollerField.set(mViewPager, scroller);
        } catch (NoSuchFieldException e) {
            // Do nothing.
        } catch (IllegalAccessException e) {
            // Do nothing.
        }
    }

}
