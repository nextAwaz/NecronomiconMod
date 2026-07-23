# Resource API

`elocindev.necronomicon.api.resource.v1.ResourceBuilderAPI`

Register built-in resource packs that ship with your mod.

## Fabric

```java
ResourceBuilderAPI.registerBuiltinPack(
    FabricLoader.getInstance(),
    "mymod",
    "extra_pack",
    Text.of("Extra textures"),
    true,   // enabled by default
    false   // not fixed
);
```

## Forge (Legacy)

```java
ResourceBuilderAPI.registerBuiltinPack(
    "mymod",
    Path.of("resourcepacks/extra"),
    Component.literal("Extra Pack"),
    true,
    Component.literal("Extra textures"),
    PackType.CLIENT_RESOURCES,
    Pack.Position.TOP,
    false
);
```
