pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "fabric-loom" || requested.id.id == "net.fabricmc.fabric-loom") {
                useModule("net.fabricmc:fabric-loom:${requested.version}")
            }
        }
    }
}

rootProject.name = "necronomicon-fabric-1201"
