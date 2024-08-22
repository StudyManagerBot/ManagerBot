package app.discord.configurations.jda

import app.discord.bot.JDAInstance
import app.discord.common.EnableDiscordBot
import net.dv8tion.jda.api.JDA
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean( annotation = [EnableDiscordBot::class])
class JDAConfiguration(
    @Value("\${discord.bot.secretToken}") private val secretToken: String
){

    @Bean
    fun jdaInstance() = JDAInstance(secretToken = this.secretToken)

    @Bean
    @ConditionalOnMissingBean(JDA::class)
    fun jda(jdaInstance: JDAInstance) = jdaInstance.jda

}