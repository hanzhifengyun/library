package com.hanzhifengyun.library.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.WindowManager;

/**
 * dp、sp、px 相互转换工具
 */
public class DisplayUtil {

    /**
     * 将px值转换为dip 或 dp值, 保证尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip 或 dp值转换为px值, 保证尺寸大小不变
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 将px值转换为sp值, 保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 将sp值转换为px值, 保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }



    /**
     * 获取屏幕高度
     */
    public static int getWindowHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 获取屏幕高度
     */
    public static int getStatusHeight(Activity context) {
        Rect rect= new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }


    /**
     * 获取屏幕宽度
     */
    public static int getWindowWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

}
