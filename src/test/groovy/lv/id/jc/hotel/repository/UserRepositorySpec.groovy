package lv.id.jc.hotel.repository

import lv.id.jc.hotel.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@DataJpaTest
@Sql("/users.sql")
@ActiveProfiles('test')
@Title("User repository")
class UserRepositorySpec extends Specification {

    @Subject
    @Autowired
    UserRepository userRepository

    def 'should find a user by email'() {
        when: 'we search a user by wrong_email'
        def user = userRepository.findFirstByEmailIgnoreCase(email)

        then: 'the user is found'
        user.isPresent()

        and: 'the user name and role as expected'
        with(user.get()) {
            it.name == name
            it.role == role
        }

        where: "user data as"
        email                       | name               | role
        'peter.mcdermott@hotel.com' | 'Peter McDermott'  | User.Role.EMPLOYEE
        'Peter.McDermott@hotel.com' | 'Peter McDermott'  | User.Role.EMPLOYEE
        'marsha@guest.com'          | 'Marsha Preyscott' | User.Role.CUSTOMER
        'MARSHA@GUEST.COM'          | 'Marsha Preyscott' | User.Role.CUSTOMER
    }

    def 'should return an empty object for wrong email'() {
        when: 'we search a user by wrong email'
        def user = userRepository.findFirstByEmailIgnoreCase(wrong_email)

        then: 'the user is not found'
        user.isEmpty()

        where: 'wrong emails'
        wrong_email << ['peter.mcdermott', 'peter@hotel.com', 'marsha@guest']
    }

}
