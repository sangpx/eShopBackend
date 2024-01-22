package com.base.springsecurity.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void  init();
    public void save(MultipartFile file);
    public Resource load(String fileName);
    public boolean deleteFile(String fileName);
    public void deleteAll();
    public Stream<Path> loadAll();

}
