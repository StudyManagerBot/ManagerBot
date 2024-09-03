package app.discord.configurations

import app.discord.repository.jpa.attendance.JpaAttendanceHistoryRepository
import app.discord.repository.jpa.user.JpaUserEntityRepository
import app.discord.repository.jpa.user.JpaUserRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean( annotation = [EnableDiscordBot::class] )
class DiscordBotConfigurations {

    @Bean
    fun userRepository(jpaUserEntityRepository: JpaUserEntityRepository,
                       jpaAttendanceHistoryRepository: JpaAttendanceHistoryRepository) = JpaUserRepository(
        jpaUserEntityRepository = jpaUserEntityRepository,
        jpaAttendanceHistoryRepository = jpaAttendanceHistoryRepository

    )
}