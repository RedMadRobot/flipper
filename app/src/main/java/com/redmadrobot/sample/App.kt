package com.redmadrobot.sample

import android.app.Application
import com.redmadrobot.flipper.ToggleRouter
import com.redmadrobot.sample.configs.HardcodedConfig
import com.redmadrobot.sample.configs.RemoteConfig


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = HardcodedConfig()
//        val config = RemoteConfig(this)
        ToggleRouter.init(config)
    }
}
