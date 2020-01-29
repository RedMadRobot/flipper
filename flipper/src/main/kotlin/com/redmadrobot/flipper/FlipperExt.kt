package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View

fun flipperPoint(feature: Feature, action: () -> Unit) {
    ToggleRouter.flip(feature, action)
}

fun flipperPointAb(feature: Feature, actionA: () -> Unit, actionB: () -> Unit) {
    ToggleRouter.flipAb(feature, actionA, actionB)
}

fun <T> flipperPointAb(feature: Feature, valueA: T, valueB: T): T {
    return ToggleRouter.returnAb(feature, valueA, valueB)
}

fun flipperPointValue(feature: Feature) = ToggleRouter.returnFeatureValue(feature)

fun flipperPointStringValue(feature: Feature) = ToggleRouter.returnStringFeatureValue(feature)

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

fun flipperPointIsEnabled(feature: Feature): Boolean = ToggleRouter.featureIsEnabled(feature)
