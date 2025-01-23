package com.typeface.dropbox.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FILE_INFO")
public class FileInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String type;
  private String path;

  public FileInfo() {
  }

  public FileInfo(Long id, String name, String type, String path) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.path = path;
  }

  private FileInfo(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.type = builder.type;
    this.path = builder.path;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getPath() {
    return path;
  }

  public static class Builder {
    private Long id;
    private String name;
    private String type;
    private String path;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public FileInfo build() {
      return new FileInfo(this);
    }
  }

}
