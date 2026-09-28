plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

kotlin {
    // Built with JDK 21, emitted as Java 17 bytecode for the Android app to consume.
    jvmToolchain(21)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

application {
    mainClass.set("zanshin.cli.MainKt")
}

dependencies {
    implementation(project(":core"))
}
