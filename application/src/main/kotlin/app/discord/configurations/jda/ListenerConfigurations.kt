package app.discord.configurations.jda

import app.discord.common.EnableDiscordBot
import app.discord.listeners.GuildMemberJoinEventListenerAdapter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean( annotation = [EnableDiscordBot::class] )
class ListenerConfigurations {

    @Bean
    fun guildMemberJoinEventListenerAdapter() = GuildMemberJoinEventListenerAdapter()
}