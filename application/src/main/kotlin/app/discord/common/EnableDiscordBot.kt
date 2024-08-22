package app.discord.common

import app.discord.configurations.jda.JDAConfiguration
import org.springframework.context.annotation.Import

@Target(
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(
    JDAConfiguration::class
)
annotation class EnableDiscordBot