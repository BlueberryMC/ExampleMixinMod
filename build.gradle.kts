import net.blueberrymc.blueberryFarm.blueberry

plugins {
    java
    id("net.blueberrymc.blueberryFarm") version("1.0.3-SNAPSHOT") // https://github.com/BlueberryMC/BlueberryFarm
}

group = "net.blueberrymc.example"
version = "0.0.1"

tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

blueberry {
    minecraftVersion.set("1.18")
    apiVersion.set("0.1.0")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo.spongepowered.org/repository/maven-public/") }
    maven { url = uri("https://repo2.acrylicstyle.xyz/") }
}

dependencies {
    blueberry()
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks {
    withType<net.blueberrymc.blueberryFarm.tasks.RunClient> {
        this.addArgs("--mixin mixins.examplemod.json")
    }

    withType<net.blueberrymc.blueberryFarm.tasks.RunServer> {
        this.addArgs("--mixin mixins.examplemod.json")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}