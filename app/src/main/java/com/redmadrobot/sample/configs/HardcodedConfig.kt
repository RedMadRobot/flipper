package com.redmadrobot.sample.configs

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.value.FeatureValue
import com.redmadrobot.flipper.config.value.FeatureValueImpl
import com.redmadrobot.sample.Features


class HardcodedConfig : FlipperConfig() {
    private val features = mapOf(
        Features.Feature1.id to true,
        Features.Feature2.id to false,
        Features.Feature3.id to true,
        Features.Feature4.id to "Hello"
    )

    override fun getFeatureValue(feature: Feature): FeatureValue {
        val featureValue = features[feature.id]
            ?: throw IllegalArgumentException("Feature with id `${feature.id}` not found.")

        return FeatureValueImpl(featureValue.toString())
    }
// It works fine, but quite roughly =)
//    override fun featureIsEnabled(feature: Feature): Boolean {
//        return features[feature.id]?.let {
//            it
//        } ?: throw IllegalArgumentException("Feature with id `${feature.id}` not found.")
//    }
}
