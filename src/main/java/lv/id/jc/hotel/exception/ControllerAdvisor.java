package lv.id.jc.hotel.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return getErrorResponseEntity(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return getErrorResponseEntity(ex, request);
    }

    @NotNull
    private ResponseEntity<Object> getErrorResponseEntity(BindException ex, WebRequest request) {
        var description = request.getDescription(false);
        var errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(FieldError::toString)
                .toList();
        var body = new ErrorResponse(Instant.now(), description, errors);
        return ResponseEntity.badRequest().body(body);
    }
}

record ErrorResponse(Instant timestamp, String description, List<String> errors) {
}