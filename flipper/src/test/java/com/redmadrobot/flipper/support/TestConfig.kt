package com.redmadrobot.flipper.support

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperConfig


class TestConfig : FlipperConfig {
    private val features = mutableMapOf(TestFeature.id to true)

    override fun featureIsEnabled(feature: Feature): Boolean {
        return features[feature.id] ?: false
    }

    fun enableFeature(isEnabled: Boolean) {
        features[TestFeature.id] = isEnabled
    }
}
