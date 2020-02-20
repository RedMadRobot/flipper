package com.redmadrobot.flipper.config


sealed class FlipperValue {
    object EmptyValue : FlipperValue()

    data class BooleanValue(val value: Boolean) : FlipperValue()
    data class StringValue(val value: String) : FlipperValue()
    data class DoubleValue(val value: Double) : FlipperValue()
    data class LongValue(val value: Long) : FlipperValue()

    data class ByteArrayValue(val value: ByteArray) : FlipperValue() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ByteArrayValue

            if (!value.contentEquals(other.value)) return false

            return true
        }

        override fun hashCode(): Int {
            return value.contentHashCode()
        }
    }
}

