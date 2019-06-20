package com.example.jbanner;

import android.widget.ImageView;

public interface IJBannerAdapter<M>{
    void fillBannerItemData(JBanner jBanner, ImageView imageView,M mode,int position);
}
