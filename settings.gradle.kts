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

fun includeMc(minecraftVersion: String, kind: String) {
    val projectName = "$minecraftVersion-$kind"
    include(projectName)
    project(":$projectName").projectDir = file("$minecraftVersion/$kind")
}

/// 26.2 ///
includeMc("26.2", "common")
includeMc("26.2", "fabric")
includeMc("26.2", "neo")

val ciBuildProjectNames = rootProject.children
    .map { it.name }
    .filterNot { it == "common" || it.endsWith("-common") }

gradle.extensions.extraProperties["ciBuildProjectNames"] = ciBuildProjectNames
