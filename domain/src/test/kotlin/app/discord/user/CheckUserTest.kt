package app.discord.user

import app.discord.user.entity.User
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CheckUserTest : BehaviorSpec({


    given("올바른 유저가") {
        `when`("첫 가입을 시도하면"){
            then("해당 유저 도메인이 생성된다."){
                //TODO(테스트 코드 추가할것.)
            }
        }

        val user = UserDomainBuilder.validUser()
        `when`("올바른 유저정보를 업데이트하면"){
            val updatedUserName = "testUName"
            val updatedGlobalName = "testGName"
            val updatedNickname = "testNName"
            val updatedUser: User =  user.updateUserInfo(userName = updatedUserName,
                globalName = updatedGlobalName,
                nickname = updatedNickname)
            then("정상적으로 유저 정보가 변경된다."){
                updatedUser.userName shouldBe updatedUserName
                updatedUser.globalName shouldBe updatedGlobalName
                updatedUser.nickname shouldBe updatedNickname

                updatedUser.userIdentifier shouldBe user.userIdentifier
                updatedUser.registerTime shouldBe user.registerTime
                updatedUser.leaveTime shouldBe user.leaveTime
                updatedUser.isBan shouldBe user.isBan
            }
        }

        `when`("잘못된 유저정보를 업데이트하면"){
            val originalUserName = user.userName
            val originalGlobalName = user.globalName
            val originalNickname = user.nickname

            then("userName에 의해서 실패하는 오류가 발생한다."){
                shouldThrowExactly<IllegalArgumentException> {
                    user.updateUserInfo(userName = UserEventBuilder.INVALID_SPECIAL_STRING)
                }
                user.userName shouldBe originalUserName
            }

            then("globe name에 의해서 실패하는 오류가 발생한다."){
                shouldThrowExactly<IllegalArgumentException> {
                    user.updateUserInfo(globalName = UserEventBuilder.INVALID_SPECIAL_STRING)
                }
                user.globalName shouldBe originalGlobalName
            }
            then("nickname 의해서 실패하는 오류가 발생한다."){
                shouldThrowExactly<IllegalArgumentException> {
                    user.updateUserInfo(nickname = UserEventBuilder.INVALID_SPECIAL_STRING)
                }
                user.nickname shouldBe originalNickname
            }
        }

    }

})