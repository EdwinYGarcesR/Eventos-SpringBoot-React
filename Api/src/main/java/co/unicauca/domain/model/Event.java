package co.unicauca.domain.model;

public class Event {
  private Double score; // Valoracion
  private String description;
  private String end; // Fecha de finalizacion
  private String id;
  private String imageUrl;
  private String location;
  private String name; // Nombre
  private String start; // Fecha de inicio
  private String type;
  private User owner;

  public Event(Double score, String description, String end, String id, String imageUrl, String location, String name,
      String start, String type, User owner) {
    this.score = score;
    this.description = description;
    this.end = end;
    this.id = id;
    this.imageUrl = imageUrl;
    this.location = location;
    this.name = name;
    this.start = start;
    this.type = type;
    this.owner = owner;
  }

  public Double getScore() {
    return this.score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEnd() {
    return this.end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStart() {
    return this.start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public User getOwner() {
    return this.owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

}
