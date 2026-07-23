# Necronomicon API

A personal utility library for Minecraft modding — config, datagen, NBT, text animations, and more.

[中文版](docs/README.zh.md)

**License:** Mozilla Public License 2.0 (MPL-2.0)

---

## Supported Platforms

| Platform | Versions | Status |
|----------|----------|--------|
| **Fabric** | 1.20.1 | ✅ Active |
| **Fabric** | 26.2 | ✅ Active (Mojang mappings) |
| **NeoForge** | 26.2 | ✅ Active |
| **Forge** | 1.20.1+ | ❌ **Deprecated** — see note below |

### Forge Deprecation Notice

Forge support has been dropped due to ForgeGradle build tool incompatibilities:

- **ForgeGradle 6.x** (the latest) relies on internal Gradle APIs and **does not work with Gradle 8.6+** nor **JDK 21+**
- ForgeGradle 7.x has tags on GitHub but **has never been published to any Maven** (as of July 2026)
- The MCP mapping toolchain used by ForgeGradle fails to generate mapped artifacts under JDK 21

**Migration path:** NeoForge forked from Forge at MC 1.20.1 and offers full API compatibility for that version. NeoGradle actively supports Gradle 9.x and JDK 25+. Migrate to NeoForge for current and future versions.

---

## Building

> Requires Gradle 9.5.1+ (wrapper included) and JDK 25 for MC 26.2 / JDK 17 for MC 1.20.1.

```bash
# Fabric 1.20.1
./gradlew -p builds/fabric-1201 build

# Fabric 26.2
./gradlew -p builds/fabric-262 build

# NeoForge 26.2
./gradlew -p builds/neoforge-262 build
```

JARs are output to `build/libs/` inside each build directory.

---

## Features

🛠️ **Config API** — JSON5-based config system for both loaders.

🔄 **Datagen API** — Model and data generation utilities (Fabric).

🌍 **Resource Pack API** — Built-in resource pack registration.

🔧 **NBT API** — Cross-loader entity NBT utilities with cooldown support.

📝 **Text API** — Animated text effects: rainbow, breathing, and color gradients.

🔢 **Math & Color Utils** — Looking vectors, color interpolation, gradient helpers.

---

## Using Necronomicon in your project

### CurseMaven (Fabric)

```kotlin
repositories {
    maven { url = "https://cursemaven.com" }
}

dependencies {
    modImplementation("curse.maven:necronomicon-586157:${fileid}")
}
```

---

## API Documentation

See [docs/](docs/) for detailed API reference.

---

## Feature Comparison

| Feature           | Fabric | Forge (Legacy) |
|-------------------|--------|----------------|
| v1/config         | ✅ Yes | ✅ Yes |
| v1/datagen        | ✅ Yes | ⚠️ Partial |
| v1/resource       | ✅ Yes | ✅ Yes |
| v1/nbt            | ✅ Yes | ✅ Yes |
| v1/text           | ✅ Yes | ✅ Yes |
| v1/events         | WIP    | WIP   |
| utils             | ✅ Yes | ✅ Yes |
| json              | ✅ Yes | ✅ Yes |
