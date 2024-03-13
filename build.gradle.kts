plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.practicatrim2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation("junit:junit:5.8.1")
    implementation("com.github.ajalt.mordant:mordant:2.3.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}