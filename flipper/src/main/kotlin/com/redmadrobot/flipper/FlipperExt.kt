package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import com.redmadrobot.flipper.config.FlipperConfigBuilder
import com.redmadrobot.flipper.config.FlipperValue

typealias FlipperPredicate = (FlipperValue) -> Boolean

inline fun flipperPoint(feature: Feature, noinline action: () -> Unit) {
    ToggleRouter.flip(feature, action)
}

inline fun flipperPointAb(feature: Feature, noinline actionA: () -> Unit, noinline actionB: () -> Unit) {
    ToggleRouter.flipAb(feature, actionA, actionB)
}

inline fun <T> flipperPointAb(feature: Feature, valueA: T, valueB: T): T {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

inline fun <T> flipperPointAb(feature: Feature, noinline valueA: () -> T, noinline valueB: () -> T): T {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

inline fun View.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

inline fun View.flipperPointAb(feature: Feature, alternativeView: View) {
    ToggleRouter.flipAb(feature, this, alternativeView)
}

inline fun MenuItem.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

inline fun MenuItem.flipperPointAb(feature: Feature, alternativeMenuItem: MenuItem) {
    ToggleRouter.flipAb(feature, this, alternativeMenuItem)
}

inline fun <T : Any> flipperPointSelect(feature: Feature, mapping: Map<FlipperValue, T>): T {
    return ToggleRouter.select(feature, mapping)
}

inline fun buildFeaturesMap(builderAction: FlipperConfigBuilder.() -> Unit): MutableMap<String, FlipperValue> {
    return FlipperConfigBuilder().apply(builderAction).build()
}
