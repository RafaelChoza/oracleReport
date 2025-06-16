package com.OracleResults.OracleResults.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.OracleResults.OracleResults.dto.ItemOracle;
import com.OracleResults.OracleResults.repository.ItemRepository;

import java.io.IOException;

@Service
public class CSVService {

    private final ItemRepository itemRepository;

    public CSVService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Método auxiliar para conversión segura a Integer
    private Integer convertToInteger(String value) {
        String trimmed = value.trim();
        if (trimmed.equalsIgnoreCase("null") || trimmed.isEmpty()) {
            return null;
        }
        return Integer.parseInt(trimmed);
    }

    public String saveCSV(MultipartFile file) {
        List<ItemOracle> items = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            br.readLine(); // Saltar cabecera
            
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                
                // Imprimir la cantidad de columnas para depuración
                System.out.println("Número de columnas en esta línea: " + values.length);
                
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replaceAll("^\"|\"$", "");
                    System.out.println("Valor " + i + ": " + values[i]);
                }
                System.out.println("Valores: " + java.util.Arrays.toString(values));
                
                // Validar que haya suficientes columnas antes de acceder a ellas
                if (values.length < 13) {
                    System.out.println("Error: La línea no tiene suficientes columnas, se omitirá.");
                    continue;
                }

                // Convertir valores numéricos de forma segura
                Integer quantity = convertToInteger(values[6]);
                Integer quantityCompleted = convertToInteger(values[7]);
                Integer quantityScrapped = (values.length > 8 && !values[8].isEmpty()) ? convertToInteger(values[8]) : 0;
                Integer quantityRemaining = (values.length > 9 && !values[9].isEmpty()) ? convertToInteger(values[9]) : 0;
                String dateClosed = (values.length > 12 && !values[12].isEmpty()) ? values[12] : "null";

                // Calcular el nuevo atributo basado en operación matemática
                Double yield = (quantity != null && quantity > 0) ? (double) quantityCompleted / quantity : 0.0;

                // Asegurar que no accedemos fuera de los límites del array
                String extraColumn = (values.length > 14) ? values[14] : "N/A";

                // Crear el objeto ItemOracle
                ItemOracle itemOracle = new ItemOracle(
                        null,                 // ID se genera automáticamente
                        values[0],            // Job
                        values[1],            // Status
                        values[2],            // Type
                        values[3],            // Job Class
                        values[4],            // Assembly
                        values[5],            // AssemblyDescription
                        quantity,             // Quantity
                        quantityCompleted,    // QuantityCompleted
                        yield,                // Yield calculado
                        quantityScrapped,     // QuantityScrapped
                        quantityRemaining,    // QuantityRemaining
                        values[11],           // StartDate
                        values[12],           // DateCompleted
                        dateClosed,           // DateClosed
                        extraColumn           // Nueva columna
                );
                
                // Verificar si ya existe un registro idéntico
                boolean exists = itemRepository.existsByJob(
                        itemOracle.getJob()
                );
                
                if (!exists) {  // Solo se agrega si no existe duplicado
                    items.add(itemOracle);
                } else {
                    System.out.println("Registro duplicado encontrado y omitido: " + java.util.Arrays.toString(values));
                }
            }
            itemRepository.saveAll(items);
            return "Archivo procesado y datos guardados correctamente.";
        } catch (IOException e) {
            return "Error al procesar el archivo CSV: " + e.getMessage();
        }
    }
}
