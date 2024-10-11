package app.discord.configurations.commands

import org.springframework.stereotype.Component

@Target(
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class EnableSlashCommand