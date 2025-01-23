package com.typeface.dropbox.service.impl;

import com.typeface.dropbox.dto.FileInfo;
import com.typeface.dropbox.dto.FileResponse;
import com.typeface.dropbox.exception.FileAlreadyPresentException;
import com.typeface.dropbox.exception.FileNotFoundException;
import com.typeface.dropbox.repository.FileInfoRepo;
import com.typeface.dropbox.service.FileHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("com.typeface.dropbox.service.impl.FileHandlerServiceImplementation")
public class FileHandlerServiceImplementation implements FileHandlerService {

  @Autowired
  @Qualifier("com.typeface.dropbox.repository.FileInfoRepo")
  private FileInfoRepo fileInfoRepo;

  @Value("${file.local.dir}")
  private String localFileDir;

  @Override
  public String uploadFile(MultipartFile file) throws IOException, FileAlreadyPresentException {
    String filePath = localFileDir + file.getOriginalFilename();

    if (new File(filePath).exists()) {
      throw new FileAlreadyPresentException("File Already exist with given name");
    }

    fileInfoRepo.save(
        new FileInfo.Builder().name(file.getOriginalFilename()).type(file.getContentType())
            .path(filePath).build());

    file.transferTo(new File(filePath));

    return "File uploaded successfully.";
  }

  @Override
  public FileResponse downloadFile(String fileName) throws IOException, FileNotFoundException {
    Optional<FileInfo> fileInfo = fileInfoRepo.findByName(fileName);
    if (fileInfo.isEmpty()) {
      throw new FileNotFoundException("No file with name: " + fileName);
    }
    FileInfo file = fileInfo.get();
    return new FileResponse.Builder().contentType(file.getType())
        .byteResponse(Files.readAllBytes(new File(file.getPath()).toPath())).build();
  }

  @Override
  public List<String> getAllFiles() {
    List<FileInfo> allFilesLst = fileInfoRepo.findAll();

    List<String> allFileName = new ArrayList<>();

    allFilesLst.stream().map(FileInfo::getName).forEach(allFileName::add);

    return allFileName;
  }
}
