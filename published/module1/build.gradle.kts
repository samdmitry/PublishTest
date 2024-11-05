import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
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
            baseName = "module1"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.published.module2)
        }
    }
}

android {
    namespace = "com.samdmitry.module1"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val localProperties = Properties()
rootDir.resolve("local.properties")
    .takeIf { it.exists() }
    ?.inputStream()
    ?.use { localProperties.load(it) }

publishing.publications
    .withType<MavenPublication>()
    .configureEach {
        groupId = "com.samdmitry.published"
        version = "0.0.1"
    }

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/samdmitry/PublishTest")
            credentials {
                username =
                    localProperties.getProperty("github.user") ?: System.getenv("GITHUB_ACTOR")
                password =
                    localProperties.getProperty("github.token") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
