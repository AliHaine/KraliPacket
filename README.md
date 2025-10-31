# KraliPacket

[![Maven Central](https://img.shields.io/maven-central/v/dev.aliyag/KraliPacket?style=flat-square)](https://central.sonatype.com/artifact/dev.aliyag/KraliPacket)
[![License](https://img.shields.io/badge/license-MIT-blue.svg?style=flat-square)](LICENSE)

A lightweight and modern Minecraft Packet API that simplifies custom plugin message channel management for Bukkit/Spigot servers.

## Installation

### Maven

```xml
<dependency>
    <groupId>dev.aliyag</groupId>
    <artifactId>KraliPacket</artifactId>
    <version>1.1</version>
    <scope>provided</scope>
</dependency>
```

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    compileOnly("dev.aliyag:KraliPacket:1.1")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    compileOnly 'dev.aliyag:KraliPacket:1.1'
}
```

## Quick Start

### Register a Channel

Get the KraliPacket instance and register your custom channel:

```java
import com.alihaine.kralipacket.KraliPacket;
import com.alihaine.kralipacket.KPacket;

public class YourPlugin extends JavaPlugin {
    private KPacket kPacket;
    
    @Override
    public void onEnable() {
        KraliPacket kraliPacket = KraliPacket.getKraliPacket();
        
        // Register without connection packet
        kPacket = kraliPacket.registerPacket("CUS|example", null);
        
        // Or with a connection packet that's sent automatically when a player joins
        ByteArrayDataOutput connectionPacket = ByteStreams.newDataOutput();
        connectionPacket.writeUTF("Welcome!");
        kPacket = kraliPacket.registerPacket("CUS|example", connectionPacket);
    }
}
```

### Send Packets

Once registered, you can send packets to players:

```java
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public void sendExamplePacket(Player player) {
    ByteArrayDataOutput out = ByteStreams.newDataOutput();
    out.writeUTF("Hello from server!");
    out.writeInt(42);
    
    kPacket.sendPacket(player, out);
}
```

## Connection Packets

Connection packets are automatically sent to players when they join the server. This is useful for:

- 📝 Sending initial configuration
- ⚙️ Setting up default client-side settings
- 📊 Synchronizing data between server and client
- 🎮 Initializing client-side features

If you don't need a connection packet, simply pass `null` during registration.

## Use Cases

- Custom client-server communication
- Modded server integration
- Client-side UI synchronization
- Custom data streaming
- Player-specific configurations


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.