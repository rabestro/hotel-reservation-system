package lv.id.jc.hotel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lv.id.jc.hotel.validator.RoomNumber;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@ToString
@Table(name = "ROOM", uniqueConstraints = @UniqueConstraint(columnNames = "NUMBER"))
public class Room extends AbstractAuditable<User, Long> {

    @RoomNumber
    @Column(unique = true, length = 20, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private RoomType type;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
}
