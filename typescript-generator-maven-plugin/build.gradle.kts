/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("cz.habarta.typescript-generator.java-conventions")
}

dependencies {
    api(project(":typescript-generator-core"))
    compileOnly("org.apache.maven:maven-core:3.0")
    compileOnly("org.apache.maven.plugin-tools:maven-plugin-annotations:3.0")
}

description = "typescript-generator-maven-plugin"
