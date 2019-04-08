package com.hanzhifengyun.library.util;

import android.view.View;

/**
 * 防止用户快速点击多次按钮
 */
public abstract class PreventDoubleClickListener implements View.OnClickListener {

    /**
     * 限制用户不能连续点击的间隔时间
     */
    public static final long MIN_CLICK_DELAY_TIME = 500;

    private static boolean enabled = true;

    private static final Runnable ENABLE_AGAIN = new Runnable() {
        @Override
        public void run() {
            enabled = true;
        }
    };

    @Override
    public void onClick(View view) {
        if (enabled) {
            enabled = false;
            view.postDelayed(ENABLE_AGAIN, MIN_CLICK_DELAY_TIME);
            doClick(view);
        }
    }

    /**
     * 响应用户点击事件
     *
     * @param view view
     */
    public abstract void doClick(View view);
}
