package lv.id.jc.hotel.model.projection;

import lv.id.jc.hotel.model.RoomType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "count", types = {RoomType.class})
public interface RoomTypeCount {

    @Value("#{target.name}")
    String getType();

    @Value("#{target.rooms.size()}")
    Long getCount();
}
