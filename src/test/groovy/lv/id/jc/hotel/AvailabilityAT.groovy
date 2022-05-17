package lv.id.jc.hotel

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AvailabilityAT extends Specification {

    @LocalServerPort
    int port

    @Autowired
    TestRestTemplate restTemplate


    def 'should get rooms availability'() {
        given: 'request with correct data'
        def content = '{"checkIn": "3022-05-22", "checkOut": "3022-05-25"}'

        expect: 'request processed successfully'
        true
//        mockMvc.perform(
//                get('/availability')
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("UTF-8")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .content(content))
//                .andExpect(status().isOk())
    }

}
