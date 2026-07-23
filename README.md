# Necronomicon API
An API to make less repetitive code, sync changes and bring parity to both loaders with common code.
It also implements some bug fixes to make my mods work properly.

**License:** Mozilla Public License 2.0 (MPL-2.0)

## Features
🛠️ Easy to use config API for both loaders.

🔄 Datagen utility API for Fabric (and partially for Forge)

🌍 Dynamic Pack Generation API for both loaders

🌏 Worldgen utility API for Fabric (Mostly feature generation)

🌿 Biomes API (Work in progress)

🔧 NBT API that simplifies methods and makes them work in both loaders.

📝 Text API to add animated text and other misc stuff

🤝 Unifying utility methods that work for both loaders

## Using Necronomicon in your project

Integrating Necronomicon API in your project using CurseMaven (This does not yet support javadocs but I will probably implement a custom maven in the future)

```
repositories { 
  maven { 
    url "https://cursemaven.com" 
    content { includeGroup "curse.maven" } 
  } 
}
```

## Fabric
```
dependencies { 
  modImplementation "curse.maven:necronomicon-586157:${necronomicon_fileid}"
}
```

## Forge
```
dependencies { 
  implementation fg.deobf("curse.maven:necronomicon-586157:${necronomicon_fileid}") 
}
```



# Feature Comparision (Forge vs Fabric)

| Feature           | Fabric | Forge  |
|-------------------|--------|--------|
| v1/config         | ✅ Yes| ✅ Yes|
| v1/datagen        | ✅ Yes| ⚠️ Partial|
| v1/resource       | ✅ Yes| ✅ Yes|
| v1/worldgen       | WIP    | WIP   |
| v1/nbt            | ✅ Yes| ✅ Yes|
| v1/text           | ✅ Yes| ✅ Yes|
| v1/biomes         | WIP   |  WIP   |
| v1/events         | WIP   |  WIP   |
| utils             | ✅ Yes| ✅ Yes|
| json              | ✅ Yes| ✅ Yes|
