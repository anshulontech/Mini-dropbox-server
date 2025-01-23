package com.typeface.dropbox;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DropboxApplication {

  @Value("${file.local.dir}")
  private String localFileDir;

  public static void main(String[] args) {

    SpringApplication.run(DropboxApplication.class, args);
  }

  @Bean
  public String cleanBean() throws IOException {
    FileUtils.deleteDirectory(new File(localFileDir));
    new File(localFileDir).mkdirs();

    return "";
  }
}
