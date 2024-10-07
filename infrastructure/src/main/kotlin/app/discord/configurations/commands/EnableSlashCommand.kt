package app.discord.configurations.commands

@Deprecated(message = "for removal")
@Target(
    AnnotationTarget.CLASS
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class EnableSlashCommand(
    //TODO Non-Global command support
    val isGlobal: Boolean = true,
    val referenceGuildId: String = ""
)