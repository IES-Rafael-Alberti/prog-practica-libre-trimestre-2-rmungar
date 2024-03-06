plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.practicatrim2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("com.google.code.gson:gson:2.8.8")
    implementation("com.github.ajalt.mordant:mordant:2.3.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}