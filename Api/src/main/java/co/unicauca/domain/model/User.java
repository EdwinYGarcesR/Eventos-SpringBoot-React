package co.unicauca.domain.model;

public class User {
  private String email;
  private String name;
  private String photoUrl;
  private String uid;

  public User(String email, String name, String photoUrl, String uid) {
    this.email = email;
    this.name = name;
    this.photoUrl = photoUrl;
    this.uid = uid;
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
