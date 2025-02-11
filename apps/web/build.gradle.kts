plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("io.ktor.plugin") version "3.0.2"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
}

group = "shhashi.practice.i20250126"
version = "0.0.1-SNAPSHOT"

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
    implementation("io.insert-koin:koin-ktor")
    implementation("io.insert-koin:koin-annotations:2.0.0-RC1") // v2 がまだリリースされていないため暫定版を利用する。
    ksp("io.insert-koin:koin-ksp-compiler:2.0.0-RC1")

    implementation(kotlin("reflect"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-config-yaml")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-test-host-jvm")
    implementation("ch.qos.logback:logback-classic:1.5.15")

    implementation("org.jetbrains.exposed:exposed-core:0.59.0")

    implementation("io.ktor:ktor-server-thymeleaf")
    implementation("org.thymeleaf:thymeleaf:3.1.3.RELEASE")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.2")
}

sourceSets.main {
    java.srcDirs("build/generated/ksp/main/kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
