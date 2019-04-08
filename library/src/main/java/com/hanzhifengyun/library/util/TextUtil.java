package com.hanzhifengyun.library.util;

import android.support.annotation.Nullable;

public class TextUtil {

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }


    /**
     * 比较两个String 是否内容相同
     */
    public static boolean equals(String left, String right) {
        if (!isEmpty(left) && !isEmpty(right)) {
            return left.equals(right);
        }
        return false;
    }
}
