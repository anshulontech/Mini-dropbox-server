package com.typeface.dropbox.utils;

public enum StatusCode {
  SUCCESS(200, "SUCCESS"),
  CREATED(201, "CREATED"),
  PROCESSING_ERROR(500, "PROCESSING ERROR"),
  FILE_ALREADY_PRESENT_ERROR(409, "Conflict"),
  FILE_NOT_FOUND(404, "FILE NOT FOUND"),
  BAD_REQUEST(400, "BAD REQUEST");

  private final int code;
  private final String desc;

  public int getCode() {
    return code;
  }

  public String getDesc() {
    return desc;
  }

  StatusCode(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  @Override
  public String toString() {
    return Integer.toString(code);
  }
}
