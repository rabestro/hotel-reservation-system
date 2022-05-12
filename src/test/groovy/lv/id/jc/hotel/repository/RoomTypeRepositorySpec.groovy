package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate

@DataJpaTest
@Title("Room's type repository")
class RoomTypeRepositorySpec extends Specification {

    @Subject
    @Autowired
    RoomTypeRepository repository

    @Sql("/types.sql")
    def "should find a room type by type's name"() {
        when: "we search the room type by name"
        def roomType = repository.findFirstByName(type_name)

        then: "we found the room's type"
        roomType.isPresent()

        and: "the room's type name as we expect"
        roomType.get().getName() == type_name

        where: "the type names presented in the database"
        type_name << ['Single Room', 'Double Room', 'Deluxe Double Room']
    }

    @Sql("/types.sql")
    def "should return an empty object for wrong type name"() {
        when: "we search the room type by wrong type name"
        def roomType = repository.findFirstByName(type_name)

        then: "we get an empty object"
        roomType.isEmpty()

        where: "the type names doesn't presented in the database"
        type_name << ['Single', 'Triple Room', 'Deluxe Single Room']
    }

    @Sql("/rooms.sql")
    def "should return rooms availability on the specified period"() {
        when: "we request rooms availability for a period"
        def result = repository.getAvailability checkIn, checkOut

        result.forEach({ println it })

        then: "the query processed successfully and we got a result"
        result

        and: 'we got ids for available room types'
        result*.typeId() == availableTypes

        and: 'we got the number of free rooms of each type'
        result*.availableRooms() == availableRooms

        where:
        arrivingDate | departurDate | availableTypes | availableRooms
        '2022-05-22' | '2022-05-26' | [1, 2, 3]      | [3, 3, 2]

        and:
        checkIn = LocalDate.parse arrivingDate
        checkOut = LocalDate.parse departurDate
    }

}
