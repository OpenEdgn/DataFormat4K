import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.OpenEdgn"
version = "last"
plugins {
    kotlin("jvm") version "1.4.10"
    `maven-publish`
}

java {
    modularity.inferModulePath.set(true)
}


java.sourceCompatibility = JavaVersion.VERSION_11

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDir = compileKotlin.destinationDir


dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
    maven { url = project.uri("https://jitpack.io") }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}