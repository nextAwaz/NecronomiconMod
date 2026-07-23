# Datagen API

`elocindev.necronomicon.api.datagen.v1.NecDatagenAPI`

Model and data generation helpers for Fabric (Forge support is partial).

## Items

```java
// Single item
NecDatagenAPI.Model.makeItem(myItem, modelCollector);

// Batch items
NecDatagenAPI.Model.makeItems(new Item[]{item1, item2}, modelCollector);

// Block item
NecDatagenAPI.Model.makeItem(myBlock, modelCollector);
```

## Blocks

```java
// Simple cube_all blocks
NecDatagenAPI.Model.makeCubeAllBlocks(generator, new Block[]{myBlock});

// Full woodset (planks, door, trapdoor, slab, stairs, button, fence, gate)
NecDatagenAPI.Model.makeWoodset(generator, planks, door, trapdoor, slab,
    stairs, button, fence, fenceGate);

// Slab + stairs from parent block
NecDatagenAPI.Model.makeSlabStair(generator, slab, stairs, parentBlock);
```
