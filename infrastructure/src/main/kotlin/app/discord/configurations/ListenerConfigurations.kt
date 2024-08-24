package app.discord.configurations

import app.discord.listeners.GuildMemberJoinEventListenerAdapter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean( annotation = [EnableDiscordBot::class] )
class ListenerConfigurations {

    @Bean
    fun guildMemberJoinEventListenerAdapter(
        applicationEventPublisher: ApplicationEventPublisher
    ) = GuildMemberJoinEventListenerAdapter(applicationEventPublisher = applicationEventPublisher)
}