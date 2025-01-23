package com.typeface.dropbox.repository;

import com.typeface.dropbox.dto.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("com.typeface.dropbox.repository.FileInfoRepo")
public interface FileInfoRepo extends JpaRepository<FileInfo, Long> {
  Optional<FileInfo> findByName(String fileName);
}
