package com.example.jbanner;

import android.content.Context;

public class JBannerUtils {

    /**
     * dp  转 px
     * @param context
     * @param dp  传入多少dp
     * @return  返回dp 换算成px 后的值
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);

    }
}
