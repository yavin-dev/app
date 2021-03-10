import com.github.gradle.node.npm.task.NpmTask

val environment: String by project
val buildEnv = if (!project.hasProperty("environment")) "development" else environment

plugins {
  java
  id("com.github.node-gradle.node") version "3.0.1"
}

node {
  version.set("12.16.0")
  distBaseUrl.set("https://nodejs.org/dist")
  download.set(true)
}

val lintTask = tasks.register<NpmTask>("lintUI") {
  description = "Lint Yavin UI"
  inputs.dir("node_modules")
  inputs.dir("app")
  inputs.file("package.json")

  dependsOn(tasks.npmInstall)
  args.set(listOf("run", "lint"))
}

tasks.withType<ProcessResources> {
  dependsOn("copyUI")
}

val buildTask = tasks.register<NpmTask>("buildUI") {
  description = "Build Yavin UI"
  inputs.dir("node_modules")
  inputs.dir("app")
  inputs.dir("public")
  inputs.dir("config")
  inputs.file("package.json")
  outputs.dir("dist")

  dependsOn(tasks.npmInstall)
  environment.set(mapOf("DISABLE_MOCKS" to "true", "EMBER_ENV" to buildEnv))
  args.set(listOf("run", "build"))
}

var copyTask = tasks.register<Copy>("copyUI") {
  description = "Copy Yavin UI assets into the correct directory for packaging"
  from(buildTask)
  into("$buildDir/resources/main/META-INF/resources/ui")
}

val testTask = tasks.register<NpmTask>("testUI") {
  description = "Test Yavin UI"
  inputs.dir("app")
  inputs.dir("config")
  inputs.dir("tests")
  inputs.dir("node_modules")

  dependsOn(tasks.npmInstall, lintTask)
  args.set(listOf("test"))
}

val cleanTask = tasks.register<Delete>("cleanUI") {
  description = "Clean Yavin UI"
  delete("dist")
}

sourceSets {
  java {
    main {
      resources {
        // This makes the processResources task automatically depend on the buildUI task
        srcDir(copyTask)
      }
    }
  }
}

tasks.test {
  dependsOn(lintTask, testTask)
}

tasks.clean {
  dependsOn(cleanTask)
}

tasks.jar {
  archiveBaseName.set("yavin-ui")
}
