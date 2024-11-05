plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    //noinspection UseTomlInstead
    sourceSets {
        commonMain.dependencies {
            implementation(projects.published.module1)
//            implementation("com.samdmitry.published:module1:0.0.1")
        }
        androidMain.dependencies {
//            implementation("com.samdmitry.published:module1:0.0.1")
        }
    }
}

android {
    namespace = "com.samdmitry.publishtest"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
