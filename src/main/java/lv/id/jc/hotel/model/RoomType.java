package lv.id.jc.hotel.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.id.jc.hotel.validator.RoomTypeName;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ROOM_TYPE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoomType extends AbstractAuditable<User, Long> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @RoomTypeName
    @Column(unique = true, length = 40, nullable = false)
    private String name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Room> rooms;

    @Override
    public String toString() {
        return "RoomType{name='" + name + '\'' + '}';
    }
}
