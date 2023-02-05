/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fileprocessing.saveimagedb.repository;

import com.fileprocessing.saveimagedb.entity.FileRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author erick.karanja
 */
public interface FileRecordRepository extends JpaRepository<FileRecord, Long> {

    FileRecord findByName(String fileName);

}
