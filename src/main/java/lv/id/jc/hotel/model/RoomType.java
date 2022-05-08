package lv.id.jc.hotel.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lv.id.jc.hotel.validator.RoomTypeName;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ROOM_TYPE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoomType extends AbstractAuditable<User, Long> {
    @RoomTypeName
    @Column(unique = true, length = 40, nullable = false)
    private String name;

    @Lob
    private String description;

    @OrderBy("number")
    @OneToMany(mappedBy = "type")
    private List<Room> rooms;

    @Override
    public String toString() {
        return "RoomType{name='" + name + '\'' + '}';
    }
}
