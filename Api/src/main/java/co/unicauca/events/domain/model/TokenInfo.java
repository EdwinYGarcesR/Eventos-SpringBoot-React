package co.unicauca.events.domain.model;

public class TokenInfo {
  private boolean enabled;
  private boolean validated;
  private String email;
  private String name;
  private String photoUrl;
  private String uid;

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isValidated() {
    return this.validated;
  }

  public void setValidated(boolean validated) {
    this.validated = validated;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhotoUrl() {
    return this.photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getUid() {
    return this.uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

}
