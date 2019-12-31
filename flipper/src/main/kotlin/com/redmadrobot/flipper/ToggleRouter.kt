package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import com.redmadrobot.flipper.config.FlipperConfig


object ToggleRouter {
    private const val BAD_FLIPPERPOINT_MESSAGE = "It's not a flipper point"

    private lateinit var configuration: FlipperConfig

    fun init(config: FlipperConfig) {
        configuration = config
    }

    internal fun flip(feature: Feature, flipperPoint: Any) {
        when (flipperPoint) {
            is View -> flipView(feature, flipperPoint)
            is MenuItem -> flipMenuItem(feature, flipperPoint)
            is Function0<*> -> flipFunction(feature, flipperPoint)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    internal fun flipAb(feature: Feature, flipperPoint: Any, alternative: Any) {
        when (flipperPoint) {
            is View -> flipViewAb(feature, flipperPoint, alternative as View)
            is MenuItem -> flipMenuItemAb(feature, flipperPoint, alternative as MenuItem)
            is Function0<*> -> flipFunctionAb(feature, flipperPoint, alternative as Function0<*>)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    internal fun <T> returnAb(feature: Feature, valueA: T, valueB: T): T {
        return if (configuration.featureIsEnabled(feature)) valueA else valueB
    }

    internal fun featureIsEnabled(feature: Feature): Boolean = configuration.featureIsEnabled(feature)

    private fun flipView(feature: Feature, view: View) {
        view.visibility = if (configuration.featureIsEnabled(feature)) View.VISIBLE else View.GONE
    }

    private fun flipViewAb(feature: Feature, view: View, alternativeView: View) {
        if (configuration.featureIsEnabled(feature)) {
            view.visibility = View.VISIBLE
            alternativeView.visibility = View.GONE
        } else {
            view.visibility = View.GONE
            alternativeView.visibility = View.VISIBLE
        }
    }

    private fun flipMenuItem(feature: Feature, menuItem: MenuItem) {
        menuItem.isVisible = configuration.featureIsEnabled(feature)
    }

    private fun flipMenuItemAb(feature: Feature, menuItem: MenuItem, alternativeMenuItem: MenuItem) {
        if (configuration.featureIsEnabled(feature)) {
            menuItem.isVisible = true
            alternativeMenuItem.isVisible = false
        } else {
            menuItem.isVisible = false
            alternativeMenuItem.isVisible = true
        }
    }

    private fun flipFunction(feature: Feature, function: Function0<*>) {
        if (configuration.featureIsEnabled(feature)) {
            function()
        }
    }

    private fun flipFunctionAb(feature: Feature, function: Function0<*>, alternativeFunction: Function0<*>) {
        if (configuration.featureIsEnabled(feature)) {
            function()
        } else {
            alternativeFunction()
        }
    }
}
