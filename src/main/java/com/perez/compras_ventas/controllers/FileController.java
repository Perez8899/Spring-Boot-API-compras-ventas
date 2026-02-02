package com.perez.compras_ventas.controllers;

import com.perez.compras_ventas.common.dto.FileDownloadResponse;
import com.perez.compras_ventas.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping
    public ResponseEntity<Resource> retrieveFile(@RequestParam String filePath){

        FileDownloadResponse downloadResponse = fileService.fileDownload(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(downloadResponse.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename="+downloadResponse.getFileName())
                .body(downloadResponse.getResource());
    }
}
