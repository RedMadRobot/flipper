package com.redmadrobot.flipper

import android.view.MenuItem
import android.view.View
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.redmadrobot.flipper.config.FlipperValue
import com.redmadrobot.flipper.config.FlipperValue.LongValue
import com.redmadrobot.flipper.config.FlipperValue.StringValue
import com.redmadrobot.flipper.support.TestConfig
import com.redmadrobot.flipper.support.TestFeature
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun `when feature value satisfies the condition - then view became visible`() {
        // Given
        val view = mock<View>()
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, view) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        verify(view).visibility = View.VISIBLE
    }

    @Test
    fun `when feature value doesn't satisfy the condition - then view became visible`() {
        // Given
        val view = mock<View>()
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, view) { v -> (v as StringValue).value == "stub" }

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

    @Test()
    fun `when feature value satisfies the condition - then A-view became visible and B-view became gone`() {
        // Given
        val viewA = mock<View>()
        val viewB = mock<View>()

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        ToggleRouter.flipAb(TestFeature, viewA, viewB) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        verify(viewA).visibility = View.VISIBLE
        verify(viewB).visibility = View.GONE
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then A-view gone visible and B-view became visible`() {
        // Given
        val viewA = mock<View>()
        val viewB = mock<View>()

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        ToggleRouter.flipAb(TestFeature, viewA, viewB) { v -> (v as StringValue).value == "stub" }

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

    @Test()
    fun `when feature value satisfies the condition - then menu item became visible`() {
        // Given
        val menuItem = mock<MenuItem>()
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, menuItem) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        verify(menuItem).isVisible = true
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then menu item became invisible`() {
        // Given
        val menuItem = mock<MenuItem>()
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, menuItem) { v -> (v as StringValue).value == "stub" }

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

    @Test()
    fun `when feature value satisfies the condition - then A-menu item became visible and B-menu item became invisible`() {
        // Given
        val menuItemA = mock<MenuItem>()
        val menuItemB = mock<MenuItem>()

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        ToggleRouter.flipAb(TestFeature, menuItemA, menuItemB) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        verify(menuItemA).isVisible = true
        verify(menuItemB).isVisible = false
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then A-menu item became invisible and B-menu item became visible`() {
        // Given
        val menuItemA = mock<MenuItem>()
        val menuItemB = mock<MenuItem>()

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        ToggleRouter.flipAb(TestFeature, menuItemA, menuItemB) { v -> (v as StringValue).value == "stub" }

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

    @Test()
    fun `when feature value satisfies the condition - then execute code block`() {
        // Given
        val block = mock<() -> Unit>()
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, block) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        verify(block).invoke()
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then don't execute code block`() {
        // Given
        var workaround = false
        val block = { workaround = true }
        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flip(TestFeature, block) { v -> (v as StringValue).value == "stub" }

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

    @Test()
    fun `when feature value satisfies the condition - then execute A-code block and don't execute B-code block`() {
        // Given
        var workaroundA = false
        var workaroundB = false

        val blockA = { workaroundA = true }
        val blockB = { workaroundB = true }

        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flipAb(TestFeature, blockA, blockB) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        // Then
        assertThat(workaroundA).isTrue()
        assertThat(workaroundB).isFalse()
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then don't execute A-code block and execute B-code block`() {
        // Given
        var workaroundA = false
        var workaroundB = false

        val blockA = { workaroundA = true }
        val blockB = { workaroundB = true }

        testConfig.setValue(StringValue("Peek-a-Boo!"))

        // When
        ToggleRouter.flipAb(TestFeature, blockA, blockB) { v -> (v as StringValue).value == "stub" }

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
    fun `when feature is disabled - then return B-value`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.enableFeature(false)
        val result = ToggleRouter.returnAb(TestFeature, precalculatedValueA, precalculatedValueB)

        assertEquals(precalculatedValueB, result)
    }

    @Test()
    fun `when feature value satisfies the condition - then return A-value`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        val result = ToggleRouter.returnAb(
            TestFeature,
            precalculatedValueA,
            precalculatedValueB
        ) { v -> (v as StringValue).value == "Peek-a-Boo!" }

        assertEquals(precalculatedValueA, result)
    }

    @Test()
    fun `when feature value doesn't satisfy the condition - then return B-value`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.setValue(StringValue("Peek-a-Boo!"))
        val result = ToggleRouter.returnAb(
            TestFeature,
            precalculatedValueA,
            precalculatedValueB
        ) { v -> (v as StringValue).value == "stub" }

        assertEquals(precalculatedValueB, result)
    }

    @Test
    fun `when use returnAb - and one of values is nullable - should return nullable result`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = null

        // When
        val result = ToggleRouter.returnAb(TestFeature, precalculatedValueA, precalculatedValueB)

        assertNullable(result)
    }

    @Test
    fun `when use returnAb with boolean as a feature value - and both values are non-nullable - should return non-nullable result`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        val result = ToggleRouter.returnAb(TestFeature, precalculatedValueA, precalculatedValueB)

        assertNotNullable(result)
    }

    @Test
    fun `when use returnAb with boolean as a feature value - and both values are lazy - should return non-nullable result`() {
        // Given
        val lazycalculatedValueA = { "valueA" }
        val lazycalculatedValueB = { "valueB" }

        // When
        val result = ToggleRouter.returnAb(TestFeature, lazycalculatedValueA, lazycalculatedValueB)

        assertNotNullable(result)
    }

    @Test
    fun `when use returnAb with not boolean as a feature value - and both values are non-nullable - should return non-nullable result`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = "valueB"

        // When
        testConfig.setValue(StringValue("foo"))
        val result = ToggleRouter.returnAb(
            TestFeature,
            precalculatedValueA,
            precalculatedValueB,
            { x -> (x as StringValue).value == "bzz" }
        )

        assertNotNullable(result)
    }

    @Test
    fun `when use returnAb with not boolean as a feature value - and one of values is nullable - should return nullable result`() {
        // Given
        val precalculatedValueA = "valueA"
        val precalculatedValueB = null

        // When
        testConfig.setValue(LongValue(1L))
        val result = ToggleRouter.returnAb(
            TestFeature,
            precalculatedValueA,
            precalculatedValueB,
            { x -> (x as LongValue).value == 1L }
        )

        assertNullable(result)
    }

    @Test
    fun `when use returnAb with not boolean as a feature value - and both values are lazy - should return non-nullable result`() {
        // Given
        val lazycalculatedValueA = { "valueA" }
        val lazycalculatedValueB = { "valueB" }

        // When
        testConfig.setValue(StringValue("foo"))
        val result = ToggleRouter.returnAb(
            TestFeature,
            lazycalculatedValueA,
            lazycalculatedValueB,
            { x -> (x as StringValue).value == "bzz" }
        )

        assertNotNullable(result)
    }

    @Test
    fun `when feature can have several states - should return value by mapping`() {
        // Given
        val mapping = mapOf<FlipperValue, String>(
            StringValue("foo") to "bar",
            StringValue("bar") to "baz"
        )

        // When
        testConfig.setValue(StringValue("foo"))
        val result = ToggleRouter.select(TestFeature, mapping)

        assertEquals("bar", result)
    }

    @Suppress("UNUSED_PARAMETER") // Parameter needed for type inference
    private inline fun <reified T> assertNullable(t: T) {
        assertTrue(null is T)
    }

    @Suppress("UNUSED_PARAMETER") // Parameter needed for type inference
    private inline fun <reified T> assertNotNullable(t: T) {
        assertTrue(null !is T)
    }
}
