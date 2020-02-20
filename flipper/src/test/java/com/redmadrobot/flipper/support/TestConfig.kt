package com.redmadrobot.flipper.support

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.ext.buildFeaturesMap
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.BooleanValue


class TestConfig : FlipperConfig {
    private val features = buildFeaturesMap {
        TestFeature to true
    }

    override fun getValue(feature: Feature): FlipperValue {
        return features[feature.id]?.let { it } ?: throw IllegalArgumentException()
    }

    fun enableFeature(isEnabled: Boolean) {
        features[TestFeature.id] = BooleanValue(isEnabled)
    }

    fun setValue(value: FlipperValue) {
        features[TestFeature.id] = value
    }
}
