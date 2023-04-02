package lv.id.jc.hotel.service.impl

import lv.id.jc.hotel.model.User
import lv.id.jc.hotel.model.dto.Credentials
import lv.id.jc.hotel.repository.UserRepository
import lv.id.jc.hotel.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("User Service")
class UserServiceImpSpec extends Specification {

    def userRepository = Mock UserRepository
    def passwordEncoder = Mock PasswordEncoder

    @Subject
    UserService userService = new UserServiceImpl(userRepository, passwordEncoder)

    def 'create and save a user entity'() {
        given: 'a user credentials'
        def credentials = new Credentials(name, email, rawPassword)

        when: 'we create a customer by given credentials and role'
        userService.createUser(credentials, role)

        then: 'the encoder is called to encrypt the user password'
        1 * passwordEncoder.encode(rawPassword) >> encodedPassword

        and: 'the user entity is created and persisted'
        1 * userRepository.save({
            it.isEnabled()
            it.role === role
            it.name == name
            it.password == encodedPassword
        })

        where:
        role               | name                | email                | rawPassword   | encodedPassword
        User.Role.CUSTOMER | 'Dorothy Lash'      | 'Dorothy@OKeefe.com' | 'Dodo'        | '****'
        User.Role.CUSTOMER | 'Marsha Preyscott'  | 'Marsha@guest.com'   | 'pry%688s'    | '@@@@@'
        User.Role.EMPLOYEE | 'Christine Francis' | 'chris@hotel.com'    | 'SuP@R5eCr@+' | '#####'
    }

}
