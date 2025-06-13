package com.OracleResults.OracleResults.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.OracleResults.OracleResults.service.ExcelFileUploadService;

@RestController
@RequestMapping("/api/excel")
public class ExcellLoadController {

    @Autowired
    private ExcelFileUploadService excelFileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            excelFileUploadService.importarDesdeExcel(file.getInputStream(), file.getOriginalFilename());
            return ResponseEntity.ok("Archivo subido con exito");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al subir el archivo");
        }
    }
}