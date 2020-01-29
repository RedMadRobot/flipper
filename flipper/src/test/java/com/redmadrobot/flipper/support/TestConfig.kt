package com.redmadrobot.flipper.support

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.value.FeatureValue
import com.redmadrobot.flipper.config.value.FeatureValueImpl


class TestConfig : FlipperConfig() {
    private val features = mutableMapOf(
        TestBooleanFeature.id to true,
        TestStringFeature.id to "Test"
    )

    override fun featureIsEnabled(feature: Feature): Boolean {
        return getFeatureValue(feature).asBoolean()
    }

    override fun getFeatureValue(feature: Feature): FeatureValue {
        val featureValue = features[feature.id]
            ?: throw IllegalArgumentException("Feature with id `${feature.id}` not found.")

        return FeatureValueImpl(featureValue.toString())
    }

    fun enableFeature(isEnabled: Boolean) {
        features[TestBooleanFeature.id] = isEnabled
    }

    fun changeStringFeatureValue(value: String) {
        features[TestStringFeature.id] = value
    }
}
