package app.discord.user

import app.discord.user.dto.attendance.AttendanceStatus
import app.discord.user.entity.User
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CheckUserTest : BehaviorSpec({

    given("등록되지 않은 유저가"){
        val user = null
        `when`("길드채널에 출석을 하면"){
            val serverMemberJoinEvent = serverMemberJoinEvent()
            then("유저를 가입시키고, 출석 처리를 한다."){
                if(user == null){
                    val registerUser = UserDomainBuilder.validUser()
                    val attendanceResult = registerUser.joinAttendance(serverMemberJoinEvent)
                    attendanceResult.status.isOk() shouldBe true
                }
            }
        }
    }

    given("올바른 유저가") {
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
                    user.updateUserInfo(userName = INVALID_SPECIAL_STRING)
                }
                user.userName shouldBe originalUserName
            }

            then("globe name에 의해서 실패하는 오류가 발생한다."){
                shouldThrowExactly<IllegalArgumentException> {
                    user.updateUserInfo(globalName = INVALID_SPECIAL_STRING)
                }
                user.globalName shouldBe originalGlobalName
            }
            then("nickname 의해서 실패하는 오류가 발생한다."){
                shouldThrowExactly<IllegalArgumentException> {
                    user.updateUserInfo(nickname = INVALID_SPECIAL_STRING)
                }
                user.nickname shouldBe originalNickname
            }
        }



        `when`("입장 로그가 없을때, 길드채널에서 나가면"){
            val leftEvent = serverMemberLeftEvent()
            then("오류를 발생시킨다."){ // FIXME 오류가 나와야하는데 왜?
                shouldThrowExactly<IllegalArgumentException> {
                    val result = user.leftAttendance(event = leftEvent)
                    result.status.isOk() shouldBe false
                    result.status shouldBe AttendanceStatus.FAILURE
                }
            }
        }

        `when`("길드채널에 들어오면"){
            val joinEvent = serverMemberJoinEvent()
            then("출석 join을 한다."){
                val result = user.joinAttendance(event = joinEvent)
                result.status.isOk() shouldBe true
                result.status shouldBe AttendanceStatus.IN_PROGRESS
            }
        }

        `when`("입장 로그가 존재할 때, 길드채널에서 나가면"){
            val joinEvent = serverMemberJoinEvent()
            user.joinAttendance(event = joinEvent)
            val leftEvent = serverMemberLeftEvent()
            then("출석시간을 기록한다."){
                val result = user.leftAttendance(event = leftEvent)
                result.status.isOk() shouldBe true
                result.status shouldBe AttendanceStatus.CHECKED
            }
        }

    }
})