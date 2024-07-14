plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    id("com.google.devtools.ksp")
}

group = "me.hugo.thankmas"
version = "1.0-SNAPSHOT"

dependencies {
    compileOnly("net.kyori:adventure-api:4.17.0")
    compileOnly("net.kyori:adventure-text-minimessage:4.16.0")

    api(libs.bundles.koin)
    api(libs.hikariCP)

    ksp(libs.koin.ksp.compiler)

    api(files("C:/Users/hugov/IdeaProjects/thankmas/common/libs/miniphrase-core-1.0.0-SNAPSHOT.jar"))
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