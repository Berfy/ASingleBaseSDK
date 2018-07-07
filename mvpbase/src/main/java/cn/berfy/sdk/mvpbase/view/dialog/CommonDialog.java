package cn.berfy.sdk.mvpbase.view.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import cn.berfy.sdk.mvpbase.R;

/**
 * 通用确认提示框
 * 主要封装了一下show和dismiss方法
 *
 * @author Berfy
 * 2018.5.22
 */
public class CommonDialog {

    private Activity mContext;
    private AlertDialog mDialog;//APP版本升级提示专用弹框

    public CommonDialog(Activity context) {
        this.mContext = context;
        initDialog();
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        mDialog = builder.create();
    }

    public void showTipDialog(String msg, String okBtnText, DialogInterface.OnClickListener onClickListener) {
        showTipDialog("温馨提示", msg, okBtnText, onClickListener);
    }

    public void showTipDialog(String title, String msg, String okBtnText, DialogInterface.OnClickListener onClickListener) {
        mDialog.setTitle(title);
        mDialog.setMessage(msg);
        mDialog.setCancelable(false);
        mDialog.setButton(AlertDialog.BUTTON_POSITIVE, okBtnText, onClickListener);
        show();
    }

    public void showDialog(String msg, String leftBtnText, String rightBtnText, DialogInterface.OnClickListener leftOnClickListener, DialogInterface.OnClickListener rightOnClickListener) {
        showDialog("温馨提示", msg, leftBtnText, rightBtnText, leftOnClickListener, rightOnClickListener);
    }

    public void showDialog(String title, String msg, String leftBtnText, String rightBtnText, DialogInterface.OnClickListener leftOnClickListener, DialogInterface.OnClickListener rightOnClickListener) {
        mDialog.setTitle(title);
        mDialog.setMessage(msg);
        mDialog.setCancelable(false);
        mDialog.setButton(AlertDialog.BUTTON_POSITIVE, leftBtnText, leftOnClickListener);
        mDialog.setButton(AlertDialog.BUTTON_NEGATIVE, rightBtnText, rightOnClickListener);
        show();
    }

    public CommonDialog setContentView(int layoutId) {
        mDialog.setContentView(layoutId);
        return this;
    }

    public CommonDialog setContentView(View v) {
        mDialog.setContentView(v);
        return this;
    }

    public CommonDialog setTitle(String title) {
        mDialog.setTitle(title);
        return this;
    }

    public CommonDialog setMessage(String msg) {
        mDialog.setMessage(msg);
        return this;
    }

    public CommonDialog setButton(int whichButton, CharSequence text, DialogInterface.OnClickListener listener) {
        mDialog.setButton(whichButton, text, listener);
        return this;
    }

    public CommonDialog setButton(int iconResId) {
        mDialog.setIcon(iconResId);
        return this;
    }

    public CommonDialog setCancelable(boolean cancelable) {
        mDialog.setCancelable(cancelable);
        return this;
    }

    public CommonDialog setCanceledOnTouchOutside(boolean cancelable) {
        mDialog.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public void show() {
        if (!mContext.isFinishing() && null != mDialog && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (!mContext.isFinishing() && null != mDialog && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
