package com.redmadrobot.flipper.firebase_config

import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.*

fun FirebaseRemoteConfigValue.toFlipperValue(): FlipperValue {
    return when (val value = extract(this)) {
        is Boolean -> BooleanValue(value)
        is Double -> DoubleValue(value)
        is Long -> LongValue(value)
        is ByteArray -> ByteArrayValue(value)
        is String -> StringValue(value)
        else -> EmptyValue
    }
}

private inline fun <T> FirebaseRemoteConfigValue.guard(block: FirebaseRemoteConfigValue.() -> T): T? {
    @Suppress("SwallowedException")
    return try {
        block()
    } catch (e: IllegalArgumentException) {
        null
    }
}

private inline fun extract(value: FirebaseRemoteConfigValue): Any? {
    // @formatter:off
    return value.guard { asBoolean() }
        ?: value.guard { asDouble() }
        ?: value.guard { asLong() }
        ?: value.guard { asByteArray() }
        ?: value.guard { asString() }
    // @formatter:on
}
