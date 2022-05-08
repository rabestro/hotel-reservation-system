package lv.id.jc.hotel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends AbstractAuditable<User, Long> {
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;

    @FutureOrPresent
    private LocalDate checkIn;

    @Future
    private LocalDate checkOut;
}
