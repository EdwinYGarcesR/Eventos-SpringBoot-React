package co.unicauca.presentation.rest.exception;

public class EventRequestError {
  public EnumErrorCode code;
  public String field;
  public String message;

  public EventRequestError(EnumErrorCode code, String field, String message) {
    this.code = code;
    this.field = field;
    this.message = message;
  }
}
