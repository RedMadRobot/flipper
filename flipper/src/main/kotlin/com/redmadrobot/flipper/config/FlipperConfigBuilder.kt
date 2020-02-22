package com.redmadrobot.flipper.config

import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.config.FlipperValue.*


class FlipperConfigBuilder {
    private val features = mutableMapOf<String, FlipperValue>()

    infix fun Feature.bind(value: Boolean) {
        features[this.id] = BooleanValue(value)
    }

    infix fun Feature.bind(value: String) {
        features[this.id] = StringValue(value)
    }

    infix fun Feature.bind(value: Double) {
        features[this.id] = DoubleValue(value)
    }

    infix fun Feature.bind(value: Long) {
        features[this.id] = LongValue(value)
    }

    infix fun Feature.bind(value: ByteArray) {
        features[this.id] = ByteArrayValue(value)
    }

    fun build() = features
}
