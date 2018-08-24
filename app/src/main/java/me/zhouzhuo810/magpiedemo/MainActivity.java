package me.zhouzhuo810.magpiedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.zhouzhuo810.magpie.ui.act.BaseActivity;
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.LanguageUtil;
import me.zhouzhuo810.magpie.utils.ToastUtil;
import me.zhouzhuo810.magpiedemo.api.Api;
import me.zhouzhuo810.magpiedemo.api.entity.GetWeatherList;
import me.zhouzhuo810.magpie.utils.RxHelper;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etCity;
    private Button btnGo;
    private TextView tvResult;
    private Button btnLanguage;
    private Button btnDialog;
    private View btnTitle;
    private Button btnDownload;

    @Override
    public boolean shouldSupportMultiLanguage() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        btnLanguage = findViewById(R.id.btn_language);
        btnDialog = findViewById(R.id.btn_dialog);
        btnTitle = findViewById(R.id.btn_title);
        btnDownload = (Button) findViewById(R.id.btn_download);
        etCity = (EditText) findViewById(R.id.et_city);
        btnGo = (Button) findViewById(R.id.btn_go);
        tvResult = (TextView) findViewById(R.id.tv_result);
    }

    @Override
    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add((i + 1) + "#");
        }
        Collections.shuffle(list);
        Log.e(TAG, "排序前：\n" + list.toString());
        CollectionUtil.sort(list, false);
        Log.e(TAG, "排序后：\n" + list.toString());

    }


    @Override
    public void initEvent() {

        btnLanguage.setOnClickListener(this);

        btnDialog.setOnClickListener(this);

        btnTitle.setOnClickListener(this);

        btnDownload.setOnClickListener(this);

        btnGo.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                String city = etCity.getText().toString().trim();
                Api.getApi()
                        .getWeatherList(city)
                        .compose(RxHelper.<GetWeatherList>io_main())
                        .subscribe(new Observer<GetWeatherList>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(GetWeatherList getWeatherList) {
                                tvResult.setText(getWeatherList.toString());
                            }

                            @Override
                            public void onError(Throwable e) {
                                ToastUtil.showShortToast(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                break;
            case R.id.btn_language:
                String[] items = getResources().getStringArray(R.array.language);
                showListDialog(getString(R.string.app_name), items, true, false, new ListDialog.OnItemClick() {
                    @Override
                    public void onItemClick(int position, String item) {
                        switch (position) {
                            case 0:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.SIMPLE_CHINESE);
                                recreate();
                                break;
                            case 1:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.TRADITIONAL_CHINESE);
                                recreate();
                                break;
                            case 2:
                                LanguageUtil.setGlobalLanguage(LanguageUtil.ENGLISH);
                                recreate();
                                break;
                        }
                    }
                });
                break;
            case R.id.btn_dialog:
                startAct(DialogActivity.class);
                break;
            case R.id.btn_title:
                startAct(TitleActivity.class);
                break;
            case R.id.btn_download:
                startAct(DownloadActivity.class);
                break;
        }
    }
}
