package lv.id.jc.hotel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Reservation extends AbstractAuditable<User, Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private User guest;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate checkIn;

    @Future
    @Column(nullable = false)
    private LocalDate checkOut;
}
