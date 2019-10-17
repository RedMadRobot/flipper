<img src="images/logo.png"/>

# Flipper

Flipper is a simple and useful tool to deal with feature toggles. It's not some *secret weapon* but rather a set of best practices to work with flipping features in your application, which is shipped as a library.

### Quick start

Add this library to your gradle config
```groovy
implementation 'com.redmadrobot:flipper:1.0.5'
```

Create a class with a description of features
```kotlin
object Features {

    object Feature1 : Feature() {
        override val id = "Feature1"
    }
}
``` 

Create some simplest configuration describing which features are enabled and which features are disabled:
```kotlin
class HardcodedConfig : FlipperConfig {
    private val features = mapOf(
        Features.Feature1.id to true
    )

    override fun featureIsEnabled(feature: Feature): Boolean {
        return features[feature.id] ?: false
    }
}
``` 

Init the library in your **Application** class
```kotlin
ToggleRouter.init(HardcodedConfig(this))
```

Choose the necessary "feature edge" and place a toggle point (flipper point in this library terms).
```kotlin
val feature1Button = (Button) findViewById(R.id.feature1_button)

feature1Button.flipperPoint(Features.Feature1)

...

feature1Button.setOnClickListener { openFeature1Screen() }
```

Run and enjoy!

### Library components

- [`Feature`](https://github.com/RedMadRobot/flipper/blob/master/flipper/src/main/kotlin/com/redmadrobot/flipper/Feature.kt): base class for all your feature objects. You have to provide a unique identifier for each such object.
- [`ToggleRouter`](https://github.com/RedMadRobot/flipper/blob/master/flipper/src/main/kotlin/com/redmadrobot/flipper/ToggleRouter.kt): features orchestrator based on some variant of the configuration   
- [`FlipperConfig`](https://github.com/RedMadRobot/flipper/blob/master/flipper/src/main/kotlin/com/redmadrobot/flipper/config/FlipperConfig.kt): base class for your variant of the feature toggle configuration
- [`flipperPoint`](https://github.com/RedMadRobot/flipper/blob/master/flipper/src/main/kotlin/com/redmadrobot/flipper/FlipperExt.kt): Kotlin extension function which you should place at the edge of your feature

### More information about the "feature edges"
When it comes to Android application, you have only three ways of transition between features. 
- tap on the view (swipe like a special case)
- tap on the menu item
- an external event driven by business logic

In fact, all components of triggering transition action are the edges of the features. So, to prevent transition between features, we need to disable the border component. You can do it using extension functions on `View` and `MenuItem` or top-level function to flipping code blocks.

Some examples
```kotlin
with(feature4_button) {
    setOnClickListener { findNavController().navigate(Feature1FragmentDirections.toFeature4()) }
    flipperPoint(Features.Feature4)
}
```

```kotlin
with(bottom_nav.menu) {
    findItem(R.id.feature1).flipperPoint(Features.Feature1)
    findItem(R.id.feature2).flipperPoint(Features.Feature2)
    findItem(R.id.feature3).flipperPoint(Features.Feature3)
}
```

```kotlin
flipperPoint(Features.Feature1) {
    Log.d("Flipper", "I'll appear only when feature1 is enabled.")
}
```

```kotlin
if(flipperPointIsEnabled(Features.Feature1)) {
    Log.d("Flipper", "Feature1 is enabled.")
} else {
    Log.d("Flipper", "Feature1 is disabled.")
}
```
        
### Further reading
There is an almost "classic" article about feature toggles [Feature Toggles (aka Feature Flags)](https://www.martinfowler.com/articles/feature-toggles.html). You can read it to know more about this library underline concepts.

## Feedback
In case you have faced any bug or have a useful suggestion for improvement of this library, feel free to create an [issue](https://github.com/RedMadRobot/flipper/issues). 

## LICENSE

>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

