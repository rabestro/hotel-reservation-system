package lv.id.jc.hotel.service.impl

import lv.id.jc.hotel.model.dto.AvailabilityRequest
import lv.id.jc.hotel.repository.RoomTypeRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate

@Title("Availability Service")
class AvailabilityServiceImplSpec extends Specification {

    def roomTypeRepository = Mock RoomTypeRepository

    @Subject
    def availabilityService = new AvailabilityServiceImpl(roomTypeRepository: roomTypeRepository)

    def 'should request the repository for the availability of hotel rooms'() {

        given: 'a request containing arrival and departure dates '
        def request = new AvailabilityRequest(checkIn, checkOut)

        when:
        availabilityService.getAvailability(request)

        then:
        1 * roomTypeRepository.getAvailability(checkIn, checkOut)

        where:
        arrivalDate  | departureDate
        '2022-05-13' | '2022-05-28'
        '2023-07-21' | '2023-08-02'

        and:
        checkIn = LocalDate.parse arrivalDate
        checkOut = LocalDate.parse departureDate
    }
}
