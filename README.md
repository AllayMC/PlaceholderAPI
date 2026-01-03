# PlaceholderAPI

![Maven Central Version](https://img.shields.io/maven-central/v/org.allaymc/papi?label=papi)

The official placeholder api for Allay, inspired by the famous PAPI plugin for Spigot.

## Install

- Download .jar file from [release](https://github.com/AllayMC/PlaceholderAPI/releases) or [action](https://github.com/AllayMC/PlaceholderAPI/actions/workflows/gradle.yml)
- Put it into `plugins` folder
- Restart the server, enjoy!

## Usages

```kts
repositories {
    mavenCentral()
}

dependencies {
    compileOnly(group = "org.allaymc", name = "papi", version = "0.1.3")
}
```

```java
@EventHandler
public void onPlayerJoin(PlayerJoinEvent event) {
    var joinMessage = "{player_name} joined the server! His game mode is {game_mode}";
    joinMessage = PlaceholderAPI.getAPI().setPlaceholder(event.getPlayer(), joinMessage);
    event.setJoinMessage(joinMessage);
}
```

## Built-in Placeholders

- [x] `{x}` x coordinate
- [x] `{y}` y coordinate
- [x] `{z}` z coordinate
- [x] `{player_name}` player's name
- [x] `{player_display_name}` player's display name
- [x] `{health}` player's health
- [x] `{max_health}` player's max health
- [x] `{food_level}` player's food level
- [x] `{food_exhaustion_level}` player's food exhaustion level
- [x] `{food_saturation_level}` player's food saturation level
- [x] `{absorption}` player's absorption amount
- [x] `{air_supply_ticks}` player's remaining air supply ticks
- [x] `{air_supply_max_ticks}` player's max air supply ticks
- [x] `{on_fire_ticks}` player's remaining fire ticks
- [x] `{pitch}` player's pitch
- [x] `{yaw}` player's yaw
- [x] `{hand_item}` player's item in hand
- [x] `{offhand_item}` player's item in offhand
- [x] `{helmet_item}` player's helmet item
- [x] `{chestplate_item}` player's chestplate item
- [x] `{leggings_item}` player's leggings item
- [x] `{boots_item}` player's boots item
- [x] `{is_sneaking}` whether player is sneaking
- [x] `{is_sprinting}` whether player is sprinting
- [x] `{is_flying}` whether player is flying
- [x] `{is_swimming}` whether player is swimming
- [x] `{is_crawling}` whether player is crawling
- [x] `{is_gliding}` whether player is gliding
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

Allay: 0.17.0+

## Contributing

Contributions are welcome! Feel free to fork the repository, improve the code, and submit pull requests with your
changes.

## License

This project is licensed under the LGPL v3 License - see the [LICENSE](LICENSE) file for details.
