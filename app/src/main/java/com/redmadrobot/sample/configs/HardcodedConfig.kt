package com.redmadrobot.sample.configs

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.buildFeaturesMap
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperConfigBuilder
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.BooleanValue
import com.redmadrobot.flipper.config.FlipperValue.EmptyValue
import com.redmadrobot.sample.Features


class HardcodedConfig : FlipperConfig {
    private val features = buildFeaturesMap {
        appendBoolean(Features.Feature1.id to true)
        appendBoolean(Features.Feature2.id to false)
        appendBoolean(Features.Feature3.id to true)
        appendBoolean(Features.Feature4.id to false)
    }

    override fun getValue(feature: Feature): FlipperValue {
        return features[feature.id] ?: EmptyValue
    }
}
