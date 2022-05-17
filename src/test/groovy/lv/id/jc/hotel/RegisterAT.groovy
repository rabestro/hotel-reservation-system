package lv.id.jc.hotel

import lv.id.jc.hotel.model.dto.Credentials
import lv.id.jc.hotel.model.dto.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
@ActiveProfiles('test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegisterAT extends Specification {

    @LocalServerPort
    int port

    @Autowired
    TestRestTemplate restTemplate

    def 'should register a customer'() {
        given:
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

    def 'should reject creating a user with an existing mail address'() {
        given:
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
    }

    def 'should reject invalid user details and password'() {

    }

    String getURL() {
        "http://localhost:$port/register"
    }

}
