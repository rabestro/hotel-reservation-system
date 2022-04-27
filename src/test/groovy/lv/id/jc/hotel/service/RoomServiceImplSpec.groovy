package lv.id.jc.hotel.service

import lv.id.jc.hotel.model.Room
import lv.id.jc.hotel.repository.RoomRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title('Room Service Implementation')
class RoomServiceImplSpec extends Specification {
    static NEW_ROOM = new Room(id: null, number: '101', description: 'Single Room')
    static SINGLE_ROOM = new Room(id: 1, number: '101', description: 'Single Room')
    static DOUBLE_ROOM = new Room(id: 2, number: '102', description: 'Double Room')

    def roomRepository = Mock(RoomRepository) {
        findAll() >> [SINGLE_ROOM, DOUBLE_ROOM]
    }

    @Subject
    def roomService = new RoomServiceImpl(roomRepository)


    def 'should save a room entity'() {
        when:
        roomService.save(room)

        then:
        1 * roomRepository.save(room)

        where:
        room << [NEW_ROOM, SINGLE_ROOM, DOUBLE_ROOM]
    }

    def 'should find all rooms'() {
        when:
        roomService.findAll()

        then:
        1 * roomRepository.findAll()
    }

    def 'should find a room by room number'() {
        when:
        roomService.findByNumber(number)

        then:
        1 * roomRepository.findFirstByNumber(number)

        where:
        number << ['101', '2A', 'Smoking Room']
    }

    def 'should get a room by id'() {
        when:
        roomService.get(id)

        then:
        1 * roomRepository.findById(id)

        where:
        id << [1L, 3L, 57L]
    }

    def 'should delete a room'() {
        when:
        roomService.delete(room)

        then:
        1 * roomRepository.delete(room)

        where:
        room << [SINGLE_ROOM, DOUBLE_ROOM]
    }
}
