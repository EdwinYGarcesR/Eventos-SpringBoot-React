package co.unicauca.domain.model;

public class Comment {
  private String text;
  private String id;
  private String photoUrl;
  private String date;
  private String name;
  private String uid;

  public Comment(String text, String id, String photoUrl, String date, String name, String uid) {
    this.text = text;
    this.id = id;
    this.photoUrl = photoUrl;
    this.date = date;
    this.name = name;
    this.uid = uid;
  }

  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPhotoUrl() {
    return this.photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUid() {
    return this.uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

}
