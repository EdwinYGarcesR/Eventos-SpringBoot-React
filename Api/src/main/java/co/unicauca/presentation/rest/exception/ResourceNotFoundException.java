package co.unicauca.presentation.rest.exception;

public class ResourceNotFoundException extends Exception {
  public String id;

  public ResourceNotFoundException(String id) {
    super("There is no Resource with id: " + id);
  }
}
