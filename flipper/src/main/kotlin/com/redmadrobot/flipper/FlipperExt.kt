package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

fun flipperPoint(feature: Feature, action: () -> Unit) {
    ToggleRouter.flip(feature, action)
}

fun flipperPointAb(feature: Feature, actionA: () -> Unit, actionB: () -> Unit) {
    ToggleRouter.flipAb(feature, actionA, actionB)
}

@Nullable
fun <T> flipperPointAb(feature: Feature, valueA: T?, valueB: T?): T? {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

fun View.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

fun View.flipperPointAb(feature: Feature, alternativeView: View) {
    ToggleRouter.flipAb(feature, this, alternativeView)
}

fun MenuItem.flipperPoint(feature: Feature) {
    ToggleRouter.flip(feature, this)
}

fun MenuItem.flipperPointAb(feature: Feature, alternativeMenuItem: MenuItem) {
    ToggleRouter.flipAb(feature, this, alternativeMenuItem)
}

@NotNull
fun flipperPointIsEnabled(feature: Feature) = ToggleRouter.featureIsEnabled(feature)