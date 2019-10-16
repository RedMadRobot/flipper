package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.redmadrobot.flipper.support.TestConfig
import com.redmadrobot.flipper.support.TestFeature
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ToggleRouterTest {
    private lateinit var testConfig: TestConfig

    @Before
    fun setUp() {
        testConfig = TestConfig()
        ToggleRouter.init(testConfig)
    }

    @Test
    fun `when feature is enabled - then view became visible`() {
        // Given
        val view = mock<View>()
        testConfig.enableFeature(true)

        // When
        ToggleRouter.flip(TestFeature, view)

        // Then
        verify(view).visibility = View.VISIBLE
    }

    @Test
    fun `when feature is disabled - then view became gone`() {
        // Given
        val view = mock<View>()
        testConfig.enableFeature(false)

        // When
        ToggleRouter.flip(TestFeature, view)

        // Then
        verify(view).visibility = View.GONE
    }

    @Test
    fun `when feature is enabled - then A-view became visible and B-view became gone`() {
        // Given
        val viewA = mock<View>()
        val viewB = mock<View>()

        // When
        testConfig.enableFeature(true)
        ToggleRouter.flipAb(TestFeature, viewA, viewB)

        // Then
        verify(viewA).visibility = View.VISIBLE
        verify(viewB).visibility = View.GONE
    }

    @Test
    fun `when feature is disabled - then A-view became gone and B-view became visible`() {
        // Given
        val viewA = mock<View>()
        val viewB = mock<View>()

        // When
        testConfig.enableFeature(false)
        ToggleRouter.flipAb(TestFeature, viewA, viewB)

        // Then
        verify(viewA).visibility = View.GONE
        verify(viewB).visibility = View.VISIBLE
    }

    @Test
    fun `when feature is enabled - then menu item became visible`() {
        // Given
        val menuItem = mock<MenuItem>()
        testConfig.enableFeature(true)

        // When
        ToggleRouter.flip(TestFeature, menuItem)

        // Then
        verify(menuItem).isVisible = true
    }

    @Test
    fun `when feature is disabled - then menu item became invisible`() {
        // Given
        val menuItem = mock<MenuItem>()
        testConfig.enableFeature(false)

        // When
        ToggleRouter.flip(TestFeature, menuItem)

        // Then
        verify(menuItem).isVisible = false
    }

    @Test
    fun `when feature is enabled - then A-menu item became visible and B-menu item became invisible`() {
        // Given
        val menuItemA = mock<MenuItem>()
        val menuItemB = mock<MenuItem>()

        // When
        testConfig.enableFeature(true)
        ToggleRouter.flipAb(TestFeature, menuItemA, menuItemB)

        // Then
        verify(menuItemA).isVisible = true
        verify(menuItemB).isVisible = false
    }

    @Test
    fun `when feature is disabled - then A-menu item became invisible and B-menu item became visible`() {
        // Given
        val menuItemA = mock<MenuItem>()
        val menuItemB = mock<MenuItem>()

        // When
        testConfig.enableFeature(false)
        ToggleRouter.flipAb(TestFeature, menuItemA, menuItemB)

        // Then
        verify(menuItemA).isVisible = false
        verify(menuItemB).isVisible = true
    }

    @Test
    fun `when feature is enabled - then execute code block`() {
        // Given
        val block = mock<() -> Unit>()
        testConfig.enableFeature(true)

        // When
        ToggleRouter.flip(TestFeature, block)

        // Then
        verify(block).invoke()
    }

    @Test
    fun `when feature is disabled - then don't execute code block`() {
        // Given
        var workaround = false
        val block = { workaround = true }
        testConfig.enableFeature(false)

        // When
        ToggleRouter.flip(TestFeature, block)

        // Then
        assertThat(workaround).isFalse()
    }

    @Test
    fun `when feature is enabled - then execute A-code block and don't execute B-code block`() {
        // Given
        var workaroundA = false
        var workaroundB = false

        val blockA = { workaroundA = true }
        val blockB = { workaroundB = true }

        testConfig.enableFeature(true)

        // When
        ToggleRouter.flipAb(TestFeature, blockA, blockB)

        // Then
        assertThat(workaroundA).isTrue()
        assertThat(workaroundB).isFalse()
    }

    @Test
    fun `when feature is disabled - then don't execute A-code block and execute B-code block`() {
        // Given
        var workaroundA = false
        var workaroundB = false

        val blockA = { workaroundA = true }
        val blockB = { workaroundB = true }

        testConfig.enableFeature(false)

        // When
        ToggleRouter.flipAb(TestFeature, blockA, blockB)

        // Then
        assertThat(workaroundA).isFalse()
        assertThat(workaroundB).isTrue()
    }

    @Test
    fun `when feature is enabled - then return A-value`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.enableFeature(true)
        val result = ToggleRouter.returnAb(TestFeature, precalculatedValueA, precalculatedValueB)

        assertEquals(precalculatedValueA, result)
    }

    @Test
    fun `when feature is enabled - then return B-value`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.enableFeature(false)
        val result = ToggleRouter.returnAb(TestFeature, precalculatedValueA, precalculatedValueB)

        assertEquals(precalculatedValueB, result)
    }

    @Test
    fun `when feature is disabled - then return false`() {
        // When
        testConfig.enableFeature(false)
        val result = ToggleRouter.featureIsEnabled(TestFeature)

        assertThat(result).isFalse()
    }

    @Test
    fun `when feature is enabled - then return true`() {
        // When
        testConfig.enableFeature(true)
        val result = ToggleRouter.featureIsEnabled(TestFeature)

        assertThat(result).isTrue()
    }
}
