package co.unicauca.events.presentation.rest.exception;

public class ResourceNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;
  public String id;

  public ResourceNotFoundException(String id) {
    super("There is no Resource with id: " + id);
  }
}
