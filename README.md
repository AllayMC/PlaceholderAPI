# Allay Java Plugin Template

Welcome to the java plugin template for allay.

## Prerequisites

- Java21 or higher.
- Allay installed.

## Getting Started

1. **Clone this Repository**

```bash
git clone https://github.com/AllayMC/JavaPluginTemplate.git
```
   
2. **Navigate to the Cloned Directory**

```bash
cd JavaPluginTemplate
```
   
3. **Change Plugin Information**

- Rename package name from `org.allaymc.javaplugintemplate` to `your.group.name.and.pluginname`
- Edit [build.gradle.kts](build.gradle.kts) and [settings.gradle.kts](settings.gradle.kts)
- Edit [plugin.json](src/main/resources/plugin.json)
- Reload gradle
   
4. **Build and Run Your Plugin**

```bash
gradlew shadowJar
```
   
This command will produce a `.jar` file in the `build/libs` directory. 
Copy the `.jar` file to the `plugins` directory of your allay server.
Start the allay server and check the logs to ensure your plugin loads and operates
as expected.

5. **Test Your Plugin in Gradle**

```bash
gradlew runServer
```

This command will start an allay server with your plugin loaded.
Then close allay server by clicking `X` in the dashboard window.

## Documentation

For a deeper dive into the Allay API and its functionalities, please refer to our [documentation](https://docs.allaymc.org) (WIP).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.