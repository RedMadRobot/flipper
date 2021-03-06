package com.redmadrobot.sample.configs

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.EmptyValue
import com.redmadrobot.flipper.ext.buildFeaturesMap
import com.redmadrobot.sample.Features

class HardcodedConfig : FlipperConfig {
    private val features = buildFeaturesMap {
        Features.Feature1 bind true
        Features.Feature2 bind true
        Features.Feature3 bind true
        Features.Feature4 bind false
    }

    override fun getValue(feature: Feature): FlipperValue {
        return features[feature.id] ?: EmptyValue
    }
}
