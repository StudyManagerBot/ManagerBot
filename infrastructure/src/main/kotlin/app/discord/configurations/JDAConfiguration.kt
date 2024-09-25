package app.discord.configurations

import app.discord.jda.JDAInstance
import net.dv8tion.jda.api.JDA
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBean( annotation = [EnableDiscordBot::class] )
class JDAConfiguration(
    @Value("\${discord.bot.secretToken}") private val secretToken: String
){

    @Bean
    fun jdaInstance() = JDAInstance(secretToken = this.secretToken)

    @Bean
    fun eventListenerAdapter(beanFactory: ConfigurableListableBeanFactory) = EventListenerAdapter(beanFactory = beanFactory)

    @Bean
    @ConditionalOnMissingBean(JDA::class)
    fun jda(jdaInstance: JDAInstance, eventListenerAdapter: EventListenerAdapter) : JDA{
        eventListenerAdapter.addListeners(jdaInstance = jdaInstance)
        eventListenerAdapter.addSlashCommands(jdaInstance = jdaInstance)
        return jdaInstance.jda
    }


}