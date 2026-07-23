plugins {
    id("net.fabricmc.fabric-loom") version "1.18.0-alpha.9"
    java
}

version = "1.4.2"
base.archivesName.set("necronomicon-fabric-26.2")

val rootDir = projectDir.parentFile.parentFile

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft("com.mojang:minecraft:26.2")
    implementation("net.fabricmc:fabric-loader:0.19.3")
    implementation("net.fabricmc.fabric-api:fabric-api:0.150.1+26.2")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
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
