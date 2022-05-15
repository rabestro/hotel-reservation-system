package lv.id.jc.hotel.service.impl

import lv.id.jc.hotel.model.dto.StatisticsRequest
import lv.id.jc.hotel.repository.ReservationRepository
import lv.id.jc.hotel.repository.RoomRepository
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Issue('23')
@Title('Statistics Service')
class StatisticsServiceImplSpec extends Specification {
    final TOTAL_ROOMS = 10
    final BUSY_ROOMS = 4

    def roomRepository = Mock(RoomRepository) {
        count() >> TOTAL_ROOMS
    }

    def reservationRepository = Mock ReservationRepository

    @Subject
    def service = new StatisticsServiceImpl()

    void setup() {
        service.roomRepository = roomRepository
        service.reservationRepository = reservationRepository
    }

    def 'should get statistics about free and busy rooms'() {
        given: 'a request for a report on statistics for the period'
        def request = new StatisticsRequest(startDate, endDate)

        when: 'we request a service to get statistics'
        def response = service.getStatistics(request)

        then: 'the number of records in the response is equal to the number of days'
        response.size() == days

        and: 'the service requests the repository for each of the days'
        days * reservationRepository.countBusyRooms(_ as LocalDate) >> BUSY_ROOMS

        where:
        start        | end
        '2022-05-01' | '2022-05-05'
        '2023-07-21' | '2023-08-02'

        and:
        startDate = LocalDate.parse start
        endDate = LocalDate.parse end
        days = 1 + ChronoUnit.DAYS.between(startDate, endDate) as int
    }
}
