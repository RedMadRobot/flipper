package com.redmadrobot.flipper.ext

import android.view.MenuItem
import android.view.View
import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.ToggleRouter
import com.redmadrobot.flipper.config.FlipperValue

typealias FlipperPredicate = (FlipperValue) -> Boolean

inline fun flipperPoint(feature: Feature, noinline action: () -> Unit) {
    ToggleRouter.flip(feature, action)
}

inline fun flipperPoint(feature: Feature, noinline action: () -> Unit, noinline predicate: FlipperPredicate) {
    ToggleRouter.flip(feature, action, predicate)
}

inline fun View.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

inline fun View.flipperPoint(feature: Feature, noinline predicate: FlipperPredicate) {
    ToggleRouter.flip(feature, this, predicate)
}

inline fun MenuItem.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

inline fun MenuItem.flipperPoint(feature: Feature, noinline predicate: FlipperPredicate) {
    ToggleRouter.flip(feature, this, predicate)
}

inline fun <T : Any> flipperPointSelect(feature: Feature, mapping: Map<FlipperValue, T>): T {
    return ToggleRouter.select(feature, mapping)
}
