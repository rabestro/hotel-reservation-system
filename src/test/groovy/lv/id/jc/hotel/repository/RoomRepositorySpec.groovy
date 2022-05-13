package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import java.time.LocalDate
import java.time.temporal.ChronoUnit

@DataJpaTest
@Title("Room's Repository")
class RoomRepositorySpec extends Specification {
    @Subject
    @Autowired
    RoomRepository roomRepository

    @Sql("/rooms.sql")
    @Unroll("#checkIn - #checkOut (#type) = #numbers")
    def 'should find available rooms'() {
        when:
        def rooms = roomRepository.findAvailableRooms(type, checkIn, checkOut) as List

        then:
        rooms.number == numbers

        where:
        type | arrivingDate | departureDate | numbers
        1    | '2022-05-11' | '2022-05-12'  | ['101', '301', '302']
        2    | '2022-05-11' | '2022-05-12'  | ['102', '201', '202']
        3    | '2022-05-11' | '2022-05-12'  | ['103', '303']

        and:
        checkIn = LocalDate.parse(arrivingDate)
        checkOut = LocalDate.parse(departureDate)
    }

    @Issue('21')
    @Sql('/rooms.sql')
    def "should get room's schedule"() {
        when: 'we calculate the timetable for a hotel room'
        def schedule = roomRepository.getSchedule(room, startDate, endDate)

        then: 'we get the schedule for all days including the last one'
        schedule.size() == 1 + ChronoUnit.DAYS.between(startDate, endDate) as int

        and: 'we get correct reservation ids'
        schedule*.getBookId() == bookId as List<Long>

        and: 'for each day we get the name of the guest'
        schedule*.getName() == name

        where:
        room | start        | end          | bookId
        1    | '2022-05-30' | '2022-06-02' | [null, null, 1, 1]
        2    | '2022-07-01' | '2022-07-05' | [4, 4, 5, null, 6]
        __
        _ | name
        _ | [null, null, 'Peter McDermott', 'Peter McDermott']
        _ | ['Peter McDermott', 'Peter McDermott', 'Marsha Preyscott', null, 'Albert Wells']

        and:
        startDate = LocalDate.parse(start)
        endDate = LocalDate.parse(end)
    }

}
