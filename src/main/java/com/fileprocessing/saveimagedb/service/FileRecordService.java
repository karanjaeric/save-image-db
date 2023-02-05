/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fileprocessing.saveimagedb.service;

import com.fileprocessing.saveimagedb.entity.FileRecord;
import com.fileprocessing.saveimagedb.repository.FileRecordRepository;
import com.fileprocessing.saveimagedb.util.FileProcessingUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author erick.karanja
 */
@Service
@RequiredArgsConstructor
public class FileRecordService {

    @Autowired
    private final FileRecordRepository fileRecordRepository;

    public String uploadFile(MultipartFile file) {
        try {
            FileRecord fileRecord = FileRecord.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(FileProcessingUtil.compressFile(file.getBytes()))
                    .build();
            fileRecordRepository.save(fileRecord);
            return "File Uploaded Successfully " + file.getOriginalFilename();
        } catch (IOException ex) {
            Logger.getLogger(FileRecordService.class.getName()).log(Level.SEVERE, null, ex);
            return "An error occured";
        }
    }

    public byte[] downloadFile(String fileName) {
        FileRecord fileRecord = fileRecordRepository.findByName(fileName);
        byte[] imageBytes = FileProcessingUtil.decompressFile(fileRecord.getData());
        return imageBytes;

    }
}
