import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.vanniktech.maven.publish.MavenPublishBaseExtension

plugins {
    id("java-library")
    id("com.vanniktech.maven.publish") version "0.34.0"
    id("com.gradleup.shadow") version "9.2.2"
    id("org.allaymc.gradle.plugin") version "0.2.0"
}

group = "org.allaymc"
version = "0.1.2-SNAPSHOT"
description = "The official placeholder api for Allay"

allay {
    api = "0.17.0"

    plugin {
        entrance = "org.allaymc.papi.PlaceholderAPI"
        name = "PlaceholderAPI"
        authors += "daoge_cmd"
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        configureEach {
            options.isFork = true
        }
    }

    // We already have sources jar, so no need to build Javadoc, which would cause a lot of warnings
    withType<Javadoc> {
        enabled = false
    }

    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<ShadowJar> {
        archiveFileName = "${project.name}-${version}-shaded.jar"
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.42")

    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.42")
}

configure<MavenPublishBaseExtension> {
    publishToMavenCentral()
    signAllPublications()

    coordinates(project.group.toString(), "papi", project.version.toString())

    pom {
        name.set(project.name)
        description.set("The official placeholder api for Allay")
        inceptionYear.set("2025")
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
