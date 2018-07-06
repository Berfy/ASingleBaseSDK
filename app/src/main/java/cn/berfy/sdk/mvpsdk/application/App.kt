package cn.berfy.sdk.mvpsdk.application

import cn.berfy.sdk.mvpbase.base.BaseApplication
import cn.berfy.sdk.mvpbase.config.CacheConstant
import cn.berfy.sdk.mvpbase.util.CrashException
import cn.berfy.sdk.mvpbase.util.NotifycationUtil
import cn.berfy.sdk.mvpbase.util.ToastUtil
import cn.berfy.sdk.mvpsdk.MainActivity

class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ToastUtil.init(BaseApplication.getContext())
        CacheConstant.setRootDir("MvpSDK")
        CrashException.getInstance().init(BaseApplication.getContext(), MainActivity::class.java)
        NotifycationUtil.init(BaseApplication.getContext(), MainActivity::class.java)
    }
}
