package com.typeface.dropbox.service;

import com.typeface.dropbox.dto.FileResponse;
import com.typeface.dropbox.exception.FileAlreadyPresentException;
import com.typeface.dropbox.exception.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileHandlerService {
  String uploadFile(MultipartFile file) throws IOException, FileAlreadyPresentException;

  FileResponse downloadFile(String fileName) throws IOException, FileNotFoundException;

  List<String> getAllFiles();
}
