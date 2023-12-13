plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.hugo.translationstest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://nexus.leonardbausenwein.de/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT")
    implementation("de.cubbossa:Translations:3.3.0")
}

tasks.shadowJar {
    minimize()
    relocate ("de.cubbossa.translations", "me.hugo.translationstest.libs.translations")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

tasks.compileKotlin {
    kotlinOptions.javaParameters = true
}