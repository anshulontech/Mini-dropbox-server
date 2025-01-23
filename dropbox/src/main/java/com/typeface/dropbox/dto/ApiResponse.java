package com.typeface.dropbox.dto;

import lombok.Data;

@Data
public class ApiResponse {
  private String status;
  private Integer statusCode;
  private Object result;
  private String errorMsg;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public Object getResult() {
    return result;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  private ApiResponse(Builder builder) {
    this.status = builder.status;
    this.statusCode = builder.statusCode;
    this.result = builder.result;
    this.errorMsg = builder.errorMsg;
  }

  public static class Builder {
    private String status;
    private Integer statusCode;
    private Object result;
    private String errorMsg;

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder statusCode(Integer statusCode) {
      this.statusCode = statusCode;
      return this;
    }

    public Builder result(Object result) {
      this.result = result;
      return this;
    }

    public Builder errorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
      return this;
    }

    public ApiResponse build() {
      return new ApiResponse(this);
    }
  }
}
