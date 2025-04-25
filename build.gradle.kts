plugins {
    kotlin("jvm") version "1.9.25"
    `maven-publish`
}


group = "com.report.utils"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")

    implementation("org.postgresql:postgresql:42.2.5")
    implementation("javax.persistence:javax.persistence-api:2.2")

    implementation("software.amazon.awssdk:s3:2.25.3")
    implementation("software.amazon.awssdk:sqs:2.25.3")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.report.utils"
            artifactId = "report-utils"
            version = "1.0-SNAPSHOT"
        }
    }
}
