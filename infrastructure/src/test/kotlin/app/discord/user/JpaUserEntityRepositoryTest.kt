package app.discord.user

import app.discord.repository.jpa.user.JpaUserEntityRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.OffsetDateTime

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JpaUserEntityRepositoryTest @Autowired constructor(
    private val repository: JpaUserEntityRepository
): BehaviorSpec({
    val testUser = JpaUserEntityBuilder.validUser()

    repository.save(testUser)

    given("guilId, userId를 기준으로 유저를 조회할때"){
        `when`("유저정보를 정상적으로 불러오면"){
            val foundUser = repository.findByGuildIdAndUserId("testGuildId", "testUserId")
            then("유저 정보를 return한다."){
                foundUser shouldNotBe null
                foundUser?.id shouldBe  testUser.id
                foundUser?.guildId shouldBe testUser.guildId
                foundUser?.userId shouldBe testUser.userId
                foundUser?.username shouldBe testUser.username
                foundUser?.globalName shouldBe testUser.globalName

                foundUser?.nickname shouldBe testUser.nickname
                foundUser?.isBan shouldBe testUser.isBan
                foundUser?.registerTime shouldBe testUser.registerTime
                foundUser?.leaveTime shouldBe OffsetDateTime.MIN
            }
        }
        `when`("유저 정보가 없다면"){
            val noHaveUserInfo = repository.findByGuildIdAndUserId("00000000", "00000000")
            then("유저 정보가 null이다."){
                noHaveUserInfo shouldBe null
            }
        }
    }

    given("유저정보를 업데이트를 시도할때"){
        val tryUpdateUser = JpaUserEntityBuilder.updateUser(
            id = testUser.id
        )
        repository.save(tryUpdateUser)

        val updatedUser = repository.findByGuildIdAndUserId("testGuildId", "testUserId")
        then("유저 정보가 업데이트 된다.") {
            updatedUser shouldNotBe null
            updatedUser?.id shouldBe testUser.id
            updatedUser?.registerTime shouldNotBe testUser.registerTime

            updatedUser?.guildId shouldBe tryUpdateUser.guildId
            updatedUser?.userId shouldBe tryUpdateUser.userId
            updatedUser?.username shouldBe tryUpdateUser.username
            updatedUser?.globalName shouldBe tryUpdateUser.globalName
            updatedUser?.nickname shouldBe tryUpdateUser.nickname
            updatedUser?.isBan shouldBe tryUpdateUser.isBan
            updatedUser?.registerTime shouldBe tryUpdateUser.registerTime
        }

        `when`("업데이트할 유저가 없다면"){
            val foundUser = repository.findByGuildIdAndUserId("nonExistentGuildId", "nonExistentUserId")
            then("유저 정보가 null이다.") {
                foundUser shouldBe null
            }
        }
    }

    given("유저가 서버에서 탈퇴할 때"){
        `when`("유저가 올바른 유저라면"){
            then("유저를 탈퇴시킨다."){
                val leavedUser = JpaUserEntityBuilder.leavedUser(
                    userEntity = testUser
                )
                repository.save(leavedUser)

                val updatedUser = repository.findByGuildIdAndUserId("testGuildId", "testUserId")

                leavedUser.id shouldBe testUser.id
                leavedUser.leaveTime shouldNotBe testUser.leaveTime

                updatedUser shouldNotBe null
                updatedUser?.id shouldBe testUser.id
                updatedUser?.leaveTime shouldBe leavedUser.leaveTime

            }
        }
    }
})

