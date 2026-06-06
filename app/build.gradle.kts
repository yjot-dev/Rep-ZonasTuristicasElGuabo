plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yjotdev.zonasturisticaselguabo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.yjotdev.zonasturisticaselguabo"
        minSdk = 24
        targetSdk = 36
        versionCode = 5
        versionName = "1.5"
        testInstrumentationRunner = "com.yjotdev.zonasturisticaselguabo.CustomTestRunner"
    }
    signingConfigs {
        create("release") {
            keyAlias = project.findProperty("APP_KEY_ALIAS") as? String
            keyPassword = project.findProperty("APP_KEY_PASSWORD") as? String
            storePassword = project.findProperty("APP_STORE_PASSWORD") as? String
            storeFile = project.findProperty("APP_STORE_FILE")?.let { rootProject.file(it) }
        }
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            // Key de Google enviada al manifesto
            val mapsApiKey = project.findProperty("MAPS_API_KEY_DEBUG") as? String
                ?: error("La propiedad 'MAPS_API_KEY_DEBUG' no se encontró en custom.properties")
            manifestPlaceholders.putAll(mapOf("MAPS_API_KEY" to mapsApiKey))
        }
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            ndk {
                debugSymbolLevel = "FULL"
            }
            // Key de Google enviada al manifesto
            val mapsApiKey = project.findProperty("MAPS_API_KEY_RELEASE") as? String
                ?: error("La propiedad 'MAPS_API_KEY_RELEASE' no se encontró en custom.properties")
            manifestPlaceholders.putAll(mapOf("MAPS_API_KEY" to mapsApiKey))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            useLegacyPackaging = false
        }
    }
    lint {
        disable += setOf("NotificationPermission")
    }
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Xlint:deprecation")
}

dependencies {
    //IU
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    //Maps
    implementation(libs.android.play.services.maps)
    //Picasso
    implementation(libs.squareup.picasso)
    //Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.activity)
    ksp(libs.dagger.hilt.android.compiler)
    //Test
    testImplementation(libs.junit)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.test.uiautomator)
    kspAndroidTest(libs.dagger.hilt.android.compiler)
}