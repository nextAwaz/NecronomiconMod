plugins {
    id("fabric-loom") version "1.3.10"
    java
}

base.archivesName.set("necronomicon-fabric-1.20.1")

val minecraftVersion = "1.20.1"

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.terraformersmc.com/")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings("net.fabricmc:yarn:$minecraftVersion+build.10:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.22")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.92.0+$minecraftVersion")
    modImplementation("net.fabricmc:fabric-language-kotlin:1.10.17+kotlin.1.9.22")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Preprocessing: strip //#if / //#else / //#endif / //$$
val preprocessJava by tasks.registering(Exec::class) {
    description = "Preprocess Java sources for Fabric"
    commandLine("python3", "preprocess.py", "fabric",
        "src/main/java",
        "build/preprocessed/java")
}

val preprocessResources by tasks.registering(Exec::class) {
    description = "Preprocess resources for Fabric"
    commandLine("python3", "preprocess.py", "fabric",
        "src/main/resources",
        "build/preprocessed/resources")
}

sourceSets {
    main {
        java.setSrcDirs(listOf("build/preprocessed/java"))
        resources.setSrcDirs(listOf("build/preprocessed/resources"))
    }
}

tasks.processResources {
    dependsOn(preprocessResources)
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand(
            "mod_id" to "necronomicon",
            "mod_version" to "1.4.2",
            "mod_name" to "Necronomicon"
        )
    }
}

tasks.named("compileJava") { dependsOn(preprocessJava) }
