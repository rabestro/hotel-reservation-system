package lv.id.jc.hotel.service

import lv.id.jc.hotel.model.Room
import lv.id.jc.hotel.repository.RoomRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title('Room Service Implementation')
class RoomServiceImplSpec extends Specification {

    def roomRepository = Mock RoomRepository

    @Subject
    def roomService = new RoomServiceImpl(roomRepository)

    def 'should save a room entity'() {
        given:
        def room  = new Room(id: 1, number: '101', description: 'Single Room')

        when:
        roomService.save(room)

        then:
        1 * roomRepository.save(room)
    }

     def "FindAll"() {
    }

    def "FindByNumber"() {
    }

    def "Get"() {
    }

    def "Delete"() {
    }
}
