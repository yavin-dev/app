plugins {
    id("java")
}

repositories {
    mavenCentral()
}

/*
var copyDemo = tasks.register<Copy>("copyDemoConfigs") {
    description = "Copy demo configs directory for packaging"
    from("src/main/resources")
    into("../ws/src/main/resources")
}


tasks.withType<ProcessResources> {
    dependsOn("copyDemoConfigs")
}

tasks.jar {
    archiveBaseName.set("yavin-demo-configs")
}
*/