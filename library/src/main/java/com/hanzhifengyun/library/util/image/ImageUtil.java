package com.hanzhifengyun.library.util.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 图片框架
 */

public class ImageUtil {

    public static void loadImage(Context context, String url, int placeholder, ImageView imageView) {
        GlideApp.with(context).load(url).placeholder(placeholder).into(imageView);
    }

}
