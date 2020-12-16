plugins {
    id(Android.libraryPlugin)
    kotlin(Kotlin.androidPlugin)
}

apply(from = "../gradle/publish.gradle")

android {
    compileSdkVersion(Android.compileSdk)

    defaultConfig {
        minSdkVersion(Android.DefaultConfig.minSdk)
        targetSdkVersion(Android.DefaultConfig.targetSdk)
        versionCode = Android.DefaultConfig.versionCode
        versionName = Android.DefaultConfig.versionName

        testInstrumentationRunner = Android.DefaultConfig.instrumentationRunner

        consumerProguardFile("consumer-rules.pro")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
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
        jvmTarget = "1.8"
    }

    for (sourceSet in sourceSets) {
        sourceSet.java.srcDirs("src/${sourceSet.name}/kotlin")
    }
}

dependencies {
    implementation(Kotlin.stdLib)

    implementation(project(":flipper"))

    compileOnly(LibraryDependencies.Firebase.firebaseConfig)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.assertjCore)
    testImplementation(TestDependencies.mockitoCore)
    testImplementation(TestDependencies.mockitoKotlin)
}

