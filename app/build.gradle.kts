plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.github.iverlein.zanshin"
    compileSdk = 37

    defaultConfig {
        applicationId = "io.github.iverlein.zanshin"
        minSdk = 26
        targetSdk = 37
        versionCode = 7
        versionName = "2.2"
    }

    buildTypes {
        // Debug builds install beside the F-Droid release: own package, own
        // name on the launcher ("Zanshin beta", src/debug/res), own settings.
        debug {
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    // F-Droid rejects the dependency list AGP embeds encrypted with Google's key.
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
            // TranslationsTest reads the catalog and the store listing from outside the module.
            it.inputs.dir(rootProject.file("core/src/main/resources/texts"))
            it.inputs.dir(rootProject.file("fastlane/metadata/android"))
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.launcher)
}
