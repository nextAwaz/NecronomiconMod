# Config API

`elocindev.necronomicon.api.config.v1.NecConfigAPI`

JSON5-based config system supporting both Fabric and Forge.

## Quick Start

```java
// 1. Create config class
public class MyConfig {
    @NecConfig
    public static MyConfig INSTANCE;

    public static String getFile() {
        return NecConfigAPI.getFile("mymod.json5");
    }

    @Comment("Enable debug logging")
    public boolean debug = false;

    @NestedConfig
    public Colors colors = new Colors();

    public static class Colors {
        public int red = 255;
        public int green = 128;
        public int blue = 64;
    }
}

// 2. Register in init
NecConfigAPI.registerConfig(MyConfig.class);

// 3. Use anywhere
if (MyConfig.INSTANCE.debug) { ... }
```

## Annotations

| Annotation | Target | Description |
|-----------|--------|-------------|
| `@NecConfig` | Field | Marks the static instance field |
| `@Comment` | Field | Adds a `//` comment above the field in JSON5 |
| `@Comments` | Field | Repeatable container for multiple `@Comment` |
| `@NestedConfig` | Field | Nested config class (auto-initialized) |
