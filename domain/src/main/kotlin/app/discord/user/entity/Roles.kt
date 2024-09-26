package app.discord.user.entity

enum class UserRole(private vararg val actions: UserAction) {
    ADMIN(UserAction.BAN_USER, UserAction.KICK_USER, UserAction.SEND_MESSAGE),
    MANAGER(UserAction.KICK_USER, UserAction.SEND_MESSAGE),
    MEMBER();

    infix fun UserRole.allows(targetRoles: UserAction): Boolean
    = this.actions.contains(element = targetRoles)
}

enum class UserAction{
    KICK_USER,
    BAN_USER,
    SEND_MESSAGE,
}