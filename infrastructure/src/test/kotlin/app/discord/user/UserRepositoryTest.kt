package app.discord.user

import app.discord.jpa.JpaTest
import app.discord.repository.jpa.attendance.JpaAttendanceHistoryRepository
import app.discord.repository.jpa.user.JpaUserEntityRepository
import app.discord.repository.jpa.user.JpaUserRepository
import app.discord.user.entity.User
import app.discord.user.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired

@JpaTest
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
                insertUser.registerTime shouldBe validUser.registerTime
                insertUser.getTotalAttendanceHistories() shouldContainAll validUser.getTotalAttendanceHistories()
            }
        }
    }
})