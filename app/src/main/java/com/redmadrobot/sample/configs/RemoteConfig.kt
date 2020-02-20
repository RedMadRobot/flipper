package com.redmadrobot.sample.configs

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.firebase_config.toFlipperValue
import com.redmadrobot.sample.R


class RemoteConfig : FlipperConfig {
    private val remoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    private val configSettings by lazy {
        FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(0)
            .build()
    }

    init {
        with(remoteConfig) {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("[REMOTE CONFIG]", "Successful")
                }
            }
        }
    }

    override fun getValue(feature: Feature): FlipperValue {
        return remoteConfig.getValue(feature.id).toFlipperValue()
    }
}
