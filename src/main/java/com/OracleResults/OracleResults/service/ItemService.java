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
            Integer quantityMin,
            Integer quantityMax,
            String dateStart,
            String dateEnd) {
        Specification<ItemOracle> spec = ItemSpecification.filterItems(job, type, assembly, assemblyDescription,
                quantityMin, quantityMax, dateStart, dateEnd);

                System.out.println("Se reciben los parametros: " + job + type + assembly + assemblyDescription + quantityMin + quantityMax + dateStart + dateEnd);
        return itemRepository.findAll(spec);
    }
}
