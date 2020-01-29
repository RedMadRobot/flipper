package com.redmadrobot.flipper.config.value

class FeatureValueImpl(private val value: String) : FeatureValue {

    override fun asBoolean(): Boolean {
        return value.toBoolean()
    }

    override fun asString(): String {
        return value
    }
}