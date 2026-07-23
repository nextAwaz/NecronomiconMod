# Necronomicon API

Minecraft 模组开发的个人工具库——配置、数据生成、NBT、文本动画等。

[English](../README.md)

**许可证:** Mozilla Public License 2.0 (MPL-2.0)

---

## 支持平台

| 平台 | 版本 | 状态 |
|------|------|------|
| **Fabric** | 1.20.1 | ✅ 活跃 |
| **Fabric** | 26.2 | ✅ 活跃（Mojang 映射） |
| **NeoForge** | 26.2 | ✅ 活跃 |
| **Forge** | 1.20.1+ | ❌ **已弃用** |

### Forge 弃用说明

Forge 支持已被移除，原因为 ForgeGradle 构建工具的不兼容问题：

- **ForgeGradle 6.x**（最新版）依赖 Gradle 内部 API，**无法在 Gradle 8.6+** 以及 **JDK 21+** 上运行
- ForgeGradle 7.x 仅有 GitHub 标签，**从未发布到任何 Maven 仓库**（截至 2026 年 7 月）
- ForgeGradle 使用的 MCP 映射工具链在 JDK 21 下无法生成映射产物

**迁移路径:** NeoForge 从 Forge 在 MC 1.20.1 时分支，对该版本的 API 完全兼容。NeoGradle 积极支持 Gradle 9.x 和 JDK 25+。建议迁移到 NeoForge。

---

## 构建

> 需要 Gradle 9.5.1+（已包含 wrapper）以及对应版本的 JDK。

```bash
# Fabric 1.20.1
./gradlew -p builds/fabric-1201 build

# Fabric 26.2
./gradlew -p builds/fabric-262 build

# NeoForge 26.2
./gradlew -p builds/neoforge-262 build
```

JAR 产物在每个构建目录的 `build/libs/` 下。

---

## 功能特性

🛠️ **配置 API** — 基于 JSON5 的配置系统，双加载器通用。

🔄 **数据生成 API** — 模型和数据生成工具（Fabric）。

🌍 **资源包 API** — 内置资源包注册。

🔧 **NBT API** — 跨加载器实体 NBT 工具，含冷却系统。

📝 **文本 API** — 动态文本效果：彩虹、呼吸渐变、颜色渐变等。

🔢 **数学与颜色工具** — 视线向量计算、颜色插值、渐变辅助。

---

## 在你的项目中使用 Necronomicon

### CurseMaven（Fabric）

```kotlin
repositories {
    maven { url = "https://cursemaven.com" }
}

dependencies {
    modImplementation("curse.maven:necronomicon-586157:${fileid}")
}
```

---

## API 文档

参见 [docs/](.) 目录下的详细 API 参考。

---

## 功能对比

| 功能              | Fabric | Forge（旧版） |
|-------------------|--------|--------------|
| v1/config         | ✅ 是  | ✅ 是        |
| v1/datagen        | ✅ 是  | ⚠️ 部分     |
| v1/resource       | ✅ 是  | ✅ 是        |
| v1/nbt            | ✅ 是  | ✅ 是        |
| v1/text           | ✅ 是  | ✅ 是        |
| v1/events         | WIP    | WIP         |
| utils             | ✅ 是  | ✅ 是        |
| json              | ✅ 是  | ✅ 是        |
