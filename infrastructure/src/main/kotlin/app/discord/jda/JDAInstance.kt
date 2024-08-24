package app.discord.jda

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent

class JDAInstance(
    private val secretToken: String
) {
    val jda: JDA = this.buildJDAInstance()

    private fun buildJDAInstance() = try {
        JDABuilder.createDefault(secretToken)
            .enableIntents(GatewayIntent.GUILD_MEMBERS)
            .setStatus(OnlineStatus.ONLINE)
            .setAutoReconnect(true)
            .build()
    } catch (e: Exception){
        throw BotCreateFailedException(exception = e)
    }
}