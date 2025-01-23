package com.typeface.dropbox.exception;

import com.typeface.dropbox.dto.ApiResponse;
import com.typeface.dropbox.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice
public class DropboxExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ApiResponse errorResponse = new ApiResponse.Builder().errorMsg(ex.getMessage())
        .status(StatusCode.PROCESSING_ERROR.getDesc())
        .statusCode(StatusCode.PROCESSING_ERROR.getCode()).build();

    // Returning the response with Internal Server Error status
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(FileAlreadyPresentException.class)
  public final ResponseEntity<Object> handleFileAlreadyPresentException(Exception ex,
      WebRequest request) {
    ApiResponse errorResponse = new ApiResponse.Builder().errorMsg(ex.getMessage())
        .status(StatusCode.FILE_ALREADY_PRESENT_ERROR.getDesc())
        .statusCode(StatusCode.FILE_ALREADY_PRESENT_ERROR.getCode()).build();

    // Returning the response with Conflict Error status
    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(FileNotFoundException.class)
  public final ResponseEntity<Object> handleFileNotPresentException(Exception ex,
      WebRequest request) {
    ApiResponse errorResponse = new ApiResponse.Builder().errorMsg(ex.getMessage())
        .status(StatusCode.FILE_NOT_FOUND.getDesc()).statusCode(StatusCode.FILE_NOT_FOUND.getCode())
        .build();

    // Returning the response with Not found Error status
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
