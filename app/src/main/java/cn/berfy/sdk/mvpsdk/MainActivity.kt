package cn.berfy.sdk.mvpsdk

import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.berfy.sdk.mvpbase.base.CommonActivity
import cn.berfy.sdk.mvpbase.iview.IBaseView
import cn.berfy.sdk.mvpbase.prensenter.BasePresenter
import cn.berfy.sdk.mvpbase.util.CommonUtil
import cn.berfy.sdk.mvpbase.util.ToastUtil
import cn.berfy.sdk.mvpbase.view.RippleView
import cn.berfy.sdk.mvpsdk.R.id.*
import cn.berfy.sdk.mvpsdk.demo.fragment.view.DemoFragmentActivity
import cn.berfy.sdk.mvpsdk.demo.notify.view.NotifyActivity
import cn.berfy.sdk.mvpsdk.demo.permission.view.PermissionActivity
import cn.berfy.sdk.mvpsdk.demo.viewpager.view.StatusBarDemoActivity
import cn.berfy.sdk.mvpsdk.demo.viewpager.view.ViewPagerDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CommonActivity<IBaseView, BasePresenter<IBaseView>>(), View.OnClickListener, RippleView.OnRippleStateListener {

    private var mIsShowTitleBar = true

    override fun getContentViewId(): Int {
        return R.layout.activity_main;
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView() {
        showTitleBar()
        titleBar.setLeftIcon(true, R.mipmap.icon, { ToastUtil.getInstances().showShort("点我干啥") })
        titleBar.setTitle(getString(R.string.app_name))
        sample_text.text = stringFromJNI()
        btn_titlebar_show.setOnRippleStateListener(this)
        btn_viewpager.setOnRippleStateListener(this)
        btn_statusbar.setOnRippleStateListener(this)
        btn_notify.setOnRippleStateListener(this)
        btn_fragment.setOnRippleStateListener(this)
        btn_permission.setOnRippleStateListener(this)
    }

    override fun initPresenter(): BasePresenter<IBaseView>? {
        return null;
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_viewpager -> {
            }
        }
    }

    override fun startRipple(view: RippleView?) {
        when (view) {
            btn_titlebar_show -> {
                mIsShowTitleBar = !mIsShowTitleBar;
                ToastUtil.getInstances().showShort("点击")
            }
            btn_viewpager -> {

            }
        }
    }

    override fun finishRipple(view: RippleView?) {
        when (view) {
            btn_titlebar_show -> {
                if (mIsShowTitleBar) {
                    titleBar.visibility = View.VISIBLE
                    btn_titlebar_show.text = "隐藏标题栏"
                    btn_titlebar_show.setBackgroundResource(R.drawable.button_normal)
                    btn_titlebar_show.setRippleColor(CommonUtil.getColor(mContext, R.color.color_ffe8b7), 1f)
                } else {
                    titleBar.visibility = View.GONE
                    btn_titlebar_show.text = "显示标题栏"
                    btn_titlebar_show.setBackgroundResource(R.drawable.button_press)
                    btn_titlebar_show.setRippleColor(CommonUtil.getColor(mContext, R.color.color_titlebar_light_theme), 1f)
                }
            }
            btn_viewpager -> {
                startActivity(Intent(mContext, ViewPagerDemoActivity::class.java))
            }
            btn_statusbar -> {
                startActivity(Intent(mContext, StatusBarDemoActivity::class.java))
            }
            btn_notify -> {
                startActivity(Intent(mContext, NotifyActivity::class.java))
            }
            btn_fragment -> {
                startActivity(Intent(mContext, DemoFragmentActivity::class.java))
            }
            btn_permission -> {
                startActivity(Intent(mContext, PermissionActivity::class.java))
            }
        }
    }

    override fun cancel(view: RippleView?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
