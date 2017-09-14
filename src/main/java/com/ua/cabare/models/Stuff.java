package com.ua.cabare.models;

import com.ua.cabare.domain.Position;

public class Stuff {

  private long id;
  private String name;
  private Position position;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }
}
