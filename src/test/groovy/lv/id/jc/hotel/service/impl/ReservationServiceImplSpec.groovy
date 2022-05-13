package lv.id.jc.hotel.service.impl

import lv.id.jc.hotel.model.Reservation
import lv.id.jc.hotel.model.Room
import lv.id.jc.hotel.model.RoomType
import lv.id.jc.hotel.model.User
import lv.id.jc.hotel.model.dto.BookingRequest
import lv.id.jc.hotel.repository.ReservationRepository
import lv.id.jc.hotel.repository.RoomRepository
import lv.id.jc.hotel.repository.UserRepository
import org.springframework.data.util.Streamable
import org.springframework.security.core.userdetails.UserDetails
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate
import java.util.stream.Stream

@Issue('19')
@Title('Reservation Service')
class ReservationServiceImplSpec extends Specification {
    static GUEST_EMAIL = 'marsha@guest.com'
    static GUEST_NAME = 'Marsha Preyscott'
    static SINGLE_ROOM = 1
    static ROOM_NUMBER = '101'
    static RESERVATION_ID = 1L

    def type = Mock(RoomType)
    def room = Mock(Room) {
        getNumber() >> ROOM_NUMBER
        getType() >> type
    }
    def guest = Mock(User) {
        getName() >> GUEST_NAME
        getEmail() >> GUEST_EMAIL
    }
    def userDetails = Mock UserDetails
    def userRepository = Mock UserRepository
    def roomRepository = Mock RoomRepository
    def reservationRepository = Mock ReservationRepository

    def databaseResult = Mock (Streamable) {
        stream() >> Stream.of(room)
    }

    @Subject
    def service = new ReservationServiceImpl()

    void setup() {
        service.reservationRepository = reservationRepository
        service.userRepository = userRepository
        service.roomRepository = roomRepository
    }

    def 'should process a room reservation'() {
        given: 'request to book a certain type of room'
        def request = new BookingRequest(SINGLE_ROOM, checkIn, checkOut)

        when: 'we ask the service to process the request'
        def response = service.book(userDetails, request)

        then: 'the service get the email of the customer registered in the system'
        1 * userDetails.getUsername() >> GUEST_EMAIL

        and: 'by the found email the service is looking for the user entity'
        1 * userRepository.findFirstByEmailIgnoreCase(GUEST_EMAIL) >> Optional.of(guest)

        and: 'looking for a free room of the right type'
        1 * roomRepository.findAvailableRooms(SINGLE_ROOM, checkIn, checkOut) >> databaseResult

        and: 'create and save a booking request in the reservation repository'
        1 * reservationRepository.save({
            it.guest === guest
            it.room === room
            it.checkIn === checkIn
            it.checkOut === checkOut
        }) >> new Reservation(id: RESERVATION_ID, guest: guest, room: room, checkIn: checkIn, checkOut: checkOut)

        and: 'the booking confirmation contains expected data'
        with(response) {
            guest() == GUEST_NAME
            email() == GUEST_EMAIL
            room() == ROOM_NUMBER
            it.checkIn() == checkIn
            it.checkOut() == checkOut
            reservationId() == RESERVATION_ID
        }

        where:
        checkIn = LocalDate.EPOCH
        checkOut = checkIn.plusDays(1)
    }
}
