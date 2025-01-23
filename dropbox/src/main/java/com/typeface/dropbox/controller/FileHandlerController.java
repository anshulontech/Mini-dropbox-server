package com.typeface.dropbox.controller;

import com.typeface.dropbox.dto.ApiResponse;
import com.typeface.dropbox.dto.FileResponse;
import com.typeface.dropbox.exception.FileAlreadyPresentException;
import com.typeface.dropbox.exception.FileNotFoundException;
import com.typeface.dropbox.service.FileHandlerService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.typeface.dropbox.utils.StatusCode.CREATED;
import static com.typeface.dropbox.utils.StatusCode.SUCCESS;

@RestController
@RequestMapping("/api/files")
@Validated
public class FileHandlerController {

  @Autowired
  @Qualifier("com.typeface.dropbox.service.impl.FileHandlerServiceImplementation")
  private FileHandlerService fileHandlerService;

  @PostMapping("/upload")
  public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") @NotNull MultipartFile file)
      throws IOException, FileAlreadyPresentException {
    String uploadMsg = fileHandlerService.uploadFile(file);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(
        new ApiResponse.Builder().status(CREATED.getDesc()).statusCode(CREATED.getCode())
            .result(uploadMsg).build());
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<?> downloadFile(@PathVariable String fileName)
      throws IOException, FileNotFoundException {
    FileResponse fileResponse = fileHandlerService.downloadFile(fileName);
    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf(fileResponse.getContentType()))
        .body(fileResponse.getByteResponse());
  }

  @GetMapping(value = "/list/all",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
  public ResponseEntity<ApiResponse> getAllFilesName() {
    List<String> allFilesName = fileHandlerService.getAllFiles();

    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(
        new ApiResponse.Builder().status(SUCCESS.getDesc()).statusCode(SUCCESS.getCode())
            .result(allFilesName).build());
  }
}
