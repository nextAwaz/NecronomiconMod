plugins {
    id("net.neoforged.gradle") version "6.0.21"
    java
}

version = "1.4.2"
base.archivesName.set("necronomicon-neoforge-26.2")

val rootDir = projectDir.parentFile.parentFile

repositories {
    mavenCentral()
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    implementation("net.neoforged:neoforge:26.2.0.30-beta")
}

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

minecraft {
    runs {
        create("client") {
            workingDirectory(file("run"))
            mods {
                create("necronomicon") { source(sourceSets.main.get()) }
            }
        }
    }
}

val preprocessJava by tasks.registering(Exec::class) {
    commandLine("python3", rootDir.resolve("preprocess.py").path, "forge",
        rootDir.resolve("src/main/java").path,
        layout.buildDirectory.dir("preprocessed/java").get().asFile.path)
}
val preprocessResources by tasks.registering(Exec::class) {
    commandLine("python3", rootDir.resolve("preprocess.py").path, "forge",
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
    filesMatching("META-INF/mods.toml") {
        expand("mod_id" to "necronomicon", "mod_version" to version, "mod_name" to "Necronomicon")
    }
}
