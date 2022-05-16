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

    def room = Stub(Room) {
        getNumber() >> ROOM_NUMBER
        getType() >> Stub(RoomType)
    }
    def guest = Stub(User) {
        getRole() >> User.Role.CUSTOMER
        getName() >> GUEST_NAME
        getEmail() >> GUEST_EMAIL
    }
    def userDetails = Mock UserDetails
    def userRepository = Mock UserRepository
    def roomRepository = Mock RoomRepository
    def reservationRepository = Mock ReservationRepository

    def checkIn = LocalDate.EPOCH
    def checkOut = checkIn.plusDays(1)

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
        def reservationDetails = service.book(userDetails, request)

        then: 'the service get the email of the customer registered in the system'
        1 * userDetails.getUsername() >> GUEST_EMAIL

        and: 'by the found email the service is looking for the user entity'
        1 * userRepository.findFirstByEmailIgnoreCase(GUEST_EMAIL) >> Optional.of(guest)

        and: 'looking for a free room of the right type and one room is found'
        1 * roomRepository.findAvailableRooms(SINGLE_ROOM, checkIn, checkOut) >> Stub(Streamable) {
            stream() >> Stream.of(room)
        }

        and: 'create and save a booking request in the reservation repository'
        1 * reservationRepository.save({
            it.guest === guest
            it.room === room
            it.checkIn === checkIn
            it.checkOut === checkOut
        }) >> new Reservation(id: RESERVATION_ID, guest: guest, room: room, checkIn: checkIn, checkOut: checkOut)

        and: 'the booking confirmation contains expected data'
        with(reservationDetails) {
            guest() == GUEST_NAME
            email() == GUEST_EMAIL
            room() == ROOM_NUMBER
            it.checkIn() == checkIn
            it.checkOut() == checkOut
            reservationId() == RESERVATION_ID
        }
    }

    def 'should throw an exception if no free room is found'() {
        given: 'request to book a certain type of room'
        def request = new BookingRequest(SINGLE_ROOM, checkIn, checkOut)

        when: 'we ask the service to process the request'
        service.book(userDetails, request)

        then: 'the guest entity is found'
        1 * userRepository.findFirstByEmailIgnoreCase(_) >> Optional.of(guest)

        and: 'looking for a free room and no room is found'
        1 * roomRepository.findAvailableRooms(SINGLE_ROOM, checkIn, checkOut) >> Stub(Streamable) {
            stream() >> Stream.empty()
        }

        and: 'an exception is thrown'
        def exception = thrown(NoSuchElementException)

        and: 'the reason is given in the error message'
        exception.message =~ 'no available rooms'
    }

    def 'should throw an exception if no customer'() {
        given: 'request to book a certain type of room'
        def request = new BookingRequest(SINGLE_ROOM, checkIn, checkOut)

        when: 'we ask the service to process the request'
        service.book(userDetails, request)

        then: 'the guest entity is not found'
        1 * userRepository.findFirstByEmailIgnoreCase(_) >> Optional.empty()

        and: 'an exception is thrown'
        def exception = thrown(NoSuchElementException)

        and: 'the reason is given in the error message'
        exception.message =~ 'Only registered customers can book a room'
    }

}
