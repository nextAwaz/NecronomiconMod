# Utilities

## NecUtilsAPI

`elocindev.necronomicon.api.NecUtilsAPI`

```java
// Get looking direction vector (for projectiles/dashes)
Vec3d look = NecUtilsAPI.getLookVec(entity);

// Get entity ID as string
String id = NecUtilsAPI.getEntityId(entity);  // "minecraft:pig"

// Get world time (in ticks)
long time = NecUtilsAPI.getWorldTime(entity);

// Check if a mod is loaded
boolean loaded = NecUtilsAPI.isModLoaded("embeddium");
```

## MathUtils

`elocindev.necronomicon.math.MathUtils`

```java
Vec3d look = MathUtils.getLookingVec(entity);
```

## ColorUtils

`elocindev.necronomicon.util.ColorUtils`

```java
// Interpolate between two colors
int color = ColorUtils.interpolate(0xff0000, 0x0000ff, 0.5);

// Animated interpolation (breathing effect)
int color = ColorUtils.interpolateAnimation(0xff0000, 0x00ff00, ratio, animFactor);

// Gradient slide (bouncing)
Color color = ColorUtils.gradientSlide(progression, Color.RED, Color.BLUE);
```

## JsonFileAPI

`elocindev.necronomicon.api.json.JsonFileAPI`

```java
// Pretty-print a JSON file
JsonFileAPI.setPrettyPrint(Path.of("config.json"));
```

## FileUtils

`elocindev.necronomicon.util.FileUtils`

```java
FileUtils.setPrettyPrint(path);
```
