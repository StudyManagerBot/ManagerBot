package app.discord.repository.jpa.attendance

import app.discord.jpa.JpaTest
import app.discord.jpa.attendance.*
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.longs.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

@JpaTest
class JpaAttendanceRepositoryCRUDTest @Autowired constructor(
    private val attendanceRepository: JpaAttendanceHistoryRepository
): BehaviorSpec({

    given("attendance history with exit time"){
        val jpaAttendanceHistory = createAttendanceHistoryEntity()
        val jpaAttendanceHistory2 = createAttendanceHistoryEntity(id = 2L)
        val attendanceHistories = attendanceHistories(startId = 3L)

        // 1second
        val toleranceInSeconds = 1L

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
                val searchHistories = attendanceRepository.findAllById(insertHistories.map { it.id })
                searchHistories.size shouldBe ATTENDANCE_HISTORY_COUNT
                insertHistories.size shouldBe ATTENDANCE_HISTORY_COUNT
                ( insertHistories isSameAll searchHistories ) shouldBeEqual true
            }

        }

        `when`("update single attendanceHistory"){
            attendanceRepository.save(jpaAttendanceHistory2)
            val updateHistory = jpaAttendanceHistory2.change(exitTime = OffsetDateTime.now())
            attendanceRepository.save(updateHistory)

            then("successfully update attendance history"){
                val searchHistory = attendanceRepository.findById(updateHistory.id)
                val timeDifference = ChronoUnit.SECONDS.between(searchHistory.get().exitTime, updateHistory.exitTime)
                searchHistory.isPresent shouldBeEqual true
                searchHistory.get().id shouldBe updateHistory.id
                searchHistory.get().userIdentifier shouldBe updateHistory.userIdentifier
                timeDifference.absoluteValue shouldBeLessThanOrEqual toleranceInSeconds
            }
        }

    }
    given("attendance history"){
        val jpaAttendanceHistory = createAttendanceHistoryEntityWithoutExitTime()

        `when`("insert new attendance history"){
            attendanceRepository.save(jpaAttendanceHistory)

            then("successfully insert new attendance history"){
                val searchHistory = attendanceRepository.findById(jpaAttendanceHistory.id)
                searchHistory.isPresent shouldBeEqual true
                searchHistory.get().id shouldBe jpaAttendanceHistory.id
                searchHistory.get().userIdentifier shouldBe jpaAttendanceHistory.userIdentifier
                searchHistory.get().exitTime shouldBe null
            }
        }
    }
})