import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.50"
	maven
}

group = "ru.abbysoft.rehearsapp"
version = "0.0.9-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("javax.persistence:javax.persistence-api:2.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
