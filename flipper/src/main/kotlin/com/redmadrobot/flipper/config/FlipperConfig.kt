package com.redmadrobot.flipper.config

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.value.FeatureValue


abstract class FlipperConfig {

    abstract fun getFeatureValue(feature: Feature): FeatureValue

    open fun featureIsEnabled(feature: Feature): Boolean {
        return getFeatureValue(feature).asBoolean()
    }

    fun getString(feature: Feature): String {
        return getFeatureValue(feature).asString()
    }
}
