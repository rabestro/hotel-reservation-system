package lv.id.jc.hotel.controller

import lv.id.jc.hotel.TestConfig
import lv.id.jc.hotel.model.User
import lv.id.jc.hotel.service.UserService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import([TestConfig])
@ActiveProfiles('test')
@WebMvcTest(RegistrationController)
class RegistrationControllerSpec extends Specification {
    @Autowired
    MockMvc mockMvc
    @SpringBean
    UserService userService = Mock()

    @Ignore("Need to change status code")
    def 'should register a customer'() {
        given: 'request with correct data'
        def content = /{"name": "$name", "email": "$email", "password": "$password"}/

        when: 'request processed successfully'
        def response = mockMvc.perform(post('/register')
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(content))

        then: 'the request is passed to the service with the correct data'
        1 * userService.createUser({
            it.name() == name
            it.email() == email
            it.password() == password
        }, User.Role.CUSTOMER)

        and:
        response.andExpect(status().isOk())

        where:
        name               | email              | password
        'Marsha Preyscott' | 'marsha@guest.com' | 'MarPrey!09'
    }

    def 'should validate registration request'() {
        given: 'request with invalid data'
        def content = /{"name": "$name", "email": "$email", "password": "$password"}/

        when: 'we make a request with bad data'
        def response = mockMvc.perform(post('/register')
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(content))

        then: 'service is not called'
        0 * userService.createUser(_, _)

        and: 'response status is a bad request'
        response.andExpect(status().isBadRequest())

        where:
        name               | email              | password
        '    '             | 'marsha@guest.com' | 'MarPrey!09'
        'Marsha Preyscott' | 'marsha@guest.com' | ' '
        'Marsha Preyscott' | 'marsha@guest.com' | '1234567'
    }
}
