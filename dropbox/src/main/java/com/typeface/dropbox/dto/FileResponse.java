package com.typeface.dropbox.dto;

public class FileResponse {
  private byte[] byteResponse;
  private String contentType;

  public byte[] getByteResponse() {
    return byteResponse;
  }

  public String getContentType() {
    return contentType;
  }

  private FileResponse(Builder builder) {
    this.byteResponse = builder.byteResponse;
    this.contentType = builder.contentType;
  }

  public static class Builder {
    private byte[] byteResponse;
    private String contentType;

    public Builder byteResponse(byte[] byteResponse) {
      this.byteResponse = byteResponse;
      return this;
    }

    public Builder contentType(String contentType) {
      this.contentType = contentType;
      return this;
    }

    public FileResponse build() {
      return new FileResponse(this);
    }
  }
}
