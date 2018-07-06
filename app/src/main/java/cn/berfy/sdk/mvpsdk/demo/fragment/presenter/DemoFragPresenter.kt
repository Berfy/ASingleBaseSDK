package cn.berfy.sdk.mvpsdk.demo.fragment.presenter

import android.view.View
import cn.berfy.sdk.mvpbase.prensenter.BasePresenter
import cn.berfy.sdk.mvpsdk.R
import cn.berfy.sdk.mvpsdk.demo.fragment.view.DemoFragment
import cn.berfy.sdk.mvpsdk.demo.fragment.view.IDemoFragView
import cn.berfy.sdk.mvpsdk.demo.fragment.view.IDemoFragmentView
import kotlinx.android.synthetic.main.activity_fragment_demo.view.*

class DemoFragPresenter : BasePresenter<IDemoFragView>(), View.OnClickListener {

    fun init() {
        view.getBtnRemove().setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            view.getBtnRemove() -> {
                view.removeFrag()
            }
        }
    }
}