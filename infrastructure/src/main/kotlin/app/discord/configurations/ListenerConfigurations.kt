package app.discord.configurations

import app.discord.listeners.GuildMemberJoinEventListener
import app.discord.listeners.GuildVoiceUpdateListener
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
    ) = GuildMemberJoinEventListener(applicationEventPublisher = applicationEventPublisher)

    @Bean
    fun guildVoiceUpdateListener(
        applicationEventPublisher: ApplicationEventPublisher
    ) = GuildVoiceUpdateListener(applicationEventPublisher = applicationEventPublisher)
}