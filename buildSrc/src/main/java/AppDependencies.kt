object AppDependencies {
    object Core {
        private const val ktxVersion = "1.3.2"
        private const val appCompatVersion = "1.2.0"

        const val ktx = "androidx.core:core-ktx:$ktxVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    }

    object UI {
        private const val constraintLayoutVersion = "2.0.4"
        private const val materialComponentsVersion = "1.3.0-alpha04"

        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
        const val materialComponents = "com.google.android.material:material:$materialComponentsVersion"
    }

    object Navigation {
        private const val version = "2.3.1"

        const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        const val applicationPlugin = "androidx.navigation.safeargs.kotlin"

        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
    }
}
