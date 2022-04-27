package lv.id.jc.hotel.model

import spock.lang.Specification

import java.time.LocalDate

class ReservationSpec extends Specification {
    void setup() {
    }

    def 'should validate reservation dates'() {
        given:
        def reservation = new Reservation(checkIn: firstDay, checkOut: lastDay)

        when:
        def isValid = reservation.isValidReservation()

        then:
        isValid == expected


        where:
        from         | to           | expected
        '2020-01-01' | '2020-01-02' | true

        and:
        firstDay = LocalDate.parse(from)
        lastDay = LocalDate.parse(to)
    }

    def "GetGuest"() {
    }
}
