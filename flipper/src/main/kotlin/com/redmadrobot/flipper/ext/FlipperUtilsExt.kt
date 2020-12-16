package com.redmadrobot.flipper.ext

import com.redmadrobot.flipper.config.FlipperConfigBuilder
import com.redmadrobot.flipper.config.FlipperValue

inline fun buildFeaturesMap(builderAction: FlipperConfigBuilder.() -> Unit): MutableMap<String, FlipperValue> {
    return FlipperConfigBuilder().apply(builderAction).build()
}
