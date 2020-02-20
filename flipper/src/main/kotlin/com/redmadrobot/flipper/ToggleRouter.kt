package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import com.redmadrobot.flipper.config.FlipperConfig
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.BooleanValue


object ToggleRouter {
    private const val BAD_FLIPPERPOINT_MESSAGE = "It's not a flipper point"

    private lateinit var configuration: FlipperConfig

    fun init(config: FlipperConfig) {
        configuration = config
    }

    @PublishedApi
    internal fun flip(feature: Feature, flipperPoint: Any) {
        when (flipperPoint) {
            is View -> flipView(feature, flipperPoint)
            is MenuItem -> flipMenuItem(feature, flipperPoint)
            is Function0<*> -> flipFunction(feature, flipperPoint)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    @PublishedApi
    internal fun flip(feature: Feature, flipperPoint: Any, predicate: FlipperPredicate) {
        when (flipperPoint) {
            is View -> flipView(feature, flipperPoint, predicate)
            is MenuItem -> flipMenuItem(feature, flipperPoint, predicate)
            is Function0<*> -> flipFunction(feature, flipperPoint, predicate)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    @PublishedApi
    internal fun flipAb(feature: Feature, flipperPoint: Any, alternative: Any) {
        when (flipperPoint) {
            is View -> flipViewAb(feature, flipperPoint, alternative as View)
            is MenuItem -> flipMenuItemAb(feature, flipperPoint, alternative as MenuItem)
            is Function0<*> -> flipFunctionAb(feature, flipperPoint, alternative as Function0<*>)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    @PublishedApi
    internal fun flipAb(feature: Feature, flipperPoint: Any, alternative: Any, predicate: FlipperPredicate) {
        when (flipperPoint) {
            is View -> flipViewAb(feature, flipperPoint, alternative as View, predicate)
            is MenuItem -> flipMenuItemAb(feature, flipperPoint, alternative as MenuItem, predicate)
            is Function0<*> -> flipFunctionAb(feature, flipperPoint, alternative as Function0<*>, predicate)
            else -> throw IllegalArgumentException("$BAD_FLIPPERPOINT_MESSAGE: $flipperPoint")
        }
    }

    @PublishedApi
    internal fun <T> returnAb(feature: Feature, valueA: T, valueB: T): T {
        return if (extractBooleanValue(feature)) valueA else valueB
    }

    @PublishedApi
    internal fun <T> returnAb(feature: Feature, valueA: () -> T, valueB: () -> T): T {
        return if (extractBooleanValue(feature)) valueA() else valueB()
    }

    @PublishedApi
    internal fun <T> returnAb(feature: Feature, valueA: T, valueB: T, predicate: FlipperPredicate): T {
        return if (predicate(configuration.getValue(feature))) valueA else valueB
    }

    @PublishedApi
    internal fun <T> returnAb(feature: Feature, valueA: () -> T, valueB: () -> T, predicate: FlipperPredicate): T {
        return if (predicate(configuration.getValue(feature))) valueA() else valueB()
    }

    @PublishedApi
    internal fun <T : Any> select(feature: Feature, mapping: Map<FlipperValue, T>): T {
        val key = configuration.getValue(feature)
        val value = mapping[key]

        return requireNotNull(value) {
            "Key `$key` is not found. Selection mapping must be exhaustive."
        }
    }

    private fun flipView(feature: Feature, view: View) {
        view.visibility = if (extractBooleanValue(feature)) View.VISIBLE else View.GONE
    }

    private fun flipView(feature: Feature, view: View, predicate: FlipperPredicate) {
        view.visibility = if (predicate(configuration.getValue(feature))) View.VISIBLE else View.GONE
    }

    private fun flipViewAb(feature: Feature, view: View, alternativeView: View) {
        if (extractBooleanValue(feature)) {
            view.visibility = View.VISIBLE
            alternativeView.visibility = View.GONE
        } else {
            view.visibility = View.GONE
            alternativeView.visibility = View.VISIBLE
        }
    }

    private fun flipViewAb(feature: Feature, view: View, alternativeView: View, predicate: FlipperPredicate) {
        if (predicate(configuration.getValue(feature))) {
            view.visibility = View.VISIBLE
            alternativeView.visibility = View.GONE
        } else {
            view.visibility = View.GONE
            alternativeView.visibility = View.VISIBLE
        }
    }

    private fun flipMenuItem(feature: Feature, menuItem: MenuItem) {
        menuItem.isVisible = extractBooleanValue(feature)
    }

    private fun flipMenuItem(feature: Feature, menuItem: MenuItem, predicate: FlipperPredicate) {
        menuItem.isVisible = predicate(configuration.getValue(feature))
    }

    private fun flipMenuItemAb(feature: Feature, menuItem: MenuItem, alternativeMenuItem: MenuItem) {
        if (extractBooleanValue(feature)) {
            menuItem.isVisible = true
            alternativeMenuItem.isVisible = false
        } else {
            menuItem.isVisible = false
            alternativeMenuItem.isVisible = true
        }
    }

    private fun flipMenuItemAb(
        feature: Feature,
        menuItem: MenuItem,
        alternativeMenuItem: MenuItem,
        predicate: FlipperPredicate
    ) {
        if (predicate(configuration.getValue(feature))) {
            menuItem.isVisible = true
            alternativeMenuItem.isVisible = false
        } else {
            menuItem.isVisible = false
            alternativeMenuItem.isVisible = true
        }
    }

    private fun flipFunction(feature: Feature, function: Function0<*>) {
        if (extractBooleanValue(feature)) {
            function()
        }
    }

    private fun flipFunction(feature: Feature, function: Function0<*>, predicate: FlipperPredicate) {
        if (predicate(configuration.getValue(feature))) {
            function()
        }
    }

    private fun flipFunctionAb(feature: Feature, function: Function0<*>, alternativeFunction: Function0<*>) {
        if (extractBooleanValue(feature)) {
            function()
        } else {
            alternativeFunction()
        }
    }

    private fun flipFunctionAb(
        feature: Feature,
        function: Function0<*>,
        alternativeFunction: Function0<*>,
        predicate: FlipperPredicate
    ) {
        if (predicate(configuration.getValue(feature))) {
            function()
        } else {
            alternativeFunction()
        }
    }


    private fun extractBooleanValue(feature: Feature): Boolean {
        val featureValue = configuration.getValue(feature)

        require(featureValue is BooleanValue) {
            "You must set predicate explicitly when using ${featureValue.javaClass.canonicalName}."
        }

        return featureValue.value
    }
}
