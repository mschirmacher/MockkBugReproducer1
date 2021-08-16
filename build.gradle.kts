import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
}

val kotlinVersion = "1.5.21"
val springVersion = "2.5.3"
val junitVersion = "5.7.2"
val assertkVersion = "0.20"
val mockkVersion = "1.12.0"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springVersion") {
            bomProperty("kotlin.version", kotlinVersion)
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
    }
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:$assertkVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter:$junitVersion")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
