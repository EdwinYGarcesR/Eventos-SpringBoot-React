package co.unicauca.presentation.rest.exception;

public class TokenErrorInvalid extends Exception {
  public String token;

  public TokenErrorInvalid(String token) {
    super("Token formato invalido: " + token);
  }
}
