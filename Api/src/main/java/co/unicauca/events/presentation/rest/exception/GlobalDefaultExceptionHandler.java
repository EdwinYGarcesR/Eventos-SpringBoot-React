package co.unicauca.events.presentation.rest.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.unicauca.events.domain.model.Event;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  @RequestMapping(produces = "application/json")
  @ResponseBody
  public ResponseEntity<ErrorsPayload> handleResourceNotFoundException(ResourceNotFoundException e) {
    List<EventRequestError> eventErrors = new ArrayList<>();
    eventErrors
        .add(new EventRequestError(EnumErrorCode.NOT_FOUND, Event.class.getSimpleName(), e.getMessage()));
    return new ResponseEntity<>(new ErrorsPayload(eventErrors), HttpStatus.NOT_FOUND);
  }
}
