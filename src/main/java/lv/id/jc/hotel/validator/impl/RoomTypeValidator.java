package lv.id.jc.hotel.validator.impl;

import lv.id.jc.hotel.repository.RoomTypeRepository;
import lv.id.jc.hotel.validator.RoomTypeId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoomTypeValidator implements ConstraintValidator<RoomTypeId, Long> {
    @Autowired
    private RoomTypeRepository repository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return repository.existsById(value);
    }
}
