package app.discord.user.dto

import java.time.OffsetDateTime

@Deprecated(message = "not now Used, service impl에 추상화 클래스 작성시 참고 코드")
abstract class UserEvent(
    val userIdentifier: UserIdentifier,
    val userName: String,
    val globalName: String,
    val nickname: String,
    val registerTime: OffsetDateTime,
    val leaveTime: OffsetDateTime
)