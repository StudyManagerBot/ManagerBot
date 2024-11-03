package app.discord.configurations.commands


@EnableSlashCommand
class UserAddCommand: BaseCommand(
    command = "init_users",
    description = "유저 모두를 새로 등록합니다."
)