package az.company.feedback.exception.handler;

import az.company.feedback.exception.CustomFeignException;
import az.company.feedback.exception.FeedbackExistsException;
import az.company.feedback.exception.NotFoundException;
import az.company.feedback.exception.model.ErrorResponse;
import az.company.feedback.model.enums.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeedbackExistsException.class)
    public ResponseEntity<ErrorResponse> handleFeedbackExistsException(FeedbackExistsException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(ErrorMessages.FEEDBACK_EXISTS.getMessage())
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(ErrorMessages.RESOURCE_NOT_FOUND.getMessage())
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponse> handleCustomFeignException(CustomFeignException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_GATEWAY.value())
                .error(ErrorMessages.FEIGN_CLIENT_ERROR.getMessage())
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ErrorMessages.INTERNAL_SERVER_ERROR.getMessage())
                .message(ex.getMessage())
                .timestamp(OffsetDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
