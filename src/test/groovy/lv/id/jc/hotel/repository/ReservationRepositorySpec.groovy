package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate

@DataJpaTest
@ActiveProfiles('test')
@Title('Reservation Repository')
class ReservationRepositorySpec extends Specification {

    @Subject
    @Autowired
    ReservationRepository repository

    @Issue('23')
    @Sql(['/users.sql', '/types.sql', '/rooms.sql', '/reservations.sql'])
    def 'should count busy hotel rooms'() {
        given:
        def date = LocalDate.parse check_date

        expect:
        repository.countBusyRooms(date) == booked_rooms

        where:
        check_date   | booked_rooms
        '2022-05-29' | 0
        '2022-06-01' | 1
        '2022-06-02' | 1
        '2022-06-03' | 2
        '2022-06-04' | 2
        '2022-06-05' | 3
        '2022-07-03' | 1
        '2022-07-04' | 0

    }
}
