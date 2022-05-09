package lv.id.jc.hotel.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

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

}
