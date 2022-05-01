package lv.id.jc.hotel.service.impl;

import lv.id.jc.hotel.model.dto.ScheduleRequest;
import lv.id.jc.hotel.model.dto.ScheduleResponse;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    ReservationRepository repository;

    @Override
    public List<ScheduleResponse> getSchedule(ScheduleRequest request) {
        return request
                .startDate()
                .datesUntil(request.endDate())
                .map(date -> getScheduleResponse(request.roomId(), date))
                .toList();
    }

    private ScheduleResponse getScheduleResponse(Long roomId, LocalDate date) {
        return repository.findReservation(roomId, date)
                .map(r -> new ScheduleResponse(date, r.getId(), r.getGuest().getName()))
                .orElse(new ScheduleResponse(date));
    }
}
