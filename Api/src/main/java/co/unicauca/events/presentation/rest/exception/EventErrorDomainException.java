package co.unicauca.events.presentation.rest.exception;

import java.util.List;

public class EventErrorDomainException extends Exception {
  public List<EventRequestError> errors;

  public EventErrorDomainException(List<EventRequestError> errors) {
    super("Errors: " + errors.toString());
  }
}
