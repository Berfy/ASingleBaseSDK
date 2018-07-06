package cn.berfy.sdk.mvpsdk.demo.fragment.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.TextView
import cn.berfy.sdk.mvpbase.base.CommonActivity
import cn.berfy.sdk.mvpbase.base.CommonFragment
import cn.berfy.sdk.mvpbase.iview.IBaseView
import cn.berfy.sdk.mvpbase.prensenter.BasePresenter
import cn.berfy.sdk.mvpsdk.R
import cn.berfy.sdk.mvpsdk.R.id.tv_add1
import cn.berfy.sdk.mvpsdk.R.id.tv_add2
import cn.berfy.sdk.mvpsdk.demo.fragment.presenter.DemoFragmentPresenter
import kotlinx.android.synthetic.main.activity_fragment_demo.*

class DemoFragmentActivity : CommonActivity<IDemoFragmentView, DemoFragmentPresenter>(), IDemoFragmentView {

    override fun getContentViewId(): Int {
        return R.layout.activity_fragment_demo
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    override fun initView() {
        showTitleBar()
        titleBar.setTitle("Fragment")
        presenter.init()
    }

    override fun initPresenter(): DemoFragmentPresenter {
        return DemoFragmentPresenter();
    }

    override fun hiddenLoadingView(msg: String?) {

    }

    override fun showLoadingView(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addNewFragment(fragmentLayoutId: Int, fragment: Fragment?) {
        addFragment(fragmentLayoutId, fragment)
    }

    override fun getBtnAdd1(): TextView {
        return tv_add1
    }

    override fun getBtnAdd2(): TextView {
        return tv_add2
    }
}
