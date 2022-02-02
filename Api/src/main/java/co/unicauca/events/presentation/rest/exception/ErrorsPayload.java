package co.unicauca.events.presentation.rest.exception;

import java.util.List;

public class ErrorsPayload {
  public final List<EventRequestError> errors;

  public ErrorsPayload(List<EventRequestError> applicationErrors) {
    this.errors = applicationErrors;
  }
}
