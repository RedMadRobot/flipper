package com.redmadrobot.flipper.config.value

interface FeatureValue {

    fun asString(): String

    fun asBoolean(): Boolean
}