# Text API

`elocindev.necronomicon.api.text.TextAPI`

Animated text and color gradient utilities. Acts as a cross-loader wrapper
for Minecraft text components.

## Color Gradients

```java
// Static gradient: color1 → color2
TextAPI.setStaticGradient(text, 0xff0000, 0x0000ff);

// Sliding animated gradient
TextAPI.setSlideGradient(text, offset, 0xff0000, 0x00ff00, 1.0F);

// Breathing gradient (slow pulse)
TextAPI.setBreathingGradient(text, offset, 0x67467d, 0x467d49, 1.0F);

// Rainbow cycling
TextAPI.setRainbowGradient(text, offset, 1.0F);
```

## Style Helpers

```java
// Generate styled text directly (for tooltips, etc.)
MutableText rainbow = TextAPI.Styles.getRainbowGradient(
    Text.of("Hello"), 0, 1.0F);
MutableText gradient = TextAPI.Styles.getStaticGradient(
    Text.of("World"), 0xfcc203, 0xfce303);
```

## Animated Item Names

Use `IAnimatedText` interface + `FancyItem` base class to create items
with animated display names:

```java
public class MyItem extends FancyItem {
    public MyItem(Settings settings) {
        super(settings, AnimatedText.RAINBOW);
    }
}
```

### Animation types

| Enum | Effect |
|------|--------|
| `EMPTY` | No animation |
| `RAINBOW` | Rainbow cycling |
| `ETYR` | Static purple-green gradient |
| `BREATHING_ETYR` | Breathing purple-green gradient |
