package com.redmadrobot.flipper.ext

import android.view.MenuItem
import android.view.View
import com.redmadrobot.flipper.Feature
import com.redmadrobot.flipper.ToggleRouter

inline fun flipperPointAb(feature: Feature, noinline actionA: () -> Unit, noinline actionB: () -> Unit) {
    ToggleRouter.flipAb(feature, actionA, actionB)
}

inline fun flipperPointAb(
    feature: Feature,
    noinline actionA: () -> Unit,
    noinline actionB: () -> Unit,
    noinline predicate: FlipperPredicate
) {
    ToggleRouter.flipAb(feature, actionA, actionB, predicate)
}

inline fun View.flipperPointAb(feature: Feature, alternativeView: View) {
    ToggleRouter.flipAb(feature, this, alternativeView)
}

inline fun View.flipperPointAb(feature: Feature, alternativeView: View, noinline predicate: FlipperPredicate) {
    ToggleRouter.flipAb(feature, this, alternativeView, predicate)
}

inline fun MenuItem.flipperPointAb(feature: Feature, alternativeMenuItem: MenuItem) {
    ToggleRouter.flipAb(feature, this, alternativeMenuItem)
}

inline fun MenuItem.flipperPointAb(
    feature: Feature,
    alternativeMenuItem: MenuItem,
    noinline predicate: FlipperPredicate
) {
    ToggleRouter.flipAb(feature, this, alternativeMenuItem, predicate)
}

inline fun <T> flipperPointAb(feature: Feature, valueA: T, valueB: T): T {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

inline fun <T> flipperPointAb(feature: Feature, noinline valueA: () -> T, noinline valueB: () -> T): T {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

inline fun <T> flipperPointAb(feature: Feature, valueA: T, valueB: T, noinline predicate: FlipperPredicate): T {
    return ToggleRouter.returnAb(feature, valueA, valueB, predicate)
}

inline fun <T> flipperPointAb(
    feature: Feature,
    noinline valueA: () -> T,
    noinline valueB: () -> T,
    noinline predicate: FlipperPredicate
): T {
    return ToggleRouter.returnAb(feature, valueA, valueB, predicate)
}
