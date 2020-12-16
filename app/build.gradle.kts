plugins {
    id(Android.applicationPlugin)

    kotlin(Kotlin.androidPlugin)
    kotlin(Kotlin.androidExtensions)

    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        applicationId = Android.DefaultConfig.applicationId

        minSdkVersion(Android.DefaultConfig.minSdk)
        targetSdkVersion(Android.DefaultConfig.targetSdk)

        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName

        testInstrumentationRunner = Android.DefaultConfig.instrumentationRunner
    }

    buildTypes {
        getByName(Android.BuildTypes.release) {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(Android.Proguard.androidOptimizedRules),
                Android.Proguard.projectRules
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":flipper"))
    implementation(project(":flipper-firebase-config"))

    implementation(Kotlin.stdLib)

    implementation(AppDependencies.Core.appCompat)
    implementation(AppDependencies.Core.ktx)
    implementation(AppDependencies.UI.constraintLayout)

    implementation(AppDependencies.Navigation.fragmentKtx)
    implementation(AppDependencies.Navigation.uiKtx)

    implementation(AppDependencies.UI.materialComponents)

    implementation(LibraryDependencies.Firebase.firebaseConfig)

    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.testRunner)
}
