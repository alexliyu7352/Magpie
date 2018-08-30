package me.zhouzhuo810.magpie.ui.fgm;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import me.zhouzhuo810.magpie.R;
import me.zhouzhuo810.magpie.ui.act.IBaseActivity;
import me.zhouzhuo810.magpie.ui.dialog.BottomSheetDialog;
import me.zhouzhuo810.magpie.ui.dialog.ListDialog;
import me.zhouzhuo810.magpie.ui.dialog.OneBtnProgressDialog;
import me.zhouzhuo810.magpie.ui.dialog.TwoBtnTextDialog;
import me.zhouzhuo810.magpie.utils.CollectionUtil;
import me.zhouzhuo810.magpie.utils.DisplayUtil;
import me.zhouzhuo810.magpie.utils.ScreenAdapterUtil;

public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        //屏幕适配
        ScreenAdapterUtil.getInstance().loadView(rootView);

        initView(savedInstanceState);

        initData();

        initEvent();

        return rootView;
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().overridePendingTransition(enterAnim, exitAnim);
    }

    @Override
    public View findViewById(int id) {
        if (rootView == null) {
            return null;
        }
        return rootView.findViewById(id);
    }


    @Override
    public IBaseActivity getBaseAct() {
        return (IBaseActivity) getActivity();
    }

    @Override
    public void closeAct() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().closeAct();
    }

    @Override
    public void closeAct(boolean defaultAnimation) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().closeAct(defaultAnimation);
    }

    @Override
    public void closeAllAct() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().closeAllAct();
    }

    @Override
    public void startActWithIntent(Intent intent) {
        startActWithIntent(intent, false);
    }

    @Override
    public void startActWithIntent(Intent intent, boolean defaultAnim) {
        if (defaultAnim) {
            startActivity(intent);
        } else {
            startActivity(intent);
            overridePendingTransition(openInAnimation(), openOutAnimation());

        }
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode) {
        startActWithIntentForResult(intent, requestCode, false);
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode, boolean defaultAnim) {
        if (defaultAnim) {
            startActivityForResult(intent, requestCode);
        } else {
            startActivityForResult(intent, requestCode);
            overridePendingTransition(openInAnimation(), openOutAnimation());
        }
    }

    @Override
    public void showLoadingDialog(String title, String msg) {
        showLoadingDialog(title, msg, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable) {
        showLoadingDialog(title, msg, cancelable, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, boolean iosStyle) {
        showLoadingDialog(title, msg, cancelable, iosStyle, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, DialogInterface.OnDismissListener listener) {
        showLoadingDialog(title, msg, cancelable, false, null);
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean cancelable, boolean iosStyle, DialogInterface.OnDismissListener onDismissListener) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().showLoadingDialog(title, msg, cancelable, iosStyle, onDismissListener);
    }

    @Override
    public void hideLoadingDialog() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().hideLoadingDialog();
    }

    @Override
    public void showOneBtnProgressDialog(String title, String msg, OneBtnProgressDialog.OnProgressListener onProgressListener) {
        showOneBtnProgressDialog(title, msg, false, null, onProgressListener);
    }

    @Override
    public void showOneBtnProgressDialog(String title, String msg, DialogInterface.OnDismissListener onDismissListener, OneBtnProgressDialog.OnProgressListener onProgressListener) {
        showOneBtnProgressDialog(title, msg, false, onDismissListener, onProgressListener);
    }

    @Override
    public void showOneBtnProgressDialog(String title, String msg, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, OneBtnProgressDialog.OnProgressListener onProgressListener) {
        showOneBtnProgressDialog(title, msg, getString(R.string.magpie_ok_text), cancelable, onDismissListener, onProgressListener);
    }

    @Override
    public void showOneBtnProgressDialog(String title, String msg, String btnString, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, OneBtnProgressDialog.OnProgressListener onProgressListener) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().showOneBtnProgressDialog(title, msg, btnString, cancelable, onDismissListener, onProgressListener);
    }

    @Override
    public void hideOneBtnProgressDialog() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().hideOneBtnProgressDialog();
    }

    @Override
    public void showTwoBtnTextDialog(String title, String msg, TwoBtnTextDialog.OnTwoBtnTextClick onTwoBtnClick) {
        showTwoBtnTextDialog(title, msg, false, onTwoBtnClick);
    }

    @Override
    public void showTwoBtnTextDialog(String title, String msg, boolean cancelable, TwoBtnTextDialog.OnTwoBtnTextClick onTwoBtnClick) {
        showTwoBtnTextDialog(title, msg, cancelable, null, onTwoBtnClick);
    }

    @Override
    public void showTwoBtnTextDialog(String title, String msg, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, TwoBtnTextDialog.OnTwoBtnTextClick onTwoBtnClick) {
        showTwoBtnTextDialog(title, msg, getString(R.string.magpie_cancel_text), getString(R.string.magpie_ok_text), cancelable, onDismissListener, onTwoBtnClick);
    }

    @Override
    public void showTwoBtnTextDialog(String title, String msg, String leftBtnString, String rightBtnString, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, TwoBtnTextDialog.OnTwoBtnTextClick onTwoBtnClick) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().showTwoBtnTextDialog(title, msg, leftBtnString, rightBtnString, cancelable, onDismissListener, onTwoBtnClick);
    }

    @Override
    public void hideTwoBtnTextDialog() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().hideTwoBtnTextDialog();
    }

    @Override
    public void showListDialog(String[] items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(null, CollectionUtil.stringToList(items), cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, String[] items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, CollectionUtil.stringToList(items), cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, String[] items, boolean alignLeft, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, CollectionUtil.stringToList(items), alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean alignLeft, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean cancelable, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, cancelable, null, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, ListDialog.OnItemClick onItemClick) {
        showListDialog(title, items, false, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public void showListDialog(String title, List<String> items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, ListDialog.OnItemClick onItemClick) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().showListDialog(title, items, alignLeft, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public void hideListDialog() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().hideListDialog();
    }

    @Override
    public void refreshData(String... params) {

    }

    @Override
    public void loadMoreData(String... params) {

    }

    @Override
    public void showBottomSheet(String title, List<String> items, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, items, false, cancelable, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), false, cancelable, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, List<String> items, boolean alignLeft, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, items, alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean alignLeft, boolean cancelable, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), alignLeft, cancelable, null, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, String[] items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, BottomSheetDialog.OnItemClick onItemClick) {
        showBottomSheet(title, CollectionUtil.stringToList(items), alignLeft, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public void showBottomSheet(String title, List<String> items, boolean alignLeft, boolean cancelable, DialogInterface.OnDismissListener onDismissListener, BottomSheetDialog.OnItemClick onItemClick) {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().showBottomSheet(title, items, alignLeft, cancelable, onDismissListener, onItemClick);
    }

    @Override
    public TextWatcher setEditImageListener(EditText et, ImageView iv) {
        if (getBaseAct() == null) {
            return null;
        }
        return getBaseAct().setEditImageListener(et, iv);
    }

    @Override
    public void hideBottomSheet() {
        if (getBaseAct() == null) {
            return;
        }
        getBaseAct().hideBottomSheet();
    }

    @Override
    public int closeInAnimation() {
        if (getBaseAct() == null) {
            return 0;
        }
        return getBaseAct().closeInAnimation();
    }

    @Override
    public int closeOutAnimation() {
        if (getBaseAct() == null) {
            return 0;
        }
        return getBaseAct().closeOutAnimation();
    }

    @Override
    public int openInAnimation() {
        if (getBaseAct() == null) {
            return 0;
        }
        return getBaseAct().openInAnimation();
    }

    @Override
    public int openOutAnimation() {
        if (getBaseAct() == null) {
            return 0;
        }
        return getBaseAct().openOutAnimation();
    }
}
