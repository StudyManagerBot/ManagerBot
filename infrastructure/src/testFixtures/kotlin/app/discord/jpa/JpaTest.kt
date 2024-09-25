package app.discord.jpa

import jakarta.transaction.Transactional
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(properties = ["spring.config.location = classpath:application-test.yaml"])
@Transactional
annotation class JpaTest