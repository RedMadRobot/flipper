package com.redmadrobot.sample.configs

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.buildFeaturesMap
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.EmptyValue
import com.redmadrobot.sample.Features


class HardcodedConfig : FlipperConfig {
    private val features = buildFeaturesMap {
        Features.Feature1 to true
        Features.Feature2 to false
        Features.Feature3 to true
        Features.Feature4 to false
    }

    override fun getValue(feature: Feature): FlipperValue {
        return features[feature.id] ?: EmptyValue
    }
}
