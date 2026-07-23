plugins {
    id("fabric-loom") version "1.17.0-alpha.4"
    java
}

version = "1.4.2"
base.archivesName.set("necronomicon-fabric-1.20.1")

val rootDir = projectDir.parentFile.parentFile

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.20.1")
    mappings("net.fabricmc:yarn:1.20.1+build.10:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.22")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+1.20.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val preprocessJava = tasks.register<Exec>("preprocessJava") {
    commandLine("python3", rootDir.resolve("preprocess.py").path, "fabric",
        rootDir.resolve("src/main/java").path,
        layout.buildDirectory.dir("preprocessed/java").get().asFile.path)
}
val preprocessResources = tasks.register<Exec>("preprocessResources") {
    commandLine("python3", rootDir.resolve("preprocess.py").path, "fabric",
        rootDir.resolve("src/main/resources").path,
        layout.buildDirectory.dir("preprocessed/resources").get().asFile.path)
}
sourceSets {
    main {
        java.setSrcDirs(listOf(layout.buildDirectory.dir("preprocessed/java")))
        resources.setSrcDirs(listOf(layout.buildDirectory.dir("preprocessed/resources")))
    }
}
tasks.named("compileJava") { dependsOn(preprocessJava) }
tasks.processResources {
    dependsOn(preprocessResources)
    filesMatching("fabric.mod.json") {
        expand("mod_id" to "necronomicon", "mod_version" to version, "mod_name" to "Necronomicon")
    }
}
