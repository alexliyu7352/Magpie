package me.zhouzhuo810.appx.fgm;

import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import android.widget.TextView;

import me.zhouzhuo810.magpiex.ui.fgm.BaseFragment;
import me.zhouzhuo810.appx.R;

public class TestFragmentFour extends BaseFragment {

    private TextView tvTab;

    @Override
    public int getLayoutId() {
        return R.layout.fgm_test;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvTab = (TextView) findViewById(R.id.tv_tab);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            int index = getArguments().getInt("index");
            tvTab.setText("Tab" + index);
        }
    }

    @Override
    public void lazyLoadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTab.setText("i'm new one"+System.currentTimeMillis());
            }
        }, 100);
    }

    @Override
    public void initEvent() {

    }

}
