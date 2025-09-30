# PlaceholderAPI

The official placeholder api for Allay, inspired by the famous PAPI plugin for Spigot.

> [!NOTE]
> This plugin is still under development and has not been released. If you are interested in using this plugin,
> please check out the latest build from our GitHub Actions
> page: [Build Artifacts](https://github.com/AllayMC/PlaceholderAPI/actions/workflows/gradle.yml).

## Install

- Download .jar file from [release](https://github.com/AllayMC/PlaceholderAPI/releases) or [action](https://github.com/AllayMC/PlaceholderAPI/actions/workflows/gradle.yml)
- Put it into `plugins` folder
- Restart the server, enjoy!

## Usages

```kts
repositories {
    mavenCentral()
    maven("https://central.sonatype.com/repository/maven-snapshots/")
}

dependencies {
    compileOnly(group = "org.allaymc", name = "papi", version = "0.1.0-SNAPSHOT")
}
```

```java
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event) {
    var joinMessage = "{player_name} joined the server! His game mode is {game_type}";
    joinMessage = PlaceholderAPI.getAPI().setPlaceholder(event.getPlayer(), joinMessage);
    event.setJoinMessage(joinMessage);
}
```

## Built-in Placeholders

There are a number of built-in placeholders that can be used once papi is installed:

- [x] `{x}` x coordinate
- [x] `{y}` y coordinate
- [x] `{z}` z coordinate
- [x] `{player_name}` player's name
- [x] `{dimension}` dimension name
- [x] `{dimension_id}` dimension id
- [x] `{ping}` player ping
- [x] `{date}` date
- [x] `{time}` time
- [x] `{datetime}` date and time
- [x] `{year}` year
- [x] `{month}` month
- [x] `{day}` day
- [x] `{hour}` hour
- [x] `{minute}` minute
- [x] `{second}` second
- [x] `{mc_version}` minecraft version
- [x] `{online}` online player count
- [x] `{max_online}` max online player count
- [x] `{address}` player's address
- [x] `{runtime_id}` player's runtime id
- [x] `{exp_level}` player's experience level
- [x] `{exp_progress}` player's experience progress
- [x] `{game_mode}` player's game mode
- [x] `{xuid}` player's xuid
- [x] `{uuid}` player's uuid
- [x] `{device_os}` os name of player's current device
- [x] `{locale}` player's locale

## Requirement

Java: 21+

Allay: 0.8.0+

## Contributing

Contributions are welcome! Feel free to fork the repository, improve the code, and submit pull requests with your
changes.

## License

This project is licensed under the LGPL v3 License - see the [LICENSE](LICENSE) file for details.