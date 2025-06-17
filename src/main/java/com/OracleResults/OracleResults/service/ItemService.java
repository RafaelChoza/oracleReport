package com.OracleResults.OracleResults.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.OracleResults.OracleResults.dto.ItemOracle;
import com.OracleResults.OracleResults.dto.ItemSpecification;
import com.OracleResults.OracleResults.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<ItemOracle> getAllItems() {
        return itemRepository.findAll();
    }

    public Page<ItemOracle> getAllItemsPage(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public List<ItemOracle> searchItems(
            String job,
            String type,
            String assembly,
            String assemblyDescription,
            String quantityMin,
            String quantityMax,
            String dateStart,
            String dateEnd,
            String assemblyStartingWith,
            String jobEndingWith,
            String assemblyDescriptionContains) {
        Specification<ItemOracle> spec = ItemSpecification.filterItems(job, type, assembly, assemblyDescription,
                quantityMin, quantityMax, dateStart, dateEnd, assemblyStartingWith, jobEndingWith, assemblyDescriptionContains);
                job = job != null && !job.isBlank() ? job : null;
                type = type != null && !type.isBlank() ? type : null;
                assembly = assembly != null && !assembly.isBlank() ? assembly : null;
                assemblyDescription = assemblyDescription != null && !assemblyDescription.isBlank() ? assemblyDescription : null;
                quantityMin = quantityMin != null && !quantityMin.isBlank() ? quantityMin : "";
                quantityMax = quantityMax != null && !quantityMax.isBlank() ? quantityMax : "";
                dateStart = dateStart != null && !dateStart.isBlank() ? dateStart : null;
                dateEnd = dateEnd != null && !dateEnd.isBlank() ? dateEnd : null;
                assemblyStartingWith = assemblyStartingWith != null && !assemblyStartingWith.isBlank() ? assemblyStartingWith : null;
                jobEndingWith = jobEndingWith != null && !jobEndingWith.isBlank() ? jobEndingWith : null;
                assemblyDescriptionContains = assemblyDescriptionContains != null && !assemblyDescriptionContains.isBlank() ? assemblyDescriptionContains : null;                
                Integer quantityMinInt = (quantityMin != null && !quantityMin.isBlank()) ? Integer.parseInt(quantityMin) : 0;
                Integer quantityMaxInt = (quantityMax != null && !quantityMax.isBlank()) ? Integer.parseInt(quantityMax) : 0;
                System.out.println("Se reciben los parametros: " + job + type + assembly + assemblyDescription + quantityMinInt + quantityMaxInt + dateStart + dateEnd);
        return itemRepository.findAll(spec);
    }
}
