package lv.id.jc.hotel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lv.id.jc.hotel.validator.RoomTypeName;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoomType extends AbstractAuditable<User, Long> {
    @RoomTypeName
    @Column(unique = true, length = 40, nullable = false)
    private String name;

    @Lob
    private String description;

    @OneToMany(mappedBy = "type")
    private Set<Room> rooms;
}
