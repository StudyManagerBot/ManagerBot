package app.discord.listeners

import app.discord.user.dto.UserIdentifier
import app.discord.user.dto.UserRegisterEvent
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.springframework.context.ApplicationEventPublisher
import java.time.OffsetDateTime

@DiscordEventHandler
class CommandListener (
    applicationEventPublisher: ApplicationEventPublisher
) : DiscordListener(applicationEventPublisher = applicationEventPublisher) {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == ("init_users")){
            event.guild?.members?.forEach { // FIXME(트랜잭션을 묶어서 실행하도록 변경)
                this.applicationEventPublisher.publishEvent(
                    this.addMember(member = it)
                )
            }
        }
        event.reply("초기화 완료").queue()
    }

    private fun addMember(member: Member) =
        UserRegisterEvent(
            userIdentifier = UserIdentifier(
                guildId = member.guild.id,
                userId = member.user.id
            ),
            userName = member.user.name,
            globalName = member.user.globalName?:"",
            nickname = member.nickname?:"",
            registerTime = OffsetDateTime.now(),
            leaveTime = OffsetDateTime.MIN,
        )


}