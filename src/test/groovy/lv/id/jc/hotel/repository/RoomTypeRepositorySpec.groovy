package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlMergeMode
import spock.lang.Issue
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import java.time.LocalDate

@DataJpaTest
@Sql("/types.sql")
@Title("Room type repository")
class RoomTypeRepositorySpec extends Specification {

    @Subject
    @Autowired
    RoomTypeRepository repository

    def "should find a room type by type name"() {
        when: "we search the room type by name"
        def roomType = repository.findFirstByName(type_name)

        then: "we found the room type"
        roomType.isPresent()

        and: "the room type name is as we expect"
        roomType.get().getName() == type_name

        where: "the room type names presented in the database"
        type_name << ['Single Room', 'Double Room', 'Deluxe Double Room']
    }

    def "should return an empty object for wrong type name"() {
        when: "we search the room type by wrong type name"
        def roomType = repository.findFirstByName(type_name)

        then: "we get an empty object"
        roomType.isEmpty()

        where: "the type names don't exist in the database"
        type_name << ['Single', 'Triple Room', 'Deluxe Single Room']
    }

    @Issue('36')
    @SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
    @Sql(['/users.sql', '/rooms.sql', '/reservations.sql'])
    def "should return rooms availability during the specified period"() {
        when: "we request rooms availability for a period"
        def result = repository.getAvailability checkIn, checkOut

        then: "the query is processed successfully and we get a non-empty result"
        result

        and: 'we get ids for available room types'
        result*.typeId() == availableTypes

        and: 'we get the number of free rooms of each type'
        result*.availableRooms() == availableRooms

        where:
        arrivalDate  | departureDate | availableTypes | availableRooms
        '2022-05-22' | '2022-05-26'  | [1, 2, 3]      | [3, 3, 2]
        '2022-06-01' | '2022-06-02'  | [1, 2, 3]      | [2, 3, 2]
        '2022-06-01' | '2022-06-03'  | [1, 2, 3]      | [2, 3, 2]
        '2022-06-02' | '2022-06-03'  | [1, 2, 3]      | [2, 3, 2]
        '2022-06-02' | '2022-06-04'  | [1, 2, 3]      | [1, 3, 2]
        '2022-06-02' | '2022-06-04'  | [1, 2, 3]      | [1, 3, 2]
        '2022-06-01' | '2022-06-05'  | [1, 2, 3]      | [1, 3, 2]
        '2022-06-01' | '2022-06-06'  | [2, 3]         | [3, 2]
        '2022-06-07' | '2022-06-09'  | [1, 2, 3]      | [1, 3, 2]
        '2022-06-09' | '2022-06-11'  | [1, 2, 3]      | [3, 3, 2]

        and:
        checkIn = LocalDate.parse arrivalDate
        checkOut = LocalDate.parse departureDate
    }

}
