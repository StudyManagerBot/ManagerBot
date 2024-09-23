package app.discord.repository.jpa.attendance

import app.discord.jpa.JpaTest
import app.discord.jpa.attendance.*
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import java.time.OffsetDateTime

@JpaTest
class JpaAttendanceRepositoryCRUDTest @Autowired constructor(
    private val attendanceRepository: JpaAttendanceHistoryRepository
): BehaviorSpec({

    given("attendance history"){
        val jpaAttendanceHistory = createAttendanceHistoryEntity()
        val jpaAttendanceHistory2 = createAttendanceHistoryEntity(id = 2L)
        val attendanceHistories = attendanceHistories(startId = 3L)

        `when`("insert new single attendance history"){
            attendanceRepository.save(jpaAttendanceHistory)

            then("successfully insert new attendance history"){
                val searchHistory = attendanceRepository.findById(jpaAttendanceHistory.id)
                searchHistory.isPresent shouldBeEqual true
                searchHistory.get().id shouldBe jpaAttendanceHistory.id
                searchHistory.get().userIdentifier shouldBe jpaAttendanceHistory.userIdentifier
            }

            then("successfully remove history"){
                attendanceRepository.delete(jpaAttendanceHistory)
                val searchHistory = attendanceRepository.findById(jpaAttendanceHistory.id)
                searchHistory.isPresent shouldBeEqual false

            }
        }

        `when`("insert multiple histories"){
            val insertHistories = attendanceRepository.saveAll(attendanceHistories)

            then("successfully insert multiple histories"){
                val searchHistories = attendanceRepository.findAll()
                searchHistories.size shouldBe ATTENDANCE_HISTORY_COUNT
                insertHistories.size shouldBe ATTENDANCE_HISTORY_COUNT
            }

            then("successfully search histories by userIdentifier"){
                val searchHistories = attendanceRepository.findAllByUserIdentifier(userEntityIdentifier = DEFAULT_USER_ENTITY_IDENTIFIER)
                searchHistories.isNotEmpty() shouldBe true
                searchHistories.size shouldBe ATTENDANCE_HISTORY_COUNT
                ( insertHistories isSameAll searchHistories ) shouldBeEqual true
            }

        }

        `when`("update single attendanceHistory"){
            attendanceRepository.save(jpaAttendanceHistory2)
            val updateHistory = jpaAttendanceHistory2.change(exitTime = OffsetDateTime.now())
            attendanceRepository.save(updateHistory)

            then("successfully update attendance history"){
                val searchHistory = attendanceRepository.findById(updateHistory.id)
                searchHistory.isPresent shouldBeEqual true
                searchHistory.get().id shouldBe updateHistory.id
                searchHistory.get().userIdentifier shouldBe updateHistory.userIdentifier
                ( searchHistory.get().exitTime!!.toLocalDateTime().isEqual(updateHistory.exitTime!!.toLocalDateTime()) ) shouldBeEqual true
            }
        }

    }
})