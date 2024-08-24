package app.discord.configurations

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