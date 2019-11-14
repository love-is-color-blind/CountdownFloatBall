package com.huxq17.floatball.libarary.floatball;

import android.graphics.drawable.Drawable;

public class FloatBallCfg {
    public Drawable mIcon;
    public int mSize;
    /**
     * 标记悬浮球所处于屏幕中的位置
     */
    public Gravity mGravity;
    //第一次显示的y坐标偏移量，左上角是原点。
    public int mOffsetY = 0;
    public boolean mHideHalfLater = true;

    public FloatBallCfg(int size, Drawable icon, Gravity gravity, boolean hideHalfLater) {
        mSize = size;
        mIcon = icon;
        mGravity = gravity;
        mHideHalfLater = hideHalfLater;
    }

    public enum Gravity {
        RIGHT_CENTER(android.view.Gravity.RIGHT | android.view.Gravity.CENTER);

        int mValue;

        Gravity(int gravity) {
            mValue = gravity;
        }

        public int getGravity() {
            return mValue;
        }
        }
}
