/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fileprocessing.saveimagedb.controller;

import com.fileprocessing.saveimagedb.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author erick.karanja
 */
@RestController
@RequestMapping("/image")
public class FileRecordController {

    @Autowired
    private FileRecordService fileRecordService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

        String fileUploadResponse = fileRecordService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(fileUploadResponse);

    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] fileData = fileRecordService.downloadFile(fileName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(fileData);

    }

}
