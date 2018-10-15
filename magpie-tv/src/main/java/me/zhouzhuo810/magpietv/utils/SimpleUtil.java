package me.zhouzhuo810.magpietv.utils;

import android.graphics.Typeface;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * 常用方法简化
 */
public class SimpleUtil {

    /**
     * 缩放View
     *
     * @param v 要缩放的View
     */
    public static void scaleView(View v) {
        ScreenAdapterUtil.getInstance().loadView(v);
    }

    /**
     * 缩放值
     *
     * @param px 原来值
     * @return 缩放后的值
     */
    public int getScaledValue(int px) {
        return ScreenAdapterUtil.getInstance().getScaledValue(px);
    }

    /**
     * 获取字符串资源
     *
     * @param resId 资源ID
     * @return 字符串
     */
    public String getString(@StringRes int resId) {
        return BaseUtil.getApp().getString(resId);
    }

    /**
     * 获取颜色资源
     *
     * @param resId 资源ID
     * @return 颜色值
     */
    public int getColor(@ColorRes int resId) {
        return BaseUtil.getApp().getResources().getColor(resId);
    }

    /**
     * 获取字符串数组资源
     *
     * @param resId 资源ID
     * @return 字符串数组
     */
    public String[] getStringArray(@ArrayRes int resId) {
        return BaseUtil.getApp().getResources().getStringArray(resId);
    }
    
    /**
     * 获取assets文件夹中的字体
     *
     * @param path 路径，如fonts/xxx.ttf
     * @return Typeface对象，没有则返回null
     */
    public Typeface getFontFromAssets(String path) {
        return FontUtil.getTypeFaceFromAssets(path);
    }
    
    /**
     * 获取assets文件夹中的文件的字符串内容
     *
     * @param path 路径，如json/test.json
     * @return 文件内容字符串
     */
    public String getFileContentFromAssets(String path) {
        return AssetsUtil.getFileToStringFromAssets(path);
    }
}