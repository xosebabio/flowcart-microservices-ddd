plugins {
    id("java")
    id("org.springframework.boot")
}

springBoot {
    mainClass.set("com.xbs.flowcart.catalog.CatalogApplication")
}

dependencies {
    implementation(project(":catalog-implementation"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
