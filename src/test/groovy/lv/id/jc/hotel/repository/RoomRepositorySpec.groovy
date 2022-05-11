package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import java.time.LocalDate

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
}
