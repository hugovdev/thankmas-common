plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}

group = "me.hugo.thankmas"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven { url = uri("https://nexus.leonardbausenwein.de/repository/maven-public/") }
}

dependencies {
    compileOnly("net.kyori:adventure-api:4.14.0")

    implementation("io.insert-koin:koin-core:3.5.0")
    implementation("io.insert-koin:koin-annotations:1.3.0")
    ksp("io.insert-koin:koin-ksp-compiler:1.3.0")

    implementation(files("libs/miniphrase-core-1.0.0-SNAPSHOT.jar"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
    explicitApi()
}

tasks.compileKotlin {
    kotlinOptions.javaParameters = true
    compilerOptions {
        freeCompilerArgs.add("-Xcontext-receivers")
    }
}