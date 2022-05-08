package lv.id.jc.hotel.repository

import lv.id.jc.hotel.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@DataJpaTest
@Title("User's repository")
class UserRepositorySpec extends Specification {

    @Subject
    @Autowired
    UserRepository userRepository

    @Sql("/users.sql")
    def 'should find a user by email'() {
        when: 'we search a user by email'
        def user = userRepository.findFirstByEmailIgnoreCase(email)

        then: 'the user is found'
        user.isPresent()

        and: 'the user name and role as expected'
        with(user.get()) {
            it.name == name
            it.role == role
        }

        where:
        email                       | name              | role
        'peter.mcdermott@hotel.com' | 'Peter McDermott' | User.Role.EMPLOYEE
        'Peter.McDermott@hotel.com' | 'Peter McDermott' | User.Role.EMPLOYEE
    }

    def "FindByRole"() {
    }
}
