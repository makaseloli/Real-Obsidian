pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}


/// Common ///
include("common")

/// 1.21.1 ///
include("1.21.1-common")
include("1.21.1-neo")
include("1.21.1-fabric")

val ciBuildProjectNames = rootProject.children
    .map { it.name }
    .filterNot { it == "common" || it.endsWith("-common") }

gradle.extensions.extraProperties["ciBuildProjectNames"] = ciBuildProjectNames
