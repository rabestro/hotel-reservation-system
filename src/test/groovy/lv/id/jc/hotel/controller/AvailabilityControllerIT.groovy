package lv.id.jc.hotel.controller

import lv.id.jc.hotel.TestConfig
import lv.id.jc.hotel.config.AppConfig
import lv.id.jc.hotel.model.dto.AvailabilityRequest
import lv.id.jc.hotel.model.dto.AvailabilityResponse
import lv.id.jc.hotel.service.AvailabilityService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Import([TestConfig])
@ActiveProfiles('test')
@WebMvcTest(AvailabilityController)
class AvailabilityControllerIT extends Specification {
    @Autowired
    MockMvc mockMvc

    @SpringBean
    AvailabilityService service = Mock() {
        it.getAvailability(_ as AvailabilityRequest) >> [
                new AvailabilityResponse(1, 'Single Room', 12)
        ]
    }

    def 'should get rooms availability'() {
        given: 'request with correct data'
        def content = '{"checkIn": "3022-05-22", "checkOut": "3022-05-25"}'

        expect: 'request processed successfully'
        mockMvc.perform(
                get('/availability')
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
    }

    def 'should validate availability request'() {
        given: 'request with invalid data'
        def content = /{"checkIn": $checkIn, "checkOut": $checkOut}/ as String

        when: 'we make a request with bad data'
        def result = mockMvc.perform(get('/availability')
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON).content(content))
                .andReturn()

        then: 'response status is a bad request'
        result.response.status == HttpStatus.BAD_REQUEST.value()

        and: 'there is an error message in the response'
        result.response.contentAsString.contains message

        where:
        checkIn        | checkOut       | message
        null           | null           | 'must not be null'
        /"3022-02-01"/ | null           | 'must not be null'
        null           | /"3022-02-02"/ | 'must not be null'
        /"3022-02-02"/ | /"3022-01-01"/ | 'checkIn must be before checkOut'
        /"abc"/        | /"def"/        | ''
        /"1800-01-01"/ | /"2030-05-13"/ | 'must be a date in the present or in the future'

    }
}
