package lv.id.jc.hotel.service.impl

import lv.id.jc.hotel.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("UserDetails Service")
class UserDetailsServiceImplSpec extends Specification {

    def expectedUser = Mock(UserDetails)
    def userRepository = Mock(UserRepository)

    @Subject
    def userDetailsService = new UserDetailsServiceImpl(userRepository)

    def 'should load a user by username'() {
        when: 'we load a user by his username'
        def user = userDetailsService.loadUserByUsername(username)

        then: 'the table of users is searched by e-mail in a case-insensitive manner'
        1 * userRepository.findFirstByEmailIgnoreCase(username) >> Optional.of(expectedUser)

        and: 'we get the expected user'
        user === expectedUser

        where: 'there are users with these email addresses'
        username << ['peter.mcdermott@hotel.com', 'marsha@guest.com']
    }

    def 'should throw an exception for non-existent users'() {
        when: 'we are trying to load a user by a non-existent username'
        userDetailsService.loadUserByUsername(username)

        then: 'the repository tries to search by email address and returns an empty object'
        1 * userRepository.findFirstByEmailIgnoreCase(username) >> Optional.empty()

        and: 'an exception is thrown'
        def message = thrown(UsernameNotFoundException)

        and: 'the reason is given in the error message'
        message =~ "Not found: $username"

        where: 'non-existent usernames such'
        username << ['peter', 'marsha@hot-girl']
    }
}
