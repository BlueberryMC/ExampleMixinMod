import net.blueberrymc.blueberryfarm.blueberry

plugins {
    java
    id("net.blueberrymc.blueberryfarm") version("2.0.0-SNAPSHOT") // https://github.com/BlueberryMC/BlueberryFarm
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
    minecraftVersion.set("1.19.3")
    apiVersion.set("1.7.0-SNAPSHOT")
}

repositories {
    // mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo.blueberrymc.net/repository/maven-public/") }
}

dependencies {
    blueberry()
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks {
    withType<net.blueberrymc.blueberryfarm.tasks.RunClient> {
        this.addArgs("--mixin mixins.examplemod.json")
    }

    withType<net.blueberrymc.blueberryfarm.tasks.RunServer> {
        this.addArgs("--mixin mixins.examplemod.json")
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    jar {
        manifest.attributes(
            "TweakClass" to "org.spongepowered.asm.launch.MixinTweaker",
            "MixinConfigs" to "mixins.examplemod.json",
        )
    }

    test {
        useJUnitPlatform()
    }
}
