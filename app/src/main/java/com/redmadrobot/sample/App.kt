package com.redmadrobot.sample

import android.app.Application
import com.redmadrobot.flipper.ToggleRouter
import com.redmadrobot.sample.configs.HardcodedConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = HardcodedConfig()
//        val config = RemoteConfig()
        ToggleRouter.init(config)
    }
}
