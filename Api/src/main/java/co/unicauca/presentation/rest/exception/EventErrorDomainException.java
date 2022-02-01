package co.unicauca.presentation.rest.exception;

import java.util.List;

public class EventErrorDomainException extends Exception {
  public List<EventRequestError> errors;
}
