package com.profeco.market.controller;

import com.profeco.market.dto.FileUploadResponse;
import com.profeco.market.service.StorageService;
import com.profeco.market.service.storage.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public ResponseEntity<FileUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                               RedirectAttributes redirectAttributes) {

        String destionationFile = storageService.store(file);

        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        fileUploadResponse.setFileName(file.getOriginalFilename());
        fileUploadResponse.setDownloadUri(destionationFile);
        fileUploadResponse.setSize(file.getSize());

        return ResponseEntity.ok(fileUploadResponse);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
