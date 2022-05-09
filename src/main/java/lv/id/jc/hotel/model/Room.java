package lv.id.jc.hotel.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lv.id.jc.hotel.validator.RoomNumber;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Table(name = "ROOM", uniqueConstraints = @UniqueConstraint(columnNames = "NUMBER"))
public class Room extends AbstractAuditable<User, Long> {

    @RoomNumber
    @Column(unique = true, length = 20, nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private RoomType type;

    @OrderBy("checkIn")
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "Room{" + "number='" + number + '\'' + ", type=" + type.getName() + '}';
    }
}
