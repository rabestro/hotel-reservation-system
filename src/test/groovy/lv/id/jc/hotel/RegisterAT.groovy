package lv.id.jc.hotel

import lv.id.jc.hotel.controller.RegistrationController
import lv.id.jc.hotel.model.dto.Credentials
import lv.id.jc.hotel.model.dto.Customer
import lv.id.jc.hotel.repository.UserRepository
import lv.id.jc.hotel.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import spock.lang.*

@Stepwise
@ActiveProfiles('test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Issue('9')
@Title('Customer Registration Acceptance Tests')
@Subject([RegistrationController, UserService, UserRepository])
class RegisterAT extends Specification {

    @LocalServerPort
    int port

    @Autowired
    TestRestTemplate restTemplate

    def 'register a customer with valid contact information'() {
        given: 'credentials of a new hotel customer'
        def credentials = new Credentials(name, email, password)

        when:
        def response = restTemplate.postForEntity(getURL(), credentials, Customer)

        then:
        response.statusCode === HttpStatus.CREATED

        and: 'customer email saved in lowercase'
        with(response.body) {
            it.name() == name
            it.email() == email.toLowerCase()
        }

        where:
        name               | email                    | password
        'Marsha Preyscott' | 'marsha@guest.com'       | 'MarPrey!09'
        'Albert Wells'     | 'albert.wells@guest.com' | 'ag1ngH0T@Lguest'
        "Curtis O'Keefe"   | 'CURTIS@GUEST.COM'       | 'Chain&O57'
    }

    def 'reject creating a user with an existing mail address'() {
        given: 'credentials with already registered email address'
        def credentials = new Credentials(name, duplicate_email, password)

        when:
        def response = restTemplate.postForEntity(getURL(), credentials, Customer)

        then:
        response.statusCode === HttpStatus.INTERNAL_SERVER_ERROR

        where:
        name               | duplicate_email    | password
        'Marsha Preyscott' | 'marsha@guest.com' | 'MarPrey!09'
        'Marsha'           | 'Marsha@Guest.com' | 'MARPrey!09'
        'Marsha Preyscott' | 'MARSHA@GUEST.COM' | 'MarPrey!09'
        "Curtis O'Keefe"   | 'curtis@guest.com' | 'Chain&O57'
    }

    @Unroll("reject #comment")
    def 'reject invalid credentials'() {
        given: 'credentials with invalid data'
        def credentials = new Credentials(name, email, password)

        when: 'we post invalid credentials'
        def response = restTemplate.postForEntity(getURL(), credentials, Customer)

        then: 'we receive bad request status'
        response.statusCode === HttpStatus.BAD_REQUEST

        where:
        name               | email              | password     | comment
        null               | 'marsha@guest.com' | 'MarPrey!09' | 'null name'
        ''                 | 'marsha@guest.com' | 'MarPrey!09' | 'empty name'
        'Marsha'           | null               | 'MARPrey!09' | 'null email'
        'Marsha Preyscott' | 'MARSHA@GUEST.COM' | ''           | 'empty password'
        'Marsha Preyscott' | 'MARSHA@GUEST.COM' | '12345'      | 'short password'
        'Marsha Preyscott' | 'MARSHA.guest.COM' | 'MARPrey!09' | 'invalid email address'
    }

    String getURL() {
        "http://localhost:$port/register"
    }

}
