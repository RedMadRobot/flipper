package com.redmadrobot.flipper.config

import com.redmadrobot.flipper.config.FlipperValue.*


class FlipperConfigBuilder {
    private val features = mutableMapOf<String, FlipperValue>()

    fun appendBoolean(pair: Pair<String, Boolean>): FlipperConfigBuilder {
        features[pair.first] = BooleanValue(pair.second)
        return this
    }

    fun appendString(pair: Pair<String, String>): FlipperConfigBuilder {
        features[pair.first] = StringValue(pair.second)
        return this
    }

    fun appendDouble(pair: Pair<String, Double>): FlipperConfigBuilder {
        features[pair.first] = DoubleValue(pair.second)
        return this
    }

    fun appendLong(pair: Pair<String, Long>): FlipperConfigBuilder {
        features[pair.first] = LongValue(pair.second)
        return this
    }

    fun appendByteArray(pair: Pair<String, ByteArray>): FlipperConfigBuilder {
        features[pair.first] = ByteArrayValue(pair.second)
        return this
    }


    fun build() = features
}
