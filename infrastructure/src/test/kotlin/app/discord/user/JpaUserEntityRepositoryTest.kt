package app.discord.user

import app.discord.jpa.isSame
import app.discord.repository.jpa.attendance.schema.UserEntityIdentifier
import app.discord.repository.jpa.user.JpaUserEntityRepository
import app.discord.repository.jpa.user.schema.UserEntity
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
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

    given("유저를 등록할 때"){
        val registerUser = JpaUserEntityBuilder.validUser()
        `when`("정상적인 userIdentifier를 가지고 있다면"){
            then("유저 정보를 저장한다."){
                repository.save(registerUser)
                val registeredUser = repository.findByUserIdentifier(userIdentifier = DEFAULT_USER_ENTITY_IDENTIFIER)

                registeredUser shouldNotBe null
                registeredUser?.id shouldBe  registerUser.id
                registeredUser?.userIdentifier shouldBe registerUser.userIdentifier
                registeredUser?.username shouldBe registerUser.username
                registeredUser?.globalName shouldBe registerUser.globalName
                registeredUser?.nickname shouldBe registerUser.nickname
                registeredUser?.isBan shouldBe registerUser.isBan
                registeredUser?.registerTime shouldBe registerUser.registerTime
                registeredUser?.leaveTime shouldBe registerUser.leaveTime

            }
        }
    }

    given("guilId, userId를 가진 유저가"){
        `when`("유저정보를 정상적으로 불러오면"){
            val foundUser = repository.findByUserIdentifier(userIdentifier = DEFAULT_USER_ENTITY_IDENTIFIER)
            then("유저 정보를 return한다."){
                foundUser shouldNotBe null
                foundUser?.id shouldBe  testUser.id
                foundUser?.userIdentifier shouldBe testUser.userIdentifier
                foundUser?.username shouldBe testUser.username
                foundUser?.globalName shouldBe testUser.globalName

                foundUser?.nickname shouldBe testUser.nickname
                foundUser?.isBan shouldBe testUser.isBan
                foundUser?.registerTime shouldBe testUser.registerTime
                foundUser?.leaveTime shouldBe OffsetDateTime.MIN
            }
        }
        `when`("유저 정보가 없다면"){
            val noHaveUserInfo = repository.findByUserIdentifier(userIdentifier = UserEntityIdentifier(
                guildId = "00000000",
                userId = "00000000"
            ))
            then("유저 정보가 null이다."){
                noHaveUserInfo shouldBe null
            }
        }
    }

    given("이미 가입되어있는 유저가"){
        `when`("자신의 정보를 갱신할 때"){
            then("유저 정보가 업데이트 된다.") {
                val tryUpdateUser = testUser.change(
                    globalName = "updateUserName",
                    registerTime = OffsetDateTime.now().plusHours(1)
                )
                repository.save(tryUpdateUser)

                val updatedUser = repository.findByUserIdentifier(userIdentifier = DEFAULT_USER_ENTITY_IDENTIFIER)

                updatedUser shouldNotBe null
                updatedUser?.id shouldBe testUser.id
                (updatedUser?.registerTime isSame testUser.registerTime) shouldBeEqual false

                updatedUser?.userIdentifier shouldBe tryUpdateUser.userIdentifier
                updatedUser?.username shouldBe tryUpdateUser.username
                updatedUser?.globalName shouldBe tryUpdateUser.globalName
                updatedUser?.nickname shouldBe tryUpdateUser.nickname
                updatedUser?.isBan shouldBe tryUpdateUser.isBan
                (updatedUser?.registerTime isSame tryUpdateUser.registerTime) shouldBeEqual true
            }
        }

        `when`("업데이트할 유저가 없다면"){
            val foundUser = repository.findByUserIdentifier(userIdentifier = UserEntityIdentifier(
                guildId = "nonExistentGuildId",
                userId = "nonExistentUserId"
            ))
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

                val updatedUser = repository.findByUserIdentifier(DEFAULT_USER_ENTITY_IDENTIFIER)

                leavedUser.id shouldBe testUser.id
                (leavedUser.leaveTime isSame testUser.leaveTime) shouldBeEqual false

                updatedUser shouldNotBe null
                updatedUser?.id shouldBe testUser.id
                (updatedUser?.leaveTime isSame leavedUser.leaveTime) shouldBeEqual true

            }
        }
    }

    given("1개의 길드의 모든 멤버 정보를 삭제할 때"){
        val userToDelete = JpaUserEntityBuilder.validUser(guildId = "deletedGuildId")
        repository.save(userToDelete)
        `when`("guildId가 있다면"){
            then("해당 길드 유저 정보를 모두 지운다."){
//                val membersToDelete:List<UserEntity> = repository.findAllByGuildId(guildId =  userToDelete.guildId)
                val membersToDelete:List<UserEntity> =
                    repository.findAllByUserIdentifierGuildId(guildId = userToDelete.userIdentifier.guildId)
                repository.deleteAllInBatch(membersToDelete)

//                val deletedMembers = repository.findAllByGuildId(guildId =  userToDelete.guildId)
                val deletedMembers =
                    repository.findAllByUserIdentifierGuildId(guildId =  userToDelete.userIdentifier.guildId)
                val allUsers = repository.findAll()
                deletedMembers.size shouldBe 0
                allUsers.size shouldNotBe 0

            }
        }
    }
})

