import java.util.Properties

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/samdmitry/PublishTest")
            credentials {
                val localProperties = Properties()
                file(("local.properties"))
                    .takeIf { it.exists() }
                    ?.inputStream()
                    ?.use { localProperties.load(it) }
                username = localProperties.getProperty("github.user")
                password = localProperties.getProperty("github.token")
            }
        }
    }
}

rootProject.name = "PublishTest"
include(":androidApp")
include(":shared")
include(":published:module1")
include(":published:module2")
