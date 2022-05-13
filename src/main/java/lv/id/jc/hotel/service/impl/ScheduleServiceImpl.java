package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.projection.Schedule;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("scheduleServiceOld")
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ReservationRepository repository;

    @Override
    public List<Schedule> getSchedule(ScheduleRequest request) {
        return request
                .startDate()
                .datesUntil(request.endDate())
                .map(date -> getScheduleResponse(request.roomId(), date))
                .toList();
    }

    private Schedule getScheduleResponse(Long roomId, LocalDate date) {
        return repository.findReservation(roomId, date)
                .map(r -> new Schedule() {
                    @Override
                    public LocalDate getDate() {
                        return date;
                    }

                    @Override
                    public Long getBookId() {
                        return r.getId();
                    }

                    @Override
                    public Long getGuestId() {
                        return r.getGuest().getId();
                    }

                    @Override
                    public String getName() {
                        return r.getGuest().getName();
                    }

                    @Override
                    public String getEmail() {
                        return null;
                    }
                })
                .orElse(null);
    }
}
