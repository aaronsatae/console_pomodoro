plugins {
    id("java")
}

group = "org.study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.pomodoro.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}
