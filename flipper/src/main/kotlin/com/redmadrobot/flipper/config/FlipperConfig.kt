package com.redmadrobot.flipper.config

import com.redmadrobot.flipper.Feature

interface FlipperConfig {
    fun getValue(feature: Feature): FlipperValue
}
