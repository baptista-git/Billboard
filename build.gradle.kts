import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "pt.baptista.kotlin.training"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Dependencies for using MongoDB sync driver and its LOG
    implementation("org.litote.kmongo:kmongo:4.3.0")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}