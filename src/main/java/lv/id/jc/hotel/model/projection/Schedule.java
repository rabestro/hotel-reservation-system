package lv.id.jc.hotel.model.projection;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface Schedule {
    LocalDate getDate();

    Long getBookId();

    Long getGuestId();

    String getName();

    String getEmail();
}
