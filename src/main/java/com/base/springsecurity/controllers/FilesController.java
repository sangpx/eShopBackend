package com.base.springsecurity.controllers;

import com.base.springsecurity.models.dto.payload.response.MessageResponse;
import com.base.springsecurity.models.entity.FileInfo;
import com.base.springsecurity.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/files")
public class FilesController {
    @Autowired
    private FilesStorageService storageService;

    //Upload File
    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageResponse(message, true));
        } catch (Exception e) {
            message = "Error! Could not upload the file: " + file.getOriginalFilename()  + e.getMessage();
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new MessageResponse(message, false));
        }
    }

    //Get All Files
    @GetMapping("/getAllFiles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll()
            .map(path -> {
                    String fileName = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                        .fromMethodName(FilesController.class, "getFile",
                                path.getFileName().toString()).build().toString();
                    return new FileInfo(fileName, url);
                }
            ).collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fileInfos);
    }

    //Dowload File
    @GetMapping("/{fileName:.+}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        Resource file = storageService.load(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + file.getFilename() + "\"").body(file);
    }

    //Delete File
    @DeleteMapping("/deleteFile/{fileName:.+}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteFile(@PathVariable String fileName) {
        String message = "";
        try {
            boolean existedFile = storageService.deleteFile(fileName);
            if (existedFile) {
                message = "Delete the file successfully: " + fileName;
                return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new MessageResponse(message, true));
            }
            message = "The file does not exist!";
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse(message, false));
        } catch (Exception e) {
            message = "Error! Could not delete the file: " + fileName + e.getMessage();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(message, false));
        }
    }
}


