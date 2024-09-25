package app.discord.listeners

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.context.ApplicationEventPublisher

@Deprecated(message = "메시지 받는 형식이 listener와 다른 메커니즘이므로, 새로운 템플릿을 만들 예정.")
@DiscordEventHandler
class MessageListener (applicationEventPublisher: ApplicationEventPublisher
) :DiscordListener(applicationEventPublisher){
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        this.applicationEventPublisher.publishEvent(
            this.onSlashCommand(event = event)
        )
    }

    private fun onSlashCommand(event: SlashCommandInteractionEvent){
        //TODO 메시지 받아서 명령어 수행하는 로직 추가할것. 1. 모든 유저를 등록하는 로직 추가.
    }

}