package com.redmadrobot.flipper.config

import com.redmadrobot.flipper.Feature


interface FlipperConfig {
    fun featureIsEnabled(feature: Feature): Boolean
}
