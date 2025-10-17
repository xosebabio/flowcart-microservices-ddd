plugins {
    id("java")
    id("org.springframework.boot") version "3.3.6" apply false
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    group = "com.xbs.flowcart"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.3.6")
            mavenBom("org.springframework:spring-framework-bom:6.1.6")
            mavenBom("org.springframework.data:spring-data-bom:2024.1.6")
        }
    }

    dependencies {
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
