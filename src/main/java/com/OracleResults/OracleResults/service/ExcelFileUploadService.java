
package com.OracleResults.OracleResults.service;

import com.OracleResults.OracleResults.dto.ItemOracle;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.OracleResults.OracleResults.repository.ItemRepository;

@Service
public class ExcelFileUploadService {

    @Autowired
    private ItemRepository itemRepository;

    public void importarDesdeExcel(InputStream inputStream, String fileName) {
        try {
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                throw new Exception("Formato de archivo no soportado");
            }
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<ItemOracle> items = new ArrayList<>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Saltar la cabecera
                }
                
                
            }
            itemRepository.saveAll(items);
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
