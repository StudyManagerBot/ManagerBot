package app.discord.user

import app.discord.jpa.JpaTest
import app.discord.jpa.isSame
import app.discord.repository.jpa.attendance.JpaAttendanceHistoryRepository
import app.discord.repository.jpa.user.JpaUserEntityRepository
import app.discord.repository.jpa.user.JpaUserRepository
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

@JpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest @Autowired constructor(
    private val userEntityRepository: JpaUserEntityRepository,
    private val attendanceHistoryRepository: JpaAttendanceHistoryRepository
): BehaviorSpec({
    val userRepository: UserRepository =
        JpaUserRepository(
            jpaUserEntityRepository = userEntityRepository,
            jpaAttendanceHistoryRepository = attendanceHistoryRepository )
    given("valid user"){
        val validUser: User = validUserWithHistory()
        `when`("insert"){
            val insertUser = userRepository.insertUser(user = validUser)
            then("successfully insert"){
                insertUser.userIdentifier shouldBe validUser.userIdentifier
                (insertUser.registerTime isSame validUser.registerTime) shouldBe true
                insertUser.getTotalAttendanceHistories() shouldContainAll validUser.getTotalAttendanceHistories()
            }
        }
    }
})
// insert[UserAttendanceHistory(userIdentifier=UserIdentifier(guildId=testGuildId, userId=testUserId),
// attendanceDates=[
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:02.897326, exitTime=2024-11-26T22:54:03.341603),
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.361566, exitTime=null),
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.676040, exitTime=null)])
// ]
//
// validUser[
// UserAttendanceHistory(userIdentifier=UserIdentifier(guildId=testGuildId, userId=testUserId), attendanceDates=[
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.676040, exitTime=null)])
// ]



//"attendanceDates" expected: <[
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:02.897326, exitTime=2024-11-26T22:54:03.341603),
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.361566, exitTime=null),
// UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.676040, exitTime=null)]>,
// but was: <[UserAttendance(date=2024-11-26, attendanceTime=2024-11-26T22:54:03.676040, exitTime=null)]>
