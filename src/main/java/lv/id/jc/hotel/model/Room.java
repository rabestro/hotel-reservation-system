package lv.id.jc.hotel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lv.id.jc.hotel.validator.RoomNumber;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "NUMBER"))
public class Room extends AbstractAuditable<User, Long> {

    @Column(unique = true)
    @RoomNumber
    private String number;

    private String description;
}
