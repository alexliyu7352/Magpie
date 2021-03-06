package me.zhouzhuo810.magpie.utils.conversion;

import android.view.View;

import me.zhouzhuo810.magpie.utils.loadviewhelper.AbsLoadViewHelper;


public class SimpleConversion implements IConversion {

    @Override
    public void transform(View view, AbsLoadViewHelper loadViewHelper) {
        transform(view, loadViewHelper, false);
    }
    
    @Override
    public void transform(View view, AbsLoadViewHelper loadViewHelper, boolean forceWidthHeight) {
        if (view.getLayoutParams() != null) {
            loadViewHelper.loadWidthHeightFont(view, forceWidthHeight);
            loadViewHelper.loadPadding(view, forceWidthHeight);
            loadViewHelper.loadLayoutMargin(view, forceWidthHeight);
            loadViewHelper.loadMinWidthAndHeight(view, forceWidthHeight);
        }
    }
}
