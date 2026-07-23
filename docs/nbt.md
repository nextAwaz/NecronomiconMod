# NBT API

`elocindev.necronomicon.api.nbt.v1.NecNbtAPI`

Cross-loader entity NBT utilities.

## Entity NBT

```java
// Write values
NecNbtAPI.Entity.putLong(entity, "my_key", 100L);
NecNbtAPI.Entity.putString(entity, "name", "value");
NecNbtAPI.Entity.putIntArray(entity, "data", new int[]{1,2,3});

// Read values
long value = NecNbtAPI.Entity.getLong(entity, "my_key");
String name = NecNbtAPI.Entity.getString(entity, "name");
```

**Supported types:** long, int, boolean, String, float, double, byte, short,
UUID, byte[], int[], long[], CompoundTag/NbtCompound.

## Cooldown API

```java
// Set a 100-tick cooldown
NecNbtAPI.Cooldown.setCooldown(player, "ability", 100);

// Check
if (NecNbtAPI.Cooldown.isOnCooldown(player, "ability")) { ... }

// Get remaining
long remaining = NecNbtAPI.Cooldown.getRemainingCooldown(player, "ability");
```

> ⚠️ **Performance note:** Each `putX`/`getX` call serializes the full entity NBT.
> For hot-path code (e.g. per-tick cooldown checks), consider caching the result.
> Future versions may switch to `PersistentDataHolder` for efficiency.
