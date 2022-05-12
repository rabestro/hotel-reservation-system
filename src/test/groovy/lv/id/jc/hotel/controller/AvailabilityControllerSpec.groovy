package lv.id.jc.hotel.controller

import lv.id.jc.hotel.model.dto.AvailabilityRequest
import lv.id.jc.hotel.service.AvailabilityService
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class AvailabilityControllerSpec extends Specification {

    def availabilityService = Mock AvailabilityService

    @Subject
    def controller = new AvailabilityController()

    void setup() {
        controller.service = availabilityService
    }

    def 'should request the service for the availability of hotel rooms'() {
        given:
        def request = new AvailabilityRequest(checkIn, checkOut)

        when:
        controller.getAvailability(request)

        then:
        1 * availabilityService.getAvailability(request)

        where: 'some dates'
        checkIn = LocalDate.EPOCH
        checkOut = LocalDate.EPOCH
    }
}
