plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
}

group = "shhashi.practice.i20250126"
version = "0.0.1-SNAPSHOT"

ext {
    set("kotlinVersion", "2.1.0")
    set("ktorVersion", "3.0.2")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.insert-koin:koin-bom:4.0.2"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-annotations:2.0.0-RC1") // v2 がまだリリースされていないため暫定版を利用する。

    implementation(kotlin("reflect"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    implementation("io.ktor:ktor-server-core-jvm:${ext.properties["ktorVersion"]}")
    implementation("io.ktor:ktor-server-netty-jvm:${ext.properties["ktorVersion"]}")
    implementation("io.ktor:ktor-server-config-yaml-jvm:${ext.properties["ktorVersion"]}")
    implementation("io.ktor:ktor-server-test-host-jvm:${ext.properties["ktorVersion"]}")
    implementation("ch.qos.logback:logback-classic:1.5.15")

    implementation("io.ktor:ktor-server-thymeleaf:${ext.properties["ktorVersion"]}")
    implementation("org.thymeleaf:thymeleaf:3.1.3.RELEASE")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
