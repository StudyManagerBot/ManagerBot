import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("net.dv8tion:JDA:${rootProject.extra.get("jdaVersion")}")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
    //Springboot starter jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //AOP
    implementation("org.springframework.boot:spring-boot-starter-aop")
    //Local & Test database
    runtimeOnly("com.h2database:h2")
    //MariaDB
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}