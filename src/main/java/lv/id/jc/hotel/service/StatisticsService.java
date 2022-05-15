package lv.id.jc.hotel.service;

import lv.id.jc.hotel.model.dto.DailyStatistics;
import lv.id.jc.hotel.model.dto.StatisticsRequest;

import java.util.List;

/**
 * See hotel availability statistics (how many rooms are free/busy) for a specified period.
 */
public interface StatisticsService {

    /**
     * Calculates hotel availability statistics
     *
     * @param request contains start and end dates
     * @return list of daily statistics from start till end dates
     */
    List<DailyStatistics> getStatistics(StatisticsRequest request);

}
