package lv.id.jc.hotel.model.dto;

import lv.id.jc.hotel.model.Reservation;

import java.time.LocalDate;

public record ReservationDetails(Long reservationId, String guest, String email,
                                 String room, String type, String description,
                                 LocalDate checkIn, LocalDate checkOut) {
    public ReservationDetails(Reservation reservation) {
        this(
                reservation.getId(),
                reservation.getGuest().getName(),
                reservation.getGuest().getEmail(),
                reservation.getRoom().getNumber(),
                reservation.getRoom().getType().getName(),
                reservation.getRoom().getType().getDescription(),
                reservation.getCheckIn(),
                reservation.getCheckOut()
        );
    }
}
