plugins {
    id("java-library")
}

dependencies {
    implementation(project(":catalog-api-contract"))
    implementation(project(":catalog-domain-events"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.data:spring-data-jpa")
    implementation("jakarta.validation:jakarta.validation-api")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
