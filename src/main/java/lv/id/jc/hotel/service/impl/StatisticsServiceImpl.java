package lv.id.jc.hotel.service.impl;

import lombok.RequiredArgsConstructor;
import lv.id.jc.hotel.model.dto.DailyStatistics;
import lv.id.jc.hotel.model.dto.StatisticsRequest;
import lv.id.jc.hotel.repository.ReservationRepository;
import lv.id.jc.hotel.repository.RoomRepository;
import lv.id.jc.hotel.service.StatisticsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public List<DailyStatistics> getStatistics(StatisticsRequest request) {
        final var totalRooms = roomRepository.count();

        Function<LocalDate, DailyStatistics> collectDailyStatistics = date -> {
            var busy = reservationRepository.countBusyRooms(date);
            return new DailyStatistics(date, totalRooms - busy, busy);
        };
        return request
                .startDate()
                .datesUntil(request.endDate().plusDays(1))
                .map(collectDailyStatistics)
                .toList();
    }

}
