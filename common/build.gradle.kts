plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.slf4j.api)
}

java.toolchain {
    languageVersion = JavaLanguageVersion.of(17)
}
