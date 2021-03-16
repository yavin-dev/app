import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jmailen.kotlinter") version "3.3.0"
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
}

repositories {
    maven {
        url = uri("https://oss.jfrog.org/oss-snapshot-local")
        content {
            includeGroup("com.yahoo.navi")
        }
    }
    mavenCentral()
}

dependencies {
    implementation(project(":ui"))
    implementation("com.yahoo.navi", "models", "0.2.0-beta-SNAPSHOT") {
        exclude(group = "com.yahoo.elide", module = "elide-core")
    }
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.yahoo.elide", "elide-spring-boot-starter", "5.0.0-pr32")
    implementation("com.h2database", "h2", "1.3.176")
    implementation("org.hibernate", "hibernate-validator", "6.1.5.Final")
    implementation("io.micrometer","micrometer-core", "1.5.1")
    implementation("org.liquibase", "liquibase-core", "3.8.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("com.jayway.restassured", "rest-assured", "2.9.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

val jarName = "yavin-ws"
tasks.register<Exec>("execJar") {
    dependsOn("bootJar")
    commandLine = listOf("java", "-jar", "${project.buildDir}/libs/${jarName}.jar")
}

tasks.bootJar {
    archiveBaseName.set(jarName)
}
