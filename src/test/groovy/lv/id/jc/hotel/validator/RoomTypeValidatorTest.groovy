package lv.id.jc.hotel.validator

import lv.id.jc.hotel.repository.RoomTypeRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import javax.validation.ConstraintValidatorContext

@Title('Validator for Room Type ID')
class RoomTypeValidatorTest extends Specification {

    final ROOM_TYPE_ID = 27L
    def repository = Stub RoomTypeRepository

    @Subject
    def roomTypeValidator = new RoomTypeValidator(repository)

    def 'should validate existing room type id'() {

        when: 'the given id exists in the database'
        repository.existsById(ROOM_TYPE_ID) >> true

        then: 'the validator returns true'
        roomTypeValidator.isValid(ROOM_TYPE_ID, _ as ConstraintValidatorContext)
    }

    def 'should reject non existing room type id'() {

        when: "the given id doesn't exist in the database"
        repository.existsById(ROOM_TYPE_ID) >> false

        then: 'the validator returns false'
        !roomTypeValidator.isValid(ROOM_TYPE_ID, _ as ConstraintValidatorContext)
    }

}
