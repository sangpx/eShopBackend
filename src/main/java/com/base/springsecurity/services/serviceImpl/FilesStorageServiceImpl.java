package com.base.springsecurity.services.serviceImpl;

import com.base.springsecurity.services.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");


    //Tao ra folder root neu nhu no khong ton tai
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        }catch (Exception e) {
            throw  new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public boolean save(MultipartFile file) {
        try {
            //resolve tuong ung voi path folder
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println("Error save file!" + e.getMessage());
            return false;
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            //resolve tuong ung voi path folder
           Path file = root.resolve(fileName);
           Resource resource = new UrlResource(file.toUri());
           if(resource.exists() || resource.isReadable()) {
               return  resource;
           } else {
               throw new RuntimeException("Could not read the file!");
           }
        }catch (Exception e) {
            throw new RuntimeException("Error load file!" + e.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String fileName) {
        try{
            Path file = root.resolve(fileName);
            return Files.deleteIfExists(file);
        } catch (Exception e) {
            throw new RuntimeException("Error! " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files
                    .walk(this.root, 1)
                    .filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load all files!");
        }
    }
}
