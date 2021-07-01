import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jmailen.kotlinter") version "3.3.0"
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    `java-library`
    `maven-publish`
    signing
}

repositories {
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        content {
            includeGroup("dev.yavin")
        }
    }
    mavenCentral()
}

dependencies {
    implementation(project(":ui"))
    //Comment this line to disable the demo config
    implementation("dev.yavin","demo-config","0.10")
    implementation("dev.yavin", "models", "0.2.0-beta-SNAPSHOT") {
        exclude(group = "com.yahoo.elide", module = "elide-core")
    }
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.yahoo.elide", "elide-spring-boot-starter", "5.0.2")
    implementation("com.h2database", "h2", "1.3.176")
    // drivers for models
    runtimeOnly("org.apache.hive","hive-jdbc","3.1.2"){
        exclude(group="org.apache.logging.log4j", module = "log4j-slf4j-impl")
        exclude(group="org.eclipse.jetty", module="jetty-runner")
    }
    runtimeOnly("com.facebook.presto","presto-jdbc","0.247")
    runtimeOnly("org.apache.calcite.avatica","avatica-core","1.17.0")
    runtimeOnly("mysql","mysql-connector-java","8.0.23")
    runtimeOnly("org.postgresql","postgresql","42.2.19")
    // drivers for models    
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
    // set JVM arguments for the test JVM(s)
    jvmArgs ("-Xmx2048m")
}


val jarName = "yavin-app"
tasks.register<Exec>("execJar") {
    dependsOn("bootJar")
    commandLine = listOf("java", "-jar", "${project.buildDir}/libs/${jarName}-${version}.jar")
}


tasks.bootJar {
    archiveBaseName.set(jarName)
}

// Publishing to Maven central
group = "dev.yavin"
version = "1.0.1-beta.5"
val yavin_app_artifact = artifacts.add("archives",layout.buildDirectory.file("libs/".plus(jarName).plus("-").plus(version).plus(".jar")))

// Maven env vars. These are set via screwdriver.yaml
var mvn_dev_name = System.getenv("MVN_DEV_NAME")
var mvn_dev_email = System.getenv("MVN_DEV_EMAIL")

var mvn_scm_conn = System.getenv("MVN_SCM_CONN")
var mvn_scm_dev_conn = System.getenv("MVN_SCM_DEV_CONN")
var mvn_scm_url = System.getenv("MVN_SCM_URL")

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact (yavin_app_artifact)
            artifactId = jarName
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("yavin app")
                description.set("UI and Webservice for the yavin")
                url.set("http://yavin.dev")
                licenses {
                    license {
                        name.set("MIT")
                    }
                }
                developers {
                    developer {
                        id.set("yavin-dev")
                        name.set(mvn_dev_name)
                        email.set(mvn_dev_email)
                    }
                }
                scm {
                    connection.set(mvn_scm_conn)
                    developerConnection.set(mvn_scm_dev_conn)
                    url.set(mvn_scm_url)
                }
            }
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials {
                username = System.getenv("OSSRH_USER") as String?
                password = System.getenv("OSSRH_TOKEN") as String?
            }
        }
    }
}

allprojects {
    extra["signing.password"] = System.getenv("GPG_SIGN_PASS")
}

signing {
    sign(publishing.publications["mavenJava"])
}
