import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "org.allaymc.papi"
description = "The official placeholder api for Allay"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    compileOnly(group = "org.allaymc.allay", name = "api", version = "master-SNAPSHOT")
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.34")

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.34")
}

publishing {
    repositories {
        // Jitpack requires us to publish artifacts to local maven repo
        mavenLocal()
    }

    java {
        withSourcesJar()
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            pom {
                inceptionYear.set("2025")
                packaging = "jar"
                url.set("https://github.com/AllayMC/PlaceholderAPI")

                scm {
                    connection.set("scm:git:git://github.com/AllayMC/PlaceholderAPI.git")
                    developerConnection.set("scm:git:ssh://github.com/AllayMC/PlaceholderAPI.git")
                    url.set("https://github.com/AllayMC/PlaceholderAPI")
                }

                licenses {
                    license {
                        name.set("LGPL 3.0")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.en.html")
                    }
                }

                developers {
                    developer {
                        name.set("AllayMC Team")
                        organization.set("AllayMC")
                        organizationUrl.set("https://github.com/AllayMC")
                    }
                }
            }
        }
    }
}

tasks.shadowJar {
    archiveClassifier = "shaded"
}

tasks.register<JavaExec>("runServer") {
    outputs.upToDateWhen { false }
    dependsOn("shadowJar")

    val shadowJar = tasks.named("shadowJar", ShadowJar::class).get()
    val pluginJar = shadowJar.archiveFile.get().asFile
    val cwd = layout.buildDirectory.file("run").get().asFile
    val pluginsDir = cwd.resolve("plugins").apply { mkdirs() }
    doFirst { pluginJar.copyTo(File(pluginsDir, pluginJar.name), overwrite = true) }

    val group = "org.allaymc.allay"
    val allays = configurations.compileOnly.get().dependencies.filter { it.group == group }
    val dependency = allays.find { it.name == "server" } ?: allays.find { it.name == "api" }!!
    val server = dependencies.create("$group:server:${dependency.version}")
    classpath = files(configurations.detachedConfiguration(server).resolve())
    mainClass = "org.allaymc.server.Allay"
    workingDir = cwd
}
